package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.BaseTestCase


open class BaseParametrizedTest(configBuilder: Configurator.Builder = Configurator.Builder.default()) :
    BaseTestCase<TestCaseDsl, MutableList<String>>(
        configBuilder = configBuilder,
        dataProducer = provideMainDataProducer()
    ) {

    companion object {
        private fun provideMainDataProducer(): (TestCaseDsl.() -> Unit) -> MutableList<String> {
            return { action ->
                val testCaseDsl = TestCaseDsl().apply(action)
                mutableListOf(testCaseDsl.list.joinToString(","))
            }
        }
    }
}

@DslMarker
annotation class TestCaseDslMarker

@TestCaseDslMarker
class TestCaseDsl {
    val list: MutableList<Int> = mutableListOf()

    fun rawData(rawData: Int) {
        list.add(rawData)
    }
}