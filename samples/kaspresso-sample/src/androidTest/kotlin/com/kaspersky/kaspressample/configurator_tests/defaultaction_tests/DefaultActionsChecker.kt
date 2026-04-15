package com.kaspersky.kaspressample.configurator_tests.defaultaction_tests

import org.junit.Assert.assertTrue

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
