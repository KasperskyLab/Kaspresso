package com.kaspersky.kaspresso.flakysafety.scalpel

import com.google.common.truth.Truth.assertThat
import org.junit.Test

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