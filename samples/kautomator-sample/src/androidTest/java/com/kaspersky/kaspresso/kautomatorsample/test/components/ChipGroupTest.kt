package com.kaspersky.kaspresso.kautomatorsample.test.components

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.ComponentsActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.ComponentsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
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

class ChipGroupTest : TestCase() {

    companion object {
        private const val CHIP_ID = "chip3"
        private const val CHIP_TEXT = "Chip 1"
        private const val CHIP_INDEX = 1
    }

    @get:Rule
    val activityRule = activityScenarioRule<ComponentsActivity>()

    @Test
    fun test() = run {
        step("Select chip with id") {
            ComponentsScreen {
                chipGroup {
                    isNotChipWithIdSelected(CHIP_ID)
                    selectChipWithId(CHIP_ID)
                    isChipWithIdSelected(CHIP_ID)
                }
            }
        }

        step("Select chip with text") {
            ComponentsScreen {
                chipGroup {
                    isNotChipWithTextSelected(CHIP_TEXT)
                    selectChipWithText(CHIP_TEXT)
                    isChipWithTextSelected(CHIP_TEXT)
                }
            }
        }

        step("Select chip with index") {
            ComponentsScreen {
                chipGroup {
                    isNotChipWithIndexSelected(CHIP_INDEX)
                    selectChipWithIndex(CHIP_INDEX)
                    isChipWithIndexSelected(CHIP_INDEX)
                }
            }
        }
    }
}
