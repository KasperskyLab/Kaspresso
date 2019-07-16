package com.kaspersky.kaspresso.interceptors.interactors.impl.autoscroll

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
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