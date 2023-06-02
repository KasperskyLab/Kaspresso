package com.kaspersky.kaspresso.docloc.rule

import androidx.appcompat.app.AppCompatDelegate
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ThemeRule internal constructor(
    private val changeAppTheme: Boolean,
    private val logger: UiTestLogger
) : TestRule {


    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                logger.i("DocLoc: changeAppThemeRule started")
                try {
                    if (changeAppTheme) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        base.evaluate()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        base.evaluate()
                    }
                } finally {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }
    }
}
