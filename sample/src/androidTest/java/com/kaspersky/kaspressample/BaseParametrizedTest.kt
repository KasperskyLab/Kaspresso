package com.kaspersky.kaspressample

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.BaseTestCase


open class BaseParametrizedTest(configBuilder: Configurator.Builder = Configurator.Builder.default()) :
    BaseTestCase<TestCaseDsl, TestCaseData>(
        configBuilder = configBuilder,
        dataProducer = provideMainDataProducer()
    ) {

    companion object {
        private fun provideMainDataProducer(): (TestCaseDsl.() -> Unit) -> TestCaseData {
            return { action ->
                TestCaseDataProducer().initData(action)
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

interface ScenarioData

class TestCaseData(val list: MutableList<String> = mutableListOf()) : ScenarioData{
    fun addString(string: String) {
        list.add(string)
    }

}

class TestCaseDataProducer {

    /**
     * Init data f.e. with server and transform it to testcase abstractions
     */
    fun initData(action: TestCaseDsl.() -> Unit): TestCaseData {
        val testCaseDsl = TestCaseDsl().apply(action)

        //Place to init data

        return TestCaseData(mutableListOf(testCaseDsl.list.joinToString(",")))
    }
}