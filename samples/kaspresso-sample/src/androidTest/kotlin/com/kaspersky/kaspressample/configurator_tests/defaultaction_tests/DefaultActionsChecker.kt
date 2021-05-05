package com.kaspersky.kaspressample.configurator_tests.defaultaction_tests

import org.junit.Assert.assertTrue

object DefaultActionsChecker {

    private val checkList = mutableListOf<Char>()

    fun putBeforeInParentTestCase() {
        checkList.add('A')
    }

    fun putBeforeInTestCase() {
        checkList.add('B')
    }

    fun putBeforeInBeforeSection() {
        checkList.add('C')
    }

    fun putAfterInParentTestCase() {
        checkList.add('D')
    }

    fun putAfterInTestCase() {
        checkList.add('E')
    }

    fun putAfterInAfterSection() {
        checkList.add('F')
    }

    fun reset() {
        checkList.clear()
    }

    fun assertBefore() {
        // A - first => calls  in parent TestCase
        // B - second => calls in TestCase's constructor in beforeEachTest
        // C - third => calls in beforeTest section
        assertTrue(checkList == "ABC".toMutableList())
    }

    fun assertAfter() {
        // A - first => calls  in parent TestCase
        // B - second => calls in TestCase's constructor in beforeEachTest
        // C - third => calls in beforeTest section
        // F - fourth => calls in afterTest section
        // E - fifth => calls in TestCase's constructor in afterEachTest
        // D - NO => because in TestCase's constructor we call afterEachTest method with override=true
        assertTrue(checkList == "ABCFE".toMutableList())
    }
}
