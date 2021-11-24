package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import android.util.Log
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
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class FallbackAutoScrollToAction : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return Matchers.allOf(
            ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
            ViewMatchers.isDescendantOfA(
                Matchers.anyOf(
                    ViewMatchers.isAssignableFrom(NestedScrollView::class.java),
                    ViewMatchers.isAssignableFrom(ScrollView::class.java),
                    ViewMatchers.isAssignableFrom(HorizontalScrollView::class.java),
                    ViewMatchers.isAssignableFrom(ListView::class.java)
                )
            )
        )
    }

    private fun View?.isScrollable(): Boolean =
        (this is ScrollView || this is NestedScrollView || this is HorizontalScrollView || this is ListView)

    private tailrec fun View?.findFirstParentScrollableView(lastView: View): View? {
        return if (this == lastView) {
            if (this.isScrollable()) this else null
        } else {
            if (this.isScrollable()) {
                this
            } else {
                this?.parent?.findFirstView()?.findFirstParentScrollableView(lastView)
            }
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
        if (ViewMatchers.isDisplayingAtLeast(100).matches(view)) {
            Log.i(TAG, "View is already displayed. Returning.")
            return
        }

        val scrollView = view.findFirstParentScrollableView(view.rootView)

        when (scrollView is HorizontalScrollView) {
            true -> scrollView.scrollTo(view.right + scrollView.paddingEnd, 0)
            false -> scrollView?.scrollTo(0, view.top - scrollView.paddingTop)
        }

        uiController.loopMainThreadUntilIdle()
        if (!ViewMatchers.isDisplayingAtLeast(100).matches(view)) {
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
    }

    override fun getDescription(): String {
        return "fallback scroll to"
    }

    companion object {
        private val TAG = FallbackAutoScrollToAction::class.java.simpleName
    }
}
