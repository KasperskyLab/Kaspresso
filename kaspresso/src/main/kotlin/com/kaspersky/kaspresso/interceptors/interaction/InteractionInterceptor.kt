package com.kaspersky.kaspresso.interceptors.interaction

import com.kaspersky.kaspresso.configurator.Configurator

abstract class InteractionInterceptor<Interaction, Action, Assertion>(
    val configurator: Configurator
) {
    abstract fun interceptCheck(interaction: Interaction, assertion: Assertion)

    abstract fun interceptPerform(interaction: Interaction, action: Action)

    protected fun <ExecutedInteraction> execute(executable: () -> ExecutedInteraction) {
        configurator.executingInterceptor
            ?.interceptAndExecute(executable)
            ?: executable.invoke()
    }
}