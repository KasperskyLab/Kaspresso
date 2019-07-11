package com.kaspersky.kaspresso.kakao_interceptors

import com.kaspersky.kaspresso.configurator.Configurator

internal abstract class InteractionKakaoInterceptor(
    private val configurator: Configurator
) {

    protected fun <EspressoInteraction> execute(executable: () -> EspressoInteraction) {
        configurator.executingInterceptor
            ?.interceptAndExecute { executable.invoke() }
            ?: executable.invoke()
    }
}