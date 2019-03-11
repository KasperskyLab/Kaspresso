@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.recycler

import android.view.View
import android.support.test.espresso.Espresso
import android.support.test.espresso.Root
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.RootMatchers
import com.kaspersky.uitest_framework.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.kakao.common.KakaoDslMarker
import com.kaspersky.uitest_framework.kakao.common.assertions.BaseAssertions
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.common.matchers.ItemMatcher
import com.kaspersky.uitest_framework.kakao.common.matchers.PositionMatcher
import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import kotlin.reflect.KClass

/**
 * View with RecyclerActions, BaseAssertions and RecyclerAdapterAssertions. Gives access to it's children
 *
 * @see RecyclerActions
 * @see BaseAssertions
 * @see RecyclerAdapterAssertions
 * @see KRecyclerItem
 * @see KRecyclerItemTypeBuilder
 */
@KakaoDslMarker
class KRecyclerView : RecyclerActions, BaseAssertions, RecyclerAdapterAssertions {
    val matcher: Matcher<View>
    val itemTypes: Map<KClass<out KRecyclerItem<*>>, KRecyclerItemType<KRecyclerItem<*>>>

    override val view: ViewInteractionDelegate
    override var root: Matcher<Root> = RootMatchers.DEFAULT

    /**
     * Constructs view class with view interaction from given ViewBuilder
     *
     * @param builder ViewBuilder which will result in view's interaction
     * @param itemTypeBuilder Lambda with receiver where you pass your item providers
     *
     * @see ViewBuilder
     */
    constructor(builder: ViewBuilder.() -> Unit, itemTypeBuilder: KRecyclerItemTypeBuilder.() -> Unit) {
        val vb = ViewBuilder().apply(builder)
        matcher = vb.getViewMatcher()
        view = vb.getViewInteractionDelegate()
        itemTypes = KRecyclerItemTypeBuilder().apply(itemTypeBuilder).itemTypes
    }

    /**
     * Constructs view class with parent and view interaction from given ViewBuilder
     *
     * @param parent Matcher that will be used as parent in isDescendantOfA() matcher
     * @param builder ViewBuilder which will result in view's interaction
     * @param itemTypeBuilder Lambda with receiver where you pass your item providers
     *
     * @see ViewBuilder
     */
    constructor(parent: Matcher<View>, builder: ViewBuilder.() -> Unit,
                itemTypeBuilder: KRecyclerItemTypeBuilder.() -> Unit) : this({
        isDescendantOfA { withMatcher(parent) }
        builder(this)
    }, itemTypeBuilder)

    /**
     * Constructs view class with parent and view interaction from given ViewBuilder
     *
     * @param parent DataInteractionDelegate that will be used as parent to ViewBuilder
     * @param builder ViewBuilder which will result in view's interaction
     * @param itemTypeBuilder Lambda with receiver where you pass your item providers
     *
     * @see ViewBuilder
     */
    @Suppress("UNCHECKED_CAST")
    constructor(parent: DataInteractionDelegate, builder: ViewBuilder.() -> Unit,
                itemTypeBuilder: KRecyclerItemTypeBuilder.() -> Unit) {
        val makeTargetMatcher = DataInteractionDelegate::class.java.getDeclaredMethod("makeTargetMatcher")
        val parentMatcher = makeTargetMatcher.invoke(parent)

        val vb = ViewBuilder().apply {
            isDescendantOfA { withMatcher(parentMatcher as Matcher<View>) }
            builder(this)
        }

        matcher = vb.getViewMatcher()
        view = vb.getViewInteractionDelegate()
        itemTypes = KRecyclerItemTypeBuilder().apply(itemTypeBuilder).itemTypes
    }

