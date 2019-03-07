@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.common.actions

import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.*
import com.kaspersky.uitest_framework.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import org.hamcrest.Matcher

/**
 * Base interface for performing actions on view
 *
 * Provides a lot of basic action methods, such as click(), scrollTo(), etc.
 *
 * @see EditableActions
 * @see SwipeableActions
 * @see ScrollableActions
 * @see CheckableActions
 */
interface BaseActions {
    val view: ViewInteractionDelegate

    /**
     * Performs click on view
     *
     * @param location Location of view where it should be clicked (VISIBLE_CENTER by default)
     */
    fun click(location: GeneralLocation = GeneralLocation.VISIBLE_CENTER) {
        view.perform(GeneralClickAction(Tap.SINGLE, location, Press.FINGER,
                InputDevice.SOURCE_UNKNOWN, MotionEvent.BUTTON_PRIMARY))
    }

    /**
     * Performs double click on view
     *
     * @param location Location of view where it should be clicked (VISIBLE_CENTER by default)
     */
    fun doubleClick(location: GeneralLocation = GeneralLocation.VISIBLE_CENTER) {
        view.perform(GeneralClickAction(Tap.DOUBLE, location, Press.FINGER,
                InputDevice.SOURCE_UNKNOWN, MotionEvent.BUTTON_PRIMARY))
    }

    /**
     * Performs long click on view
     *
     * @param location Location of view where it should be clicked (VISIBLE_CENTER by default)
     */
    fun longClick(location: GeneralLocation = GeneralLocation.VISIBLE_CENTER) {
        view.perform(GeneralClickAction(Tap.LONG, location, Press.FINGER,
                InputDevice.SOURCE_UNKNOWN, MotionEvent.BUTTON_PRIMARY))
    }

    /**
     * Presses IME action, if supported view is in focus
     */
    fun pressImeAction() {
        view.perform(ViewActions.pressImeActionButton())
    }

    /**
     * Scrolls to the view, if possible
     */
    fun scrollTo() {
        view.perform(ViewActions.scrollTo())
    }

    /**
     * Performs custom action on a view
     *
     * @param function Lambda that must return ViewAction which will be performed
     */
    fun act(function: () -> ViewAction) {
        view.perform(function.invoke())
    }

    /**
     * Adds failure handler to the view
     *
     * @param function Lambda that handles this view's errors
     */
    fun onFailure(function: (error: Throwable, matcher: Matcher<View>) -> Unit) {
        view.withFailureHandler(function)
    }

    /**
     * Repeats given action on the view until this view will match the given matcher
     *
     * @param maxAttempts Maximum repeat count of the action
     * @param action Action to be performed
     * @param matcher ViewBuilder that will be used as matcher
     *
     * @see ViewActions.repeatedlyUntil
     */
    fun repeatUntil(maxAttempts: Int = 1, action: () -> ViewAction, matcher: ViewBuilder.() -> Unit) {
        view.perform(ViewActions.repeatedlyUntil(action(),
                ViewBuilder().apply(matcher).getViewMatcher(), maxAttempts))
    }
}