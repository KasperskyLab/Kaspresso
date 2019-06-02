package com.kaspersky.kaspressample.data

class TestCaseDataProducer {

    /**
     * Init data f.e. with server and transform it to testcase abstractions
     */
    fun initData(action: (TestCaseDsl.() -> Unit)?): TestCaseData {

        val testCaseDsl = TestCaseDsl().also { testCaseDsl -> action?.let { testCaseDsl.apply(it) } }

        //Init data at server side or by another way

        return TestCaseData(mutableListOf(testCaseDsl.list.joinToString(",")))
    }
}