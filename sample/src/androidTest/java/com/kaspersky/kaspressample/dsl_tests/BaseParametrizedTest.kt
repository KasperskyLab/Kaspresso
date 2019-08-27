package com.kaspersky.kaspressample.dsl_tests

import com.kaspersky.kaspressample.dsl_tests.data.TestCaseData
import com.kaspersky.kaspressample.dsl_tests.data.TestCaseDsl
import com.kaspersky.kaspressample.dsl_tests.data_producers.TestCaseDataProducer
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase

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