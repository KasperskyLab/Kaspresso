@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.recycler

import android.view.View
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.RootMatchers
import com.kaspersky.uitest_framework.kakao.dispatchers.ViewDispatcher
import com.kaspersky.uitest_framework.kakao.common.KakaoDslMarker
import com.kaspersky.uitest_framework.kakao.common.actions.BaseActions
import com.kaspersky.uitest_framework.kakao.common.assertions.BaseAssertions
import org.hamcrest.Matcher

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
@KakaoDslMarker
open class KRecyclerItem<out T>(matcher: Matcher<View>) : BaseActions, BaseAssertions {
    override val view = ViewDispatcher(Espresso.onView(matcher))
    override var root = RootMatchers.DEFAULT

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