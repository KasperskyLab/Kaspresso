package com.kaspersky.kaspresso.flakysafety.scalpel

import com.kaspersky.kaspresso.flakysafety.scalpel.ScalpelSwitcher.TakeScalpState.START
import com.kaspersky.kaspresso.flakysafety.scalpel.ScalpelSwitcher.TakeScalpState.TOOK_AND_ABSENCE
import com.kaspersky.kaspresso.flakysafety.scalpel.ScalpelSwitcher.TakeScalpState.TOOK_AND_EXISTS

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