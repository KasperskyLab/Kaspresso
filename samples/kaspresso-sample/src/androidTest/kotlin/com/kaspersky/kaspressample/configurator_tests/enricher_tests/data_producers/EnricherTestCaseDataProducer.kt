package com.kaspersky.kaspressample.configurator_tests.enricher_tests.data_producers

import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.EnricherTestData
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.Post
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.User
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.dsl.EnricherTestDsl

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

class EnricherTestCaseDataProducer {

    fun initData(action: (EnricherTestDsl.() -> Unit)?): EnricherTestData {
        val testCaseDsl = EnricherTestDsl().also { testCaseDsl -> action?.let { testCaseDsl.apply(it) } }

        val users = testCaseDsl.users.map { userDsl ->
            User(
                id = userDsl.id,
                name = userDsl.name
            )
        }
        val posts = testCaseDsl.users.map { it.posts }.flatten().map { postDsl ->
            Post(
                id = postDsl.id,
                userId = postDsl.userId,
                title = postDsl.title,
                body = postDsl.body
            )
        }

        return EnricherTestData(users, posts)
    }
}