    /**
     * Performs given actions/assertion on child at given position
     *
     * @param T Type of item at given position. Must be registered via constructor.
     * @param position Position of item in adapter
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    inline fun <reified T : KRecyclerItem<*>> childAt(position: Int, function: T.() -> Unit) {
        val provideItem = itemTypes.getOrElse(T::class) {
            throw IllegalStateException("${T::class.java.simpleName} did not register to KRecyclerView")
        }.provideItem

        try {
            scrollTo(position)
        } catch (error: Throwable) {}

        function((provideItem(PositionMatcher(matcher, position)) as T).also { inRoot { withMatcher(this@KRecyclerView.root) } })
    }

    /**
     * Performs given actions/assertion on first child in adapter
     *
     * @param T Type of item at first position. Must be registered via constructor.
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    inline fun <reified T : KRecyclerItem<*>> firstChild(function: T.() -> Unit) {
        childAt(0, function)
    }

    /**
     * Performs given actions/assertion on last child in adapter
     *
     * @param T Type of item at last position. Must be registered via constructor.
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    inline fun <reified T : KRecyclerItem<*>> lastChild(function: T.() -> Unit) {
        childAt(getSize() - 1, function)
    }

    /**
     * Performs given actions/assertion on all children in adapter
     *
     * @param T Type of all items. Must be registered via constructor.
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    inline fun <reified T : KRecyclerItem<*>> children(function: T.() -> Unit) {
        for (i in 0 until getSize()) {
            childAt(i, function)
        }
    }

    /**
     * Performs given actions/assertion on child that matches given matcher
     *
     * @param T Type of item at given position. Must be registered via constructor.
     * @param childMatcher Matcher for item in adapter
     * @return Item with type T. To make actions/assertions on it immediately, use perform() infix function.
     */
    inline fun <reified T : KRecyclerItem<*>> childWith(noinline childMatcher: ViewBuilder.() -> Unit): T {
        val provideItem = itemTypes.getOrElse(T::class) {
            throw IllegalStateException("${T::class.java.simpleName} did not register to KRecyclerView")
        }.provideItem

        try {
            scrollTo(childMatcher)
        } catch (error: Throwable) {}

        return (provideItem(ItemMatcher(matcher,
                ViewBuilder().apply(childMatcher).getViewMatcher())) as T).also { inRoot { withMatcher(this@KRecyclerView.root) } }
    }

    /**
     * Returns the adapter position of item matched by given matcher
     *
     * @param childMatcher Matcher that will be used to find item
     * @return Position of that item in adapter
     */
    fun getPosition(childMatcher: ViewBuilder.() -> Unit): Int {
        val match = ItemMatcher(matcher, ViewBuilder().apply(childMatcher).getViewMatcher())

        scrollTo(childMatcher)
        Espresso.onView(match).inRoot(root).check(ViewAssertions.matches(Matchers.anything()))
        return match.position
    }

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your view
     */
    operator fun invoke(function: KRecyclerView.() -> Unit) {
        function(this)
    }

    /**
     * Infix function for invoking lambda on your view
     *
     * Sometimes instance of view is a result of a function or constructor.
     * In this specific case you can't call invoke() since it will be considered as
     * tail lambda of your fun/constructor. In such cases please use this function.
     *
     * @param function Tail lambda with receiver which is your view
     * @return This object
     */
    infix fun perform(function: KRecyclerView.() -> Unit): KRecyclerView {
        function(this)
        return this
    }

    /**
     * Calls childAt() on your view with base child
     *
     * Calls childAt() on your KRecyclerView and casts received item to KEmptyRecyclerItem
     *
     * @param position Position of child in adapter
     * @param tail Lambda with KEmptyRecyclerItem receiver
     * @see KEmptyRecyclerItem
     */
    fun emptyChildAt(position: Int, tail: KEmptyRecyclerItem.() -> Unit) {
        childAt(position, tail)
    }

    /**
     * Calls firstChild() on your view with base child
     *
     * Calls firstChild() on your KRecyclerView and casts received item to KEmptyRecyclerItem
     *
     * @param tail Lambda with KEmptyRecyclerItem receiver
     * @see KEmptyRecyclerItem
     */
    fun emptyFirstChild(tail: KEmptyRecyclerItem.() -> Unit) {
        firstChild(tail)
    }

    /**
     * Calls lastChild() on your view with base child
     *
     * Calls lastChild() on your KRecyclerView and casts received item to KEmptyRecyclerItem
     *
     * @param tail Lambda with KEmptyRecyclerItem receiver
     * @see KEmptyRecyclerItem
     */
    fun emptyLastChild(tail: KEmptyRecyclerItem.() -> Unit) {
        lastChild(tail)
    }

    /**
     * Calls childWith() on your view with base child
     *
     * Calls childWith() on your KRecyclerView and casts received item to KEmptyRecyclerItem
     *
     * @param builder View builder that will match the child view
     * @return Matched KEmptyRecyclerItem
     * @see KEmptyRecyclerItem
     */
    fun emptyChildWith(builder: ViewBuilder.() -> Unit) = childWith<KEmptyRecyclerItem>(builder)
}
