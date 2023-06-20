package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import androidx.test.espresso.DataInteraction
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.SystemDialogsSafetyParams
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

/**
 * The implementation of [DataBehaviorInterceptor] and [SystemDialogSafetyProvider] interfaces.
 * Provides system dialog safety functionality for [DataInteraction.check] calls.
 */
class SystemDialogSafetyDataBehaviorInterceptor(
    logger: UiTestLogger,
    instrumentalDependencyProvider: InstrumentalDependencyProvider,
    adbServer: AdbServer,
    systemDialogsSafetyParams: SystemDialogsSafetyParams
) : DataBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, instrumentalDependencyProvider, adbServer, systemDialogsSafetyParams) {

    /**
     * Wraps the given [action] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [DataInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: DataInteraction, action: () -> T): T = passSystemDialogs(action)
}
