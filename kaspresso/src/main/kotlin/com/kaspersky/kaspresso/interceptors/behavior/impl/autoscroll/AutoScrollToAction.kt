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
import io.github.kakaocup.kakao.common.actions.NestedScrollToAction

class AutoScrollToAction(
    private val logger: UiTestLogger,
    private val viewAction: ViewAction = NestedScrollToAction(),
) : ViewAction by viewAction {

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
        try {
            viewAction.perform(uiController, view)
        } catch (exception: PerformException) {
            autoScrollForScrollViewsWithPadding(uiController, view)
        } catch (exception: Exception) {
            throw exception
        }
    }

    private fun autoScrollForScrollViewsWithPadding(uiController: UiController, view: View) {
        if (ViewMatchers.isDisplayingAtLeast(VISIBLE_AREA_PERCENTAGE).matches(view)) {
            logger.i(TAG, "View is displayed. Returning.")
            return
        }

        val scrollView = view.findFirstParentScrollableView(view.rootView)

        /**
         * Scroll till view and try to find it
         */
        scrollView?.scrollTo(view)
        uiController.loopMainThreadUntilIdle()

        if (ViewMatchers.isDisplayingAtLeast(VISIBLE_AREA_PERCENTAGE).matches(view)) {
            logger.i(TAG, "View is already displayed after fallback scrolling to view. Returning.")
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

    private fun View.scrollTo(view: View) {
        makeFirstParentScrollableViewScroll()
        scrollTo(view.x.toInt(), view.y.toInt())
    }

    private fun View.makeFirstParentScrollableViewScroll() {
        val nextView = this.parent.findFirstView() ?: return
        nextView.findFirstParentScrollableView(nextView.rootView)?.also { scrollableParent ->
            nextView.makeFirstParentScrollableViewScroll()
            scrollableParent.scrollTo(this.x.toInt(), this.y.toInt())
        }
    }

    companion object {
        private val TAG = AutoScrollToAction::class.java.simpleName
        private const val VISIBLE_AREA_PERCENTAGE = 90 // This is the default required by Espresso to be able to perform a click
    }
}
