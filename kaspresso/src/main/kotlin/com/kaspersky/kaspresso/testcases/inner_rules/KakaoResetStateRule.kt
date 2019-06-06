package com.kaspersky.kaspresso.testcases.inner_rules

import com.kaspersky.klkakao.configurator.KakaoConfigurator
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

internal class KakaoResetStateRule : TestRule {

    override fun apply(base: Statement?, description: Description?)= object : Statement() {
        override fun evaluate() {
            requireNotNull(base)
            with(KakaoConfigurator) {
                initViewInteractionDelegateFactory(null)
                initDataInteractionDelegateFactory(null)
                initWebInteractionDelegateFactory(null)
            }
        }
    }

}