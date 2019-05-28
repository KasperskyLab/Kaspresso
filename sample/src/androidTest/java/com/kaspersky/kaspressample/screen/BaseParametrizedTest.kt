package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspresso.testcases.BaseTestCase


open class BaseParametrizedTest : BaseTestCase<TestCaseDsl, String>() {
    override fun provideMainDataProducer(): (TestCaseDsl.() -> Unit) -> String {
        return { action ->
            val testCaseDsl = TestCaseDsl().apply(action)
            testCaseDsl.list.joinToString(",")
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