package com.kaspersky.kaspressample.configurator_tests.defaultaction_tests

import org.junit.Assert.assertTrue

object DefaultActionsChecker {

    private val checkList = mutableListOf<Char>()

    fun putBeforeFirst() {
        checkList.add('A')
    }

    fun putBeforeLast() {
        checkList.add('B')
    }

    fun putAfterFirst() {
        checkList.add('C')
    }

    fun putAfterLast() {
        checkList.add('D')
    }

    fun reset() {
        checkList.clear()
    }

    fun assertMain() {
        assertTrue(checkList == "AB".toMutableList())
    }

    fun assertAfter() {
        assertTrue(checkList == "ABC".toMutableList())
    }

    fun assertFullAfter() {
        assertTrue(checkList == "ABCD".toMutableList())
    }

}