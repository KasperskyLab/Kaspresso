package com.kaspersky.uitest_framework.kakao

import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.web.sugar.Web
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatTextView
import android.view.View
import com.agoda.kakao.BuilderMarker
import com.agoda.kakao.ItemMatcher
import com.agoda.kakao.PositionMatcher
import com.agoda.kakao.ViewMarker
import com.kaspersky.uitest_framework.kakao.dispatchers.DataDispatcher
import com.kaspersky.uitest_framework.kakao.dispatchers.ViewDispatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import kotlin.reflect.KClass

/**
 * Created by egor.kurnikov on 04.03.2019
 */

/**
 * Base class for all Kakao views
 *
 * This base class allows create new custom view with ease. All you
 * have to do is to extend this class, implement all necessarily additional
 * actions/assertions interfaces and override necessary constructors
 *
 * @param T Type of your custom view. Needs to be defined to enable invoke() and perform() for descendants
 */
@Suppress("UNCHECKED_CAST")
@ViewMarker
open class KBaseView<out T> : BaseActions, BaseAssertions {
    override val view: ViewDispatcher

    protected constructor(view: ViewDispatcher) {
        this.view = view
    }

    /**
     * Constructs view class with view interaction from given ViewBuilder
     *
     * @param function ViewBuilder which will result in view's interaction
     *
     * @see ViewBuilder
     */
    constructor(function: ViewBuilder.() -> Unit) {
        view = ViewBuilder().apply(function).getViewDispatcher()
    }

    /**
     * Constructs view class with parent and view interaction from given ViewBuilder
     *
     * @param parent Matcher that will be used as parent in isDescendantOfA() matcher
     * @param function ViewBuilder which will result in view's interaction
     *
     * @see ViewBuilder
     */
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : this({
        isDescendantOfA { withMatcher(parent) }
        function(this)
    })

    /**
     * Constructs view class with parent and view interaction from given ViewBuilder
     *
     * @param parent DataDispatcher that will be used as parent to ViewBuilder
     * @param function ViewBuilder which will result in view's interaction
     *
     * @see ViewBuilder
     */
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) {
        view = parent.onChildView(ViewBuilder().apply(function).getViewMatcher())
                .check(ViewAssertions.matches(Matchers.anything()))
    }

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your view
     */
    operator fun invoke(function: T.() -> Unit) {
        function(this as T)
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
    infix fun perform(function: T.() -> Unit): T {
        function(this as T)
        return this
    }
}

/**
 * Simple view with BaseActions and BaseAssertions
 *
 * @see BaseActions
 * @see BaseAssertions
 */
