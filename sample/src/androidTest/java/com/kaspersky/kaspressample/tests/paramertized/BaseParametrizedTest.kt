package com.kaspersky.kaspressample.tests.paramertized

import com.kaspersky.kaspressample.data.TestCaseData
import com.kaspersky.kaspressample.data_producers.TestCaseDataProducer
import com.kaspersky.kaspressample.data.TestCaseDsl
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.api.BaseTestCase

open class BaseParametrizedTest(configBuilder: Configurator.Builder = Configurator.Builder.default()) :
    BaseTestCase<TestCaseDsl, TestCaseData>(
        configBuilder = configBuilder,
        dataProducer = provideMainDataProducer()
    ) {

    companion object {
        private fun provideMainDataProducer(): ((TestCaseDsl.() -> Unit)?) -> TestCaseData {
            return { action ->
                TestCaseDataProducer().initData(action)
            }
        }
    }

}