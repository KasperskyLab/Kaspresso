package com.kaspersky.kaspresso.flakysafety.scalpel

import com.google.common.truth.Truth.assertThat
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

class ScalpelSwitcherTest {

    @Test
    fun commonTest() {
        val scalpelSwitcher = ScalpelSwitcher()
        var takeScalpCount = 0

        scalpelSwitcher.attemptTakeScalp(
            actionToDetermineScalp = { false },
            actionToTakeScalp = { takeScalpCount++ }
        )
        assertThat(takeScalpCount).isEqualTo(0)

        scalpelSwitcher.attemptTakeScalp(
            actionToDetermineScalp = { true },
            actionToTakeScalp = { takeScalpCount++ }
        )
        scalpelSwitcher.attemptTakeScalp(
            actionToDetermineScalp = { true },
            actionToTakeScalp = { takeScalpCount++ }
        )
        assertThat(takeScalpCount).isEqualTo(1)

        scalpelSwitcher.attemptTakeScalp(
            actionToDetermineScalp = { false },
            actionToTakeScalp = { takeScalpCount++ }
        )
        assertThat(takeScalpCount).isEqualTo(1)

        scalpelSwitcher.attemptRestoreScalp {
            takeScalpCount--
        }
        scalpelSwitcher.attemptRestoreScalp {
            takeScalpCount--
        }
        assertThat(takeScalpCount).isEqualTo(0)

        scalpelSwitcher.attemptTakeScalp(
            actionToDetermineScalp = { false },
            actionToTakeScalp = { takeScalpCount++ }
        )
        assertThat(takeScalpCount).isEqualTo(0)

        scalpelSwitcher.attemptTakeScalp(
            actionToDetermineScalp = { true },
            actionToTakeScalp = { takeScalpCount++ }
        )
        assertThat(takeScalpCount).isEqualTo(1)

        scalpelSwitcher.attemptTakeScalp(
            actionToDetermineScalp = { true },
            actionToTakeScalp = { takeScalpCount++ }
        )
        assertThat(takeScalpCount).isEqualTo(1)

        scalpelSwitcher.attemptRestoreScalp {
            takeScalpCount--
        }
        assertThat(takeScalpCount).isEqualTo(0)

        scalpelSwitcher.attemptRestoreScalp {
            takeScalpCount--
        }
        assertThat(takeScalpCount).isEqualTo(0)

        scalpelSwitcher.attemptTakeScalp(
            actionToDetermineScalp = { false },
            actionToTakeScalp = { takeScalpCount++ }
        )
        assertThat(takeScalpCount).isEqualTo(0)
    }
}
