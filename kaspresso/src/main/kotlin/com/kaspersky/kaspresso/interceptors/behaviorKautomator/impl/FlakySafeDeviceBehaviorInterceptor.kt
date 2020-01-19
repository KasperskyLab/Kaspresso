package com.kaspersky.kaspresso.interceptors.behaviorKautomator.impl

import com.kaspersky.components.kautomator.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAssertion
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.interceptors.behaviorKautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

class FlakySafeDeviceBehaviorInterceptor(
    params: FlakySafetyParams,
    logger: UiTestLogger
) : DeviceBehaviorInterceptor,
    FlakySafetyProvider by FlakySafetyProviderImpl(params, logger) {

    override fun <T> interceptCheck(
        interaction: UiDeviceInteraction,
        assertion: UiDeviceAssertion,
        activity: () -> T
    ): T = flakySafely(action = activity)

    override fun <T> interceptPerform(
        interaction: UiDeviceInteraction,
        action: UiDeviceAction,
        activity: () -> T
    ): T = flakySafely(action = activity)
}