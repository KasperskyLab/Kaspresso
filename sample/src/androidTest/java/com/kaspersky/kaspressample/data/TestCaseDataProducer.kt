package com.kaspersky.kaspressample.data

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