class KView : KBaseView<KView> {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with BaseActions and TextViewAssertions
 *
 * @see BaseActions
 * @see TextViewAssertions
 */
class KButton : KBaseView<KButton>, TextViewAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with BaseActions and TextViewAssertions
 *
 * @see BaseActions
 * @see TextViewActions
 * @see TextViewAssertions
 */
class KTextView : KBaseView<KTextView>, TextViewActions, TextViewAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with EditableActions and EditableAssertions
 *
 * @see EditableActions
 * @see EditableAssertions
 */
class KEditText : KBaseView<KEditText>, EditableActions, EditableAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with CheckableActions, CheckableAssertions and TextViewAssertions
 *
 * @see CheckableActions
 * @see CheckableAssertions
 * @see TextViewAssertions
 */
class KCheckBox : KBaseView<KCheckBox>, CheckableActions, TextViewAssertions, CheckableAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with SwipeableActions and ViewPagerAssertions
 *
 * @see SwipeableActions
 * @see ViewPagerAssertions
 */
class KViewPager : KBaseView<KViewPager>, SwipeableActions, ViewPagerAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with BaseActions and ImageViewAssertions
 *
 * @see BaseActions
 * @see ImageViewAssertions
 */
class KImageView : KBaseView<KImageView>, ImageViewAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with DrawerActions and BaseAssertions
 *
 * @see DrawerActions
 * @see BaseAssertions
 */
class KDrawerView : KBaseView<KDrawerView>, DrawerActions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with SwipeableActions and BaseAssertions
 *
 * @see SwipeableActions
 * @see BaseAssertions
 */
class KSwipeView : KBaseView<KSwipeView>, SwipeableActions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with NavigationViewActions and NavigationViewAssertions
 *
 * @see NavigationViewActions
 * @see NavigationViewAssertions
 */
class KNavigationView : KBaseView<KNavigationView>, NavigationViewActions, NavigationViewAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with ProgressBarActions and ProgressBarAssertions
 *
 * @see ProgressBarActions
 * @see ProgressBarAssertions
 */
class KProgressBar : KBaseView<KProgressBar>, ProgressBarActions, ProgressBarAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with SeekBarActions and ProgressBarAssertions
 *
 * @see SeekBarActions
 * @see ProgressBarAssertions
 */
class KSeekBar : KBaseView<KSeekBar>, SeekBarActions, ProgressBarAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with RatingBarActions and RatingBarAssertions
 *
 * @see RatingBarActions
 * @see RatingBarAssertions
 */
class KRatingBar : KBaseView<KRatingBar>, RatingBarActions, RatingBarAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View for acting and asserting on BottomNavigationView
 *
 * @see BottomNavigationViewActions
 * @see BottomNavigationViewAssertions
 */
class KBottomNavigationView : KBaseView<KBottomNavigationView>,
        BottomNavigationViewActions, BottomNavigationViewAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with internal TextView and a Button
 *
 * @see Snackbar
 */
class KSnackbar : KBaseView<KSnackbar>({ isInstanceOf(Snackbar.SnackbarLayout::class.java) }) {
    val text = KTextView {
        isDescendantOfA { isInstanceOf(Snackbar.SnackbarLayout::class.java) }
        isInstanceOf(AppCompatTextView::class.java)
    }

    val action = KButton {
        isDescendantOfA { isInstanceOf(Snackbar.SnackbarLayout::class.java) }
        isInstanceOf(AppCompatButton::class.java)
    }
}

/**
 * View with TabLayoutActions and TabLayoutAssertions
 *
 * @see TabLayoutActions
 * @see TabLayoutAssertions
 */
class KTabLayout : KBaseView<KTabLayout>, TabLayoutActions, TabLayoutAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with SwipeRefreshLayoutActions and SwipeRefreshLayoutAssertions
 *
 * @see SwipeRefreshLayoutActions
 * @see SwipeRefreshLayoutAssertions
 */
class KSwipeRefreshLayout : KBaseView<KSwipeRefreshLayout>, SwipeRefreshLayoutActions, SwipeRefreshLayoutAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * View with TextInputLayoutAssertions
 *
 * @see TextInputLayoutAssertions
 */
class KTextInputLayout : KBaseView<KTextInputLayout>, TextInputLayoutAssertions {
    val edit: KEditText

    constructor(function: ViewBuilder.() -> Unit) : super(function) {
        edit = KEditText {
            isDescendantOfA(function)
            isInstanceOf(TextInputEditText::class.java)
        }
    }

    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function) {
        edit = KEditText {
            isDescendantOfA(function)
            isInstanceOf(TextInputEditText::class.java)
        }
    }

    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function) {
        edit = KEditText {
            isDescendantOfA(function)
            isInstanceOf(TextInputEditText::class.java)
        }
    }
}

/**
 * View with ScrollViewActions and BaseAssertions. Gives access to it's children
 *
 * @see ScrollViewActions
 * @see BaseAssertions
 *
 * @see KAdapterItem
 * @see KAdapterItemTypeBuilder
 */
@ViewMarker
class KListView : ScrollViewActions, BaseAssertions, ListViewAdapterAssertions {
    val matcher: Matcher<View>
    val itemTypes: Map<KClass<out KAdapterItem<*>>, KAdapterItemType<KAdapterItem<*>>>

    override val view: ViewDispatcher

