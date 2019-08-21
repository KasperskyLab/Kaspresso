package com.kaspersky.kaspresso.interceptors.interactors.impl.autoscroll

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import org.hamcrest.Matcher
import org.hamcrest.Matchers.anyOf

class AutoscrollViewInteractor : ViewInteractor {

    private val inScrollableContainerMatcher: Matcher<View> = anyOf(
        isAssignableFrom(ScrollView::class.java),
        isAssignableFrom(HorizontalScrollView::class.java),
        isAssignableFrom(ListView::class.java),
        isAssignableFrom(NestedScrollView::class.java)
    )

    override fun <R> interact(
        view: Any?,
        interaction: ViewInteraction,
        interactable: () -> R
    ): R {
        return try {
            interactable.invoke()
        } catch (e: NoMatchingViewException) {
            if (!isDescendantOfA(inScrollableContainerMatcher).matches(view)) {
                throw e
            }
            val cachedError = e
            try {
                interaction.perform(
                    ViewActions.scrollTo()
                )
                interactable.invoke()
            } catch (e: Throwable) {
                throw cachedError
            }
        }
    }
}