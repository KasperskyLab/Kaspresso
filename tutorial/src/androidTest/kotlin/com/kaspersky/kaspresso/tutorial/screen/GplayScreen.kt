package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen
import java.util.regex.Pattern

object GplayScreen: UiScreen<GplayScreen>() {
    override val packageName: String = "com.android.vending"

    val signInButton get() = UiButton { withText(".*Sign in.*|.*Войти.*".toPattern(Pattern.CASE_INSENSITIVE)) }
}
