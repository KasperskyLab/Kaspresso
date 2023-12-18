package com.kaspersky.kaspresso.docloc.rule

import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ToggleNightModeRule internal constructor(
    private val device: Device,
    private val toggleNightMode: Boolean,
    private val logger: UiTestLogger
) : TestRule {

    var isNightMode: Boolean = false
        private set

    private fun setNightMode(@AppCompatDelegate.NightMode mode: Int) {
        Handler(device.targetContext.mainLooper).post {
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                logger.i("DocLoc: ToggleNightModeRule started")
                try {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    isNightMode = false
                    base.evaluate()
                    if (toggleNightMode) {
                        setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        isNightMode = true
                        base.evaluate()
                    }
                } finally {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
                    isNightMode = false
                }
            }
        }
    }
}
