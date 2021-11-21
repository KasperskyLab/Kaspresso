package com.kaspersky.kaspresso.autoscroll

import android.util.Log
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.AutoScrollParams
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast


class AutoScrollFallbackProviderImpl(
    private val params: AutoScrollParams,
    private val logger: UiTestLogger
) : AutoScrollProvider<ViewInteraction> {

    /**
     * Invokes the given [action] and calls [scroll] if it fails. Helps in cases when test fails because of the
     * need to scroll to interacted view.
     *
     * @param interaction the instance of [ViewInteraction] interface to perform actions and assertions.
     * @param action the actual action on the interacted view.
     *
     * @throws Throwable if the exception caught while invoking [action] is not allowed via [params].
     * @return the result of [action] invocation.
     */
    @Throws(Throwable::class)
    override fun <T> withAutoScroll(interaction: ViewInteraction, action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (error.isAllowed(params.allowedExceptions)) {
                return scroll(interaction, action, error)
            }
            throw error
        }
    }

    /**
     * Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action].
     *
     * @param interaction the instance of [ViewInteraction] interface to perform actions and assertions.
     * @param action the actual action on the interacted view.
     * @param cachedError the error to be thrown if autoscroll would not help.
     *
     * @throws cachedError if autoscroll action did not help.
     * @return the result of [action] invocation.
     */
    @Throws(Throwable::class)
    override fun <T> scroll(interaction: ViewInteraction, action: () -> T, cachedError: Throwable): T {
        return try {
            interaction.perform(FallbackScrollToAction())
            logger.i("View autoScroll fallback successfully performed.")
            action.invoke()
        } catch (error: Throwable) {
            throw cachedError
        }
    }
}

class FallbackScrollToAction : ViewAction {
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

    private tailrec fun findFirstParentScrollView(view: View?): View? {
        return if (view is ScrollView || view is NestedScrollView || view is ListView) {
            view
        } else if (view?.parent != null) {
            findFirstParentScrollView(view.parent as View)
        } else {
            null
        }
    }

    override fun perform(uiController: UiController, view: View) {
        if (isDisplayingAtLeast(100).matches(view)) {
            Log.i(TAG, "View is already displayed. Returning.")
            return
        }

        val scrollView = findFirstParentScrollView(view)
        scrollView?.scrollTo(0, view.top - scrollView.paddingTop)

        uiController.loopMainThreadUntilIdle()
        if (!isDisplayingAtLeast(100).matches(view)) {
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
        return "scroll fallback to"
    }

    companion object {
        private val TAG = FallbackScrollToAction::class.java.simpleName
    }
}