    /**
     * Constructs view class with view interaction from given ViewBuilder
     *
     * @param builder ViewBuilder which will result in view's interaction
     * @param itemTypeBuilder Lambda with receiver where you pass your item providers
     *
     * @see ViewBuilder
     */
    constructor(builder: ViewBuilder.() -> Unit, itemTypeBuilder: KAdapterItemTypeBuilder.() -> Unit) {
        val vb = ViewBuilder().apply(builder)
        matcher = vb.getViewMatcher()
        view = vb.getViewDispatcher()
        itemTypes = KAdapterItemTypeBuilder().apply(itemTypeBuilder).itemTypes
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
                itemTypeBuilder: KAdapterItemTypeBuilder.() -> Unit) : this({
        isDescendantOfA { withMatcher(parent) }
        builder(this)
    }, itemTypeBuilder)

    /**
     * Constructs view class with parent and view interaction from given ViewBuilder
     *
     * @param parent DataDispatcher that will be used as parent to ViewBuilder
     * @param builder ViewBuilder which will result in view's interaction
     * @param itemTypeBuilder Lambda with receiver where you pass your item providers
     *
     * @see ViewBuilder
     */
    @Suppress("UNCHECKED_CAST")
    constructor(parent: DataDispatcher, builder: ViewBuilder.() -> Unit,
                itemTypeBuilder: KAdapterItemTypeBuilder.() -> Unit) {
        val makeTargetMatcher = DataDispatcher::class.java.getDeclaredMethod("makeTargetMatcher")
        val parentMatcher = makeTargetMatcher.invoke(parent)

        val vb = ViewBuilder().apply {
            isDescendantOfA { withMatcher(parentMatcher as Matcher<View>) }
            builder(this)
        }

        matcher = vb.getViewMatcher()
        view = vb.getViewDispatcher()
        itemTypes = KAdapterItemTypeBuilder().apply(itemTypeBuilder).itemTypes
    }

    /**
     * Performs given actions/assertion on child at given position
     *
     * @param T Type of item at given position. Must be registered via constructor.
     * @param position Position of item in adapter
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    inline fun <reified T : KAdapterItem<*>> childAt(position: Int, function: T.() -> Unit) {
        val provideItem = itemTypes.getOrElse(T::class) {
            throw IllegalStateException("${T::class.java.simpleName} did not register to KListView")
        }.provideItem

        val interaction = Espresso
                .onData(Matchers.anything())
                .inAdapterView(matcher)
                .atPosition(position)

        function(provideItem(DataDispatcher(interaction)) as T)
    }

    /**
     * Performs given actions/assertion on first child in adapter
     *
     * @param T Type of item at first position. Must be registered via constructor.
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    inline fun <reified T : KAdapterItem<*>> firstChild(function: T.() -> Unit) {
        childAt(0, function)
    }

    /**
     * Performs given actions/assertion on last child in adapter
     *
     * @param T Type of item at last position. Must be registered via constructor.
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    inline fun <reified T : KAdapterItem<*>> lastChild(function: T.() -> Unit) {
        childAt(getSize() - 1, function)
    }

    /**
     * Performs given actions/assertion on all children in adapter
     *
     * @param T Type of all items. Must be registered via constructor.
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    inline fun <reified T : KAdapterItem<*>> children(function: T.() -> Unit) {
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
    inline fun <reified T : KAdapterItem<*>> childWith(childMatcher: DataBuilder.() -> Unit): T {
        val provideItem = itemTypes.getOrElse(T::class) {
            throw IllegalStateException("${T::class.java.simpleName} did not register to KListView")
        }.provideItem

        val interaction = Espresso
                .onData(DataBuilder().apply(childMatcher).getDataMatcher())
                .inAdapterView(matcher)

        return provideItem(DataDispatcher(interaction)) as T
    }

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your view
     */
    operator fun invoke(function: KListView.() -> Unit) {
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
    infix fun perform(function: KListView.() -> Unit): KListView {
        function.invoke(this)
        return this
    }
}

/**
 * View with RecyclerActions and BaseAssertions. Gives access to it's children
 *
 * @see RecyclerActions
 * @see BaseAssertions
 *
 * @param builder ViewBuilder which will match your list view

 *
 * @see KRecyclerItem
 * @see KRecyclerItemTypeBuilder
 */
@ViewMarker
class KRecyclerView : RecyclerActions, BaseAssertions, RecyclerAdapterAssertions {
    val matcher: Matcher<View>
    val itemTypes: Map<KClass<out KRecyclerItem<*>>, KRecyclerItemType<KRecyclerItem<*>>>

