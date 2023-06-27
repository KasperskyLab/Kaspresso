package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety

import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.SystemDialogsSafetyParams
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

/**
 * The implementation of [DeviceBehaviorInterceptor] and [SystemDialogSafetyProvider] interfaces.
 * Provides system dialog safety functionality for [UiDeviceInteraction.perform] and [UiDeviceInteraction.check] calls.
 */
class SystemDialogSafetyDeviceBehaviorInterceptor(
    logger: UiTestLogger,
    instrumentalDependencyProvider: InstrumentalDependencyProvider,
    adbServer: AdbServer,
    systemDialogsSafetyParams: SystemDialogsSafetyParams
) : DeviceBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, instrumentalDependencyProvider, adbServer, systemDialogsSafetyParams) {

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [UiDeviceInteraction].
     * @param assertion the intercepted [UiDeviceAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiDeviceInteraction,
        assertion: UiDeviceAssertion,
        activity: () -> T
    ): T = passSystemDialogs(activity)

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [UiDeviceInteraction].
     * @param action the intercepted [UiDeviceAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiDeviceInteraction,
        action: UiDeviceAction,
        activity: () -> T
    ): T = passSystemDialogs(activity)
}
