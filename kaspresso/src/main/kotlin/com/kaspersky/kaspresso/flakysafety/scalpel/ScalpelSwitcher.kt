package com.kaspersky.kaspresso.flakysafety.scalpel

import com.kaspersky.kaspresso.flakysafety.scalpel.ScalpelSwitcher.TakeScalpState.START
import com.kaspersky.kaspresso.flakysafety.scalpel.ScalpelSwitcher.TakeScalpState.TOOK_AND_ABSENCE
import com.kaspersky.kaspresso.flakysafety.scalpel.ScalpelSwitcher.TakeScalpState.TOOK_AND_EXISTS

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

internal class ScalpelSwitcher {

    private var state: TakeScalpState = START

    fun attemptTakeScalp(
        actionToDetermineScalp: () -> Boolean,
        actionToTakeScalp: () -> Unit
    ) {
        if (state != START) {
            return
        }
        val isExist = actionToDetermineScalp.invoke()
        if (isExist) state = TOOK_AND_EXISTS else TOOK_AND_ABSENCE
        if (state == TOOK_AND_EXISTS) {
            actionToTakeScalp.invoke()
        }
    }

    fun attemptRestoreScalp(actionToRestoreScalp: () -> Unit) {
        if (state == TOOK_AND_EXISTS) {
            actionToRestoreScalp.invoke()
        }
        state = START
    }

    private enum class TakeScalpState {
        START,
        TOOK_AND_EXISTS,
        TOOK_AND_ABSENCE
    }
}
