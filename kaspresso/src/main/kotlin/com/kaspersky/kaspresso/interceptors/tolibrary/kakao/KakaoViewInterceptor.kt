package com.kaspersky.kaspresso.interceptors.tolibrary.kakao

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

/**
 * Kaspresso's implementation of Kakao's view interaction interceptor.
 */
internal class KakaoViewInterceptor(
    private val viewBehaviorInterceptors: List<ViewBehaviorInterceptor>,
    private val viewActionWatcherInterceptors: List<ViewActionWatcherInterceptor>,
    private val viewAssertionWatcherInterceptors: List<ViewAssertionWatcherInterceptor>
) : LibraryInterceptor<ViewInteraction, ViewAssertion, ViewAction>() {

    /**
     * Folds all [ViewBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.ViewInteraction.check] call as the initial, and invokes the resulting lambda.
     */
    override fun interceptCheck(interaction: ViewInteraction, assertion: ViewAssertion) {
        viewBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    ViewAssertionProxy(assertion, viewAssertionWatcherInterceptors)
                )
            },
            operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                { viewBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }

    /**
     * Folds all [ViewBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.ViewInteraction.perform] call as the initial, and invokes the resulting lambda.
     */
    override fun interceptPerform(interaction: ViewInteraction, action: ViewAction) {
        viewBehaviorInterceptors.fold(
            initial = {
                interaction.perform(
                    ViewActionProxy(action, viewActionWatcherInterceptors)
                )
            },
            operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                { viewBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }
}