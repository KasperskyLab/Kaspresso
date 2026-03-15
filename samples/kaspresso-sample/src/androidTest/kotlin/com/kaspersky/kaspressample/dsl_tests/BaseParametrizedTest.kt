package com.kaspersky.kaspressample.dsl_tests

import com.kaspersky.kaspressample.dsl_tests.data.TestCaseData
import com.kaspersky.kaspressample.dsl_tests.data.TestCaseDsl
import com.kaspersky.kaspressample.dsl_tests.data_producers.TestCaseDataProducer
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase

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

open class BaseParametrizedTest(
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.simple()
) : BaseTestCase<TestCaseDsl, TestCaseData>(
    kaspressoBuilder = kaspressoBuilder,
    dataProducer = provideMainDataProducer()
) {
    companion object {
        private fun provideMainDataProducer(): ((TestCaseDsl.() -> Unit)?) -> TestCaseData {
            return { action -> TestCaseDataProducer().initData(action) }
        }
    }
}
