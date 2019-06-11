package com.kaspersky.kaspressample

import com.kaspersky.kaspressample.data.TestCaseData
import com.kaspersky.kaspressample.data.TestCaseDataProducer
import com.kaspersky.kaspressample.data.TestCaseDsl
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.api.base.BaseTestCase

open class BaseParametrizedTest(
    configBuilder: Configurator.Builder = Configurator.Builder.default()
) : BaseTestCase<TestCaseDsl, TestCaseData>(
    configBuilder = configBuilder,
    dataProducer = provideMainDataProducer()
) {
    companion object {
        private fun provideMainDataProducer(): ((TestCaseDsl.() -> Unit)?) -> TestCaseData {
            return { action -> TestCaseDataProducer().initData(action) }
        }
    }
}