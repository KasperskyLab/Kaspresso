package com.kaspersky.kaspressample.dsl_tests.data_producers

import com.kaspersky.kaspressample.dsl_tests.data.TestCaseData
import com.kaspersky.kaspressample.dsl_tests.data.TestCaseDsl

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

class TestCaseDataProducer {

    /**
     * Init data f.e. with a server and transform it to testcase abstractions
     */
    fun initData(action: (TestCaseDsl.() -> Unit)?): TestCaseData {
        val testCaseDsl = TestCaseDsl().also { testCaseDsl -> action?.let { testCaseDsl.apply(it) } }
        // Init data at server side or by another way
        return TestCaseData(testCaseDsl.companies, testCaseDsl.owners)
    }
}
