package com.kaspersky.kaspressample.configurator_tests.interceptor_tests.helpers

import org.junit.Assert.assertTrue

object CheckCustomInterceptorsStorage {

    private val viewActionInterceptorCheckList = mutableListOf<Char>()
    private val viewAssertionInterceptorCheckList = mutableListOf<Char>()
    private val stepInterceptorCheckList = mutableListOf<Char>()

    fun putToViewActionInterceptorCheckList() {
        viewActionInterceptorCheckList.add('A')
    }

    fun putToViewAssertionInterceptorCheckList() {
        viewAssertionInterceptorCheckList.add('B')
    }

    fun putToStepInterceptorCheckList() {
        stepInterceptorCheckList.add('C')
    }

    fun resetAllCheckLists() {
        viewActionInterceptorCheckList.clear()
        viewAssertionInterceptorCheckList.clear()
        stepInterceptorCheckList.clear()
    }

    fun assertAllCheckLists(actionsCount: Int, assertionsCount: Int, stepCount: Int) {
        assertTrue(viewActionInterceptorCheckList == "A".repeat(actionsCount).toMutableList())
        assertTrue(viewAssertionInterceptorCheckList == "B".repeat(assertionsCount).toMutableList())
        assertTrue(stepInterceptorCheckList == "C".repeat(stepCount).toMutableList())
    }
}
