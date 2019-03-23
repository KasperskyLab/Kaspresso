package com.kaspersky.uitest_framework.espressoext.viewactions

import android.graphics.Rect
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import android.support.test.espresso.PerformException
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.util.HumanReadables
import android.support.v4.widget.NestedScrollView
import com.kaspersky.uitest_framework.util.isDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

/**
 * An implementation of scroll action.
 */
class ScrollIfPossibleAction : ViewAction {

    private val scrollableContainerMatcher = anyOf(
        isAssignableFrom(ScrollView::class.java),
        isAssignableFrom(HorizontalScrollView::class.java),
        isAssignableFrom(ListView::class.java),
        isAssignableFrom(NestedScrollView::class.java)
    )

    override fun getConstraints(): Matcher<View> {
        return allOf(
            withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
        )
    }

    override fun getDescription(): String = "scroll to"

    override fun perform(uiController: UiController, view: View) {

        if (view.isDisplayed()) {
            return
        }

        if (!isDescendantOfA(scrollableContainerMatcher).matches(view)) {
            return
        }

        val rect = Rect()
        view.getDrawingRect(rect)

        // todo it returns boolean(any parent scrolled or not), do we need to react somehow?
        view.requestRectangleOnScreen(rect, true)

        uiController.loopMainThreadUntilIdle()

        if (!view.isDisplayed()) {

            throw PerformException.Builder()
                .withActionDescription(description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(
                    RuntimeException(
                        "Scrolling to view was attempted, but the view is not displayed"
                    )
                )
                .build()
        }
    }
}