    override val view: ViewDispatcher

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
        view = vb.getViewDispatcher()
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
     * @param parent DataDispatcher that will be used as parent to ViewBuilder
     * @param builder ViewBuilder which will result in view's interaction
     * @param itemTypeBuilder Lambda with receiver where you pass your item providers
     *
     * @see ViewBuilder
     */
    @Suppress("UNCHECKED_CAST")
    constructor(parent: DataDispatcher, builder: ViewBuilder.() -> Unit,
                itemTypeBuilder: KRecyclerItemTypeBuilder.() -> Unit) {
        val makeTargetMatcher = DataDispatcher::class.java.getDeclaredMethod("makeTargetMatcher")
        val parentMatcher = makeTargetMatcher.invoke(parent)

        val vb = ViewBuilder().apply {
            isDescendantOfA { withMatcher(parentMatcher as Matcher<View>) }
            builder(this)
        }

        matcher = vb.getViewMatcher()
        view = vb.getViewDispatcher()
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
        } catch (error: Throwable) {
        }

        function(provideItem(PositionMatcher(matcher, position)) as T)
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
        } catch (error: Throwable) {
        }

        return provideItem(ItemMatcher(matcher,
                ViewBuilder().apply(childMatcher).getViewMatcher())) as T
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
        Espresso.onView(match).check(ViewAssertions.matches(Matchers.anything()))
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
}

/**
 * Class that maps types to providing functions
 *
 * To be able to support different item types in KRecyclerView, this class
 * adds support for mapping item type classes to functions that provide them.
 * KEmptyRecyclerItem is added by default.
 *
 * @see itemType
 * @see KEmptyRecyclerItem
 */
@BuilderMarker
class KRecyclerItemTypeBuilder {
    val itemTypes = mutableMapOf<KClass<out KRecyclerItem<*>>, KRecyclerItemType<KRecyclerItem<*>>>()

    init {
        itemTypes.put(KRecyclerItem::class, KRecyclerItemType { matcher -> KEmptyRecyclerItem(matcher) })
        itemTypes.put(KEmptyRecyclerItem::class, KRecyclerItemType { matcher -> KEmptyRecyclerItem(matcher) })
    }

    /**
     * Adds entry that helps KRecyclerView to automatically build child views
     *
     * To make it work, you need to pass here function (lambda, constructor), that takes matcher and returns
     * instance of your item type. In this case, matcher actually matches root view of your adapter item.
     *
     * @param provideItem Function that takes matcher of item's root view and returns instance of item view
     */
    inline fun <reified T : KRecyclerItem<*>> itemType(noinline provideItem: (Matcher<View>) -> T) {
        itemTypes.put(T::class, KRecyclerItemType(provideItem))
    }
}

/**
 * Class that maps types to providing functions
 *
 * To be able to support different item types in KListView, this class
 * adds support for mapping item type classes to functions that provide them.
 * KEmptyAdapterItem is added by default.
 *
 * @see itemType
 * @see KEmptyAdapterItem
 */
@BuilderMarker
class KAdapterItemTypeBuilder {
    val itemTypes = mutableMapOf<KClass<out KAdapterItem<*>>, KAdapterItemType<KAdapterItem<*>>>()

    init {
        itemTypes.put(KAdapterItem::class, KAdapterItemType { interaction -> KEmptyAdapterItem(interaction) })
        itemTypes.put(KEmptyAdapterItem::class, KAdapterItemType { interaction -> KEmptyAdapterItem(interaction) })
    }

