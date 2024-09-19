package com.kaspersky.kaspresso.flakysafety.scalpel

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class FlakySafeInterceptorScalpelTest {

    private val kaspresso = mockk<Kaspresso>(relaxed = true)
    private val scalpelSwitcher = mockk<ScalpelSwitcher>(relaxed = true)
    private val scalpel = FlakySafeInterceptorScalpel(kaspresso, scalpelSwitcher)
    @Test
    fun `GIVEN nested flaky safety WHEN trying to scalp flaky safety interceptors THEN should not scalp interceptors`() {
        scalpel.scalpFromLibs()
        scalpel.scalpFromLibs()
        scalpel.scalpFromLibs()

        verify(exactly = 1) { scalpelSwitcher.attemptTakeScalp(any(), any()) }
    }

    @Test
    fun `GIVEN nested flaky safety WHEN trying to restore flaky safety interceptors THEN should not restore interceptors`() {
        scalpel.scalpFromLibs()
        scalpel.scalpFromLibs()
        scalpel.scalpFromLibs()

        scalpel.restoreScalpToLibs()
        scalpel.restoreScalpToLibs()

        verify(exactly = 0) { scalpelSwitcher.attemptRestoreScalp(any()) }

        scalpel.restoreScalpToLibs()
        verify { scalpelSwitcher.attemptRestoreScalp(any()) }
    }
}
