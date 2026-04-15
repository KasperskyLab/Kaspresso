package com.kaspersky.kaspressample.dsl_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import org.junit.Rule
import org.junit.Test

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

class InitTransformDataTest : BaseParametrizedTest() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = init {
        company {
            name = "Microsoft"
            city = "Redmond"
            country = "USA"
        }
        company {
            name = "Google"
            city = "Mountain View"
            country = "USA"
        }
        owner {
            firstName = "Satya"
            secondName = "Nadella"
            country = "India"
        }
        owner {
            firstName = "Sundar"
            secondName = "Pichai"
            country = "India"
        }
    }.transform {
        makeOwner(ownerSurname = "Nadella", companyName = "Microsoft")
        makeOwner(ownerSurname = "Pichai", companyName = "Google")
    }.run {
        step("Some test step") {
            testLogger.i(data.companies.toString())

            MainScreen {
                descriptionText {
                    hasNoText(data.owners.first().firstName ?: "")
                }

                simpleButton {
                    click()
                }
            }
        }
    }
}
