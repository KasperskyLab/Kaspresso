package com.kaspersky.kaspresso.viewactions.scroll

import android.graphics.Rect
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.util.HumanReadables
import androidx.core.widget.NestedScrollView
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import com.kaspersky.kaspresso.extensions.other.isDisplayed
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