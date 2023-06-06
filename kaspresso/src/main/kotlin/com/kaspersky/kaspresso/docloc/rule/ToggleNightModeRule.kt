package com.kaspersky.kaspresso.docloc.rule

import androidx.appcompat.app.AppCompatDelegate
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ToggleNightModeRule internal constructor(
    private val toggleNightMode: Boolean,
    private val logger: UiTestLogger
) : TestRule {

    var isNightMode: Boolean = false
        private set

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                logger.i("DocLoc: ToggleNightModeRule started")
                try {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    isNightMode = false
                    base.evaluate()
                    if (toggleNightMode) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        isNightMode = true
                        base.evaluate()
                    }
                } finally {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
                    isNightMode = false
                }
            }
        }
    }
}
