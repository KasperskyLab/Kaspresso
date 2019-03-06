@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.recycler

import android.view.View
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import com.kaspersky.uitest_framework.kakao.common.actions.ScrollableActions
import com.kaspersky.uitest_framework.kakao.common.actions.SwipeableActions
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Provides ScrollableActions implementation for RecyclerView
 *
 * @see ScrollableActions
 * @see SwipeableActions
 * @see RecyclerView
 */
interface RecyclerActions : ScrollableActions, SwipeableActions {
    override fun scrollToStart() {
        view.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
    }

    override fun scrollToEnd() {
        view.perform(object : ViewAction {
            override fun getDescription() = "Scroll RecyclerView to the bottom"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(RecyclerView::class.java)

            override fun perform(controller: UiController, view: View) {
                if (view is RecyclerView) {
                    val position = view.adapter!!.itemCount - 1
                    view.scrollToPosition(position)
                    controller.loopMainThreadUntilIdle()
                    val lastView = view.findViewHolderForLayoutPosition(position)!!.itemView
                    view.scrollBy(0, lastView.height)
                    controller.loopMainThreadUntilIdle()
                }
            }
        })
    }

    override fun scrollTo(position: Int) {
        view.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    /**
     * Scrolls to specific view holder that matches given matcher
     *
     * @param matcher Matcher for view holder, which is scroll destination
     */
    fun scrollTo(matcher: Matcher<View>) {
        view.perform(RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(matcher))
    }

    /**
     * Scrolls to specific view holder that matches given matcher
     *
     * @param viewBuilder Builder that will be used to match view to scroll
     */
    fun scrollTo(viewBuilder: ViewBuilder.() -> Unit) {
        scrollTo(ViewBuilder().apply { withIndex(0, viewBuilder) }.getViewMatcher())
    }

    /**
     * Returns the size of RecyclerView
     *
     * @return size of adapter
     *
     * @see RecyclerView
     */
    fun getSize(): Int {
        var size = 0

        view.perform(object : ViewAction {
            override fun getDescription() = "Get RecyclerView adapter size"

            override fun getConstraints() = Matchers.allOf(ViewMatchers.isAssignableFrom(RecyclerView::class.java), ViewMatchers.isDisplayed())

            override fun perform(uiController: UiController?, view: View?) {
                if (view is RecyclerView) {
                    size = view.adapter?.itemCount!!
                }
            }
        })

        return size
    }
}