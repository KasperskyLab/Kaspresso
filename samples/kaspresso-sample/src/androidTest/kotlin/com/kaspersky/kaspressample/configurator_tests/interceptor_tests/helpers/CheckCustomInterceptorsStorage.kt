package com.kaspersky.kaspressample.configurator_tests.interceptor_tests.helpers

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
