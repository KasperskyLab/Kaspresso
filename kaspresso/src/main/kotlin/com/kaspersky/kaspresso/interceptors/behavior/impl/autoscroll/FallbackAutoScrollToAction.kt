package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import android.view.View
import android.view.ViewParent
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class FallbackAutoScrollToAction(
    private val logger: UiTestLogger,
) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isDescendantOfA(
            Matchers.anyOf(
                ViewMatchers.isAssignableFrom(NestedScrollView::class.java),
                ViewMatchers.isAssignableFrom(ScrollView::class.java),
                ViewMatchers.isAssignableFrom(HorizontalScrollView::class.java),
                ViewMatchers.isAssignableFrom(ListView::class.java)
            )
        )
    }

    private fun View?.isScrollable(): Boolean =
        (this is ScrollView || this is NestedScrollView || this is HorizontalScrollView || this is ListView)

    private tailrec fun View?.findFirstParentScrollableView(lastView: View): View? {
        return when {
            this.isScrollable() -> this
            this == lastView -> null
            else -> this?.parent?.findFirstView()?.findFirstParentScrollableView(lastView)
        }
    }

    private tailrec fun ViewParent?.findFirstView(): View? {
        return if (this is View) {
            this
        } else {
            this?.parent.findFirstView()
        }
    }

    override fun perform(uiController: UiController, view: View) {
        if (ViewMatchers.isDisplayingAtLeast(FULLY_VISIBLE_PERCENTAGE).matches(view)) {
            logger.i(TAG, "View is already displayed. Returning.")
            return
        }

        val scrollView = view.findFirstParentScrollableView(view.rootView)

        if (scrollView == null) {
            logger.i(TAG, "No scrolling views found. Returning.")
            return
        }

        /**
         * Scroll forwards and try to find view
         */
        scrollView.scrollForwardsTo(view)
        uiController.loopMainThreadUntilIdle()

        if (ViewMatchers.isDisplayingAtLeast(FULLY_VISIBLE_PERCENTAGE).matches(view)) {
            logger.i(TAG, "View is already displayed after scrolling forwards. Returning.")
            return
        }

        /**
         * Scroll backwards and try to find view
         */
        scrollView.scrollBackwardsTo(view)
        uiController.loopMainThreadUntilIdle()

        if (ViewMatchers.isDisplayingAtLeast(FULLY_VISIBLE_PERCENTAGE).matches(view)) {
            logger.i(TAG, "View is already displayed after scrolling backwards. Returning.")
            return
        }

        /**
         * Throw error if view not found at this point
         */
        throw PerformException.Builder()
            .withActionDescription(this.description)
            .withViewDescription(HumanReadables.describe(view))
            .withCause(
                java.lang.RuntimeException(
                    "Fallback scrolling to view was attempted, but the view is not displayed"
                )
            )
            .build()
    }

    private fun View.scrollForwardsTo(view: View) {
        when (this is HorizontalScrollView) {
            true -> scrollTo(view.right + paddingEnd, 0)
            false -> scrollTo(0, view.bottom + paddingBottom)
        }
    }

    private fun View.scrollBackwardsTo(view: View) {
        when (this is HorizontalScrollView) {
            true -> scrollTo(view.left - paddingStart, 0)
            false -> scrollTo(0, view.top - paddingTop)
        }
    }

    override fun getDescription(): String {
        return "fallback scroll to"
    }

    companion object {
        private val TAG = FallbackAutoScrollToAction::class.java.simpleName
        private const val FULLY_VISIBLE_PERCENTAGE = 100
    }
}
