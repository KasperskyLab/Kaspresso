package com.kaspersky.kaspressample.configurator_tests.defaultaction_tests

import org.junit.Assert.assertTrue

object DefaultActionsChecker {

    private val checkList = mutableListOf<Char>()

    fun putBeforeFirst() {
        checkList.add('A')
    }

    fun putBeforeSecond() {
        checkList.add('B')
    }

    fun putAfterFirst() {
        checkList.add('C')
    }

    fun putAfterSecond() {
        checkList.add('D')
    }

    fun reset() {
        checkList.clear()
    }

    fun assertBefore() {
        assertTrue(checkList == "AB".toMutableList())
    }

    fun assertAfter() {
        assertTrue(checkList == "ABD".toMutableList())
    }
}