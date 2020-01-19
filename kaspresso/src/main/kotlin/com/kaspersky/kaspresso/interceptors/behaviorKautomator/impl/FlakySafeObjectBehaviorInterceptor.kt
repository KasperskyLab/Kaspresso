package com.kaspersky.kaspresso.interceptors.behaviorKautomator.impl

import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.interceptors.behaviorKautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

class FlakySafeObjectBehaviorInterceptor(
    params: FlakySafetyParams,
    logger: UiTestLogger
) : ObjectBehaviorInterceptor,
    FlakySafetyProvider by FlakySafetyProviderImpl(params, logger) {

    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = flakySafely(action = activity)

    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = flakySafely(action = activity)
}