package com.kaspersky.components.composesupport.interceptors.behavior.impl.systemsafety

import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.SystemDialogsSafetyParams
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * The implementation of [SemanticsBehaviorInterceptor] and [SystemDialogSafetyProvider] interfaces.
 * Provides system dialog safety functionality for [ComposeInteraction.perform] and [ComposeInteraction.check] calls.
 */
class SystemDialogSafetySemanticsBehaviorInterceptor(
    logger: UiTestLogger,
    instrumentalDependencyProvider: InstrumentalDependencyProvider,
    adbServer: AdbServer,
    systemDialogsSafetyParams: SystemDialogsSafetyParams
) : SemanticsBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, instrumentalDependencyProvider, adbServer, systemDialogsSafetyParams) {

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param assertion the intercepted [ComposeAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: ComposeInteraction,
        assertion: ComposeAssertion,
        activity: () -> T
    ): T = passSystemDialogs(activity)

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param action the intercepted [ComposeAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: ComposeInteraction,
        action: ComposeAction,
        activity: () -> T
    ): T = passSystemDialogs(activity)
}
