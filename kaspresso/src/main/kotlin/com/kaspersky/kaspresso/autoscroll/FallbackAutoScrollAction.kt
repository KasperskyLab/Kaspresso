package com.kaspersky.kaspresso.autoscroll

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.NestedScrollingChild
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.util.HumanReadables
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.hamcrest.Description
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

class FallbackAutoScrollAction : ViewAction {
    private lateinit var scrollType: ScrollTypes

    override fun getConstraints() = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("unable to scroll to view within NestedScrollView or CoordinatorLayout")
        }

        override fun matchesSafely(item: View): Boolean {
            val coordinatorLayoutMatcher = Matchers.allOf(
                isDescendantOfA(isAssignableFrom(CoordinatorLayout::class.java)),
                withEffectiveVisibility(Visibility.VISIBLE)
            )
            val nestedScrollViewMatcher = Matchers.allOf(
                isDescendantOfA(isAssignableFrom(NestedScrollView::class.java)),
                withEffectiveVisibility(Visibility.VISIBLE)
            )

            return when {
                nestedScrollViewMatcher.matches(item) -> {
                    scrollType = ScrollTypes.NESTED
                    true
                }

                coordinatorLayoutMatcher.matches(item) -> {
                    scrollType = ScrollTypes.COORDINATOR
                    true
                }

                else -> {
                    scrollType = ScrollTypes.COMMON
                    ViewActions.scrollTo().constraints.matches(item)
                }
            }
        }
    }

    override fun getDescription() = "fallback scroll to"

    override fun perform(uiController: UiController, view: View) {
        try {
            when (scrollType) {
                ScrollTypes.NESTED -> nestedScrollTo(view)
                ScrollTypes.COORDINATOR -> coordinatorScrollTo(view)
                ScrollTypes.COMMON -> scrollToInner(view)
            }
        } catch (ex: Exception) {
            throw PerformException.Builder()
                .withCause(ex)
                .withViewDescription(HumanReadables.describe(view))
                .withActionDescription(description)
                .build()
        }
        uiController.loopMainThreadUntilIdle()
    }

    private fun nestedScrollTo(view: View) {
        val nestedScrollView = findFirstParentLayoutOfType<NestedScrollView>(view)
        if (nestedScrollView != null) {
            tryToPreScrollInNestedScrollView(view, nestedScrollView)
            scrollToInner(view)
        } else {
            throw Exception("Unable to find parent of type NestedScrollView")
        }
    }

    private fun tryToPreScrollInNestedScrollView(scrollTarget: View, nestedScrollView: NestedScrollView) {
        val coordinatorLayout = findFirstParentLayoutOfType<NestedScrollView>(scrollTarget)
        if (coordinatorLayout != null) {
            val collapsingToolbarLayout = findCollapsingToolbarLayoutIn(coordinatorLayout)
            if (collapsingToolbarLayout != null) {
                val toolbarHeight = collapsingToolbarLayout.height
                nestedScrollView.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
                nestedScrollView.dispatchNestedScroll(0, toolbarHeight, 0, 0, null)
            }
        }
    }

    private fun coordinatorScrollTo(view: View) {
        val coordinatorLayout = findFirstParentLayoutOfType<CoordinatorLayout>(view)
        if (coordinatorLayout != null) {
            tryToPreScrollInCoordinatorLayout(view, coordinatorLayout)
            scrollToInner(view)
        } else {
            throw Exception("Unable to find parent of type CoordinatorLayout")
        }
    }

    private fun tryToPreScrollInCoordinatorLayout(view: View, coordinatorLayout: CoordinatorLayout) {
        val nestedScrollingChild = findFirstParentLayoutOfType<NestedScrollingChild>(view)
        if (nestedScrollingChild != null) {
            val collapsingToolbarLayout = findCollapsingToolbarLayoutIn(coordinatorLayout)
            if (collapsingToolbarLayout != null) {
                val toolbarHeight = collapsingToolbarLayout.height
                nestedScrollingChild.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
                nestedScrollingChild.dispatchNestedScroll(0, toolbarHeight, 0, 0, null)
            }
        }
    }

    private fun scrollToInner(view: View) {
        val rect = Rect()
        view.getDrawingRect(rect)
        view.requestRectangleOnScreen(rect, true)
        compensateParentPadding(view)
    }

    private fun compensateParentPadding(view: View) {
        val parent = findFirstScrollableParentOf(view) ?: return
        val scrollDirection = getScrollingDirection(parent)
        val horizontalScrollAmount = if (scrollDirection == ScrollingDirections.HORIZONTAL) parent.paddingLeft else 0
        val verticalScrollAmount = if (scrollDirection == ScrollingDirections.VERTICAL) parent.paddingTop else 0

        parent.scrollBy(horizontalScrollAmount, verticalScrollAmount)
    }

    private fun getScrollingDirection(scrollableView: View): ScrollingDirections {
        if (scrollableView is RecyclerView) {
            scrollableView.layoutManager?.let {
                if (it.canScrollHorizontally()) {
                    return ScrollingDirections.HORIZONTAL
                }
            }
        }
        if (scrollableView is HorizontalScrollView) {
            return ScrollingDirections.HORIZONTAL
        }

        return ScrollingDirections.VERTICAL
    }

    private inline fun <reified ParentType> findFirstParentLayoutOfType(view: View): ParentType? {
        var parent = view.parent
        while (parent != null && parent::class.java != ParentType::class.java) {
            parent = parent.parent
        }

        return parent as ParentType?
    }

    private fun findFirstScrollableParentOf(view: View): ViewGroup? {
        var parent = view.parent
        while (parent != null) {
            if (parent is ScrollView
                || parent is HorizontalScrollView
                || parent is RecyclerView
                || parent is NestedScrollView
                || parent is ListView
                || parent is CoordinatorLayout
            ) {
                return parent as ViewGroup?
            }
            parent = parent.parent
        }

        return null
    }

    private fun findCollapsingToolbarLayoutIn(viewGroup: ViewGroup): CollapsingToolbarLayout? {
        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            if (child is CollapsingToolbarLayout) {
                return child
            } else if (child is ViewGroup) {
                return findCollapsingToolbarLayoutIn(child)
            }
        }
        return null
    }

    private enum class ScrollingDirections {
        HORIZONTAL, VERTICAL
    }

    private enum class ScrollTypes {
        NESTED, COORDINATOR, COMMON
    }
}