    /**
     * Adds entry that helps KListView to automatically build child views
     *
     * To make it work, you need to pass here function (lambda, constructor), that takes matcher and returns
     * instance of your item type. In this case, matcher actually matches your KListView and additional
     * matchers that were used (in firstChild(), childAt(), etc.)
     *
     * @param provideItem Function that takes data interaction of list view and returns instance of item view
     */
    inline fun <reified T : KAdapterItem<*>> itemType(noinline provideItem: (DataDispatcher) -> T) {
        itemTypes.put(T::class, KAdapterItemType(provideItem))
    }
}

/**
 * For internal use. Don't use manually.
 *
 * Holds type and corresponding provider function
 */
class KRecyclerItemType<out T : KRecyclerItem<*>>(val provideItem: (Matcher<View>) -> T)

/**
 * For internal use. Don't use manually.
 *
 * Holds type and corresponding provider function
 */
class KAdapterItemType<out T : KAdapterItem<*>>(val provideItem: (DataDispatcher) -> T)

/**
 * Base class for KRecyclerView adapter items
 *
 * Please extend this class to provide custom recycler view item types
 *
 * @param T type of your item. Used to enable invoke() and perform() on descendants
 * @param matcher Matcher of root view of adapter item. Can be used as parent for all views inside item.
 *
 * @see KRecyclerItemTypeBuilder
 */
@Suppress("UNCHECKED_CAST")
@ViewMarker
open class KRecyclerItem<out T>(matcher: Matcher<View>) : BaseActions, BaseAssertions {
    override val view = ViewDispatcher(Espresso.onView(matcher))

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your view
     */
    operator fun invoke(function: T.() -> Unit) {
        function(this as T)
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
    infix fun perform(function: T.() -> Unit): T {
        function(this as T)
        return this
    }
}

/**
 * Empty implementation of KRecyclerItem
 *
 * Use this if you want to perform/assert on the root view of view holder
 *
 * @param parent Matcher of the root view of view holder
 * @see KRecyclerItem
 */
class KEmptyRecyclerItem(parent: Matcher<View>) : KRecyclerItem<KEmptyRecyclerItem>(parent)

/**
 * Base class for KRecyclerView adapter items
 *
 * Please extend this class to provide custom recycler view item types
 *
 * @param T type of your item. Used to enable invoke() and perform() on descendants
 * @param interaction Data interaction of item. Can be used as parent for all views inside item.
 *
 * @see KRecyclerItemTypeBuilder
 */
@Suppress("UNCHECKED_CAST")
@ViewMarker
open class KAdapterItem<out T>(interaction: DataDispatcher) : BaseActions, BaseAssertions {
    override val view = interaction.check(ViewAssertions.matches(Matchers.anything()))

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your view
     */
    operator fun invoke(function: T.() -> Unit) {
        function(this as T)
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
    infix fun perform(function: T.() -> Unit): T {
        function(this as T)
        return this
    }
}

/**
 * Empty implementation of KAdapterItem
 *
 * Use this if you want to perform/assert on the root view of adapter item
 *
 * @param parent Matcher of the root view of adapter item
 * @see KAdapterItem
 */
class KEmptyAdapterItem(parent: DataDispatcher) : KAdapterItem<KEmptyAdapterItem>(parent)

/**
 * Class for interacting with WebViews
 *
 * @param matcher ViewBuilder which will result in matcher of web view
 */
@ViewMarker
open class KWebView(matcher: (ViewBuilder.() -> Unit)? = null) {
    private val web: Web.WebInteraction<*> = if (matcher != null) {
        Web.onWebView(ViewBuilder().apply(matcher).getViewMatcher())
    } else {
        Web.onWebView()
    }

    /**
     * Operator that allows usage of DSL style
     *
     * @param function WebElementBuilder which will give you access to match elements
     * and perform actions/assertions on it.
     */
    operator fun invoke(function: WebElementBuilder.() -> Unit) {
        WebElementBuilder(web).apply(function)
    }
}