package com.kaspersky.kaspresso.interceptors

import com.kaspersky.kaspresso.configurator.Configurator

internal interface InteractionInterceptor<Interaction, Action, Assertion> {

    val configurator: Configurator

    fun interceptCheck(interaction: Interaction, assertion: Assertion)

    fun interceptPerform(interaction: Interaction, action: Action)

    fun <ExecutedInteraction> execute(executable: () -> ExecutedInteraction) {
        configurator.executingInterceptor
            ?.interceptAndExecute(executable)
            ?: executable.invoke()
    }
}