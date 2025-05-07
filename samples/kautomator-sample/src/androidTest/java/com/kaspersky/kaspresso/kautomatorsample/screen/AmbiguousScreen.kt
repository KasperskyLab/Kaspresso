package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen
import java.util.regex.Pattern

private const val PATTERN = ".*Ambiguous button.*"

object AmbiguousScreen : UiScreen<AmbiguousScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val anyPossibleAmbiguousButtonByIdAndText = UiButton {
        withId(this@AmbiguousScreen.packageName, "ambiguous_button")
        withText(Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE))
        or {
            withId(this@AmbiguousScreen.packageName, "ambiguous_redesigned_button")
            withText(Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE))
        }
        or {
            withId(this@AmbiguousScreen.packageName, "ambiguous_new_button")
            withText(Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE))
        }
        or {
            withId(this@AmbiguousScreen.packageName, "ambiguous_vendor_button")
            withText(Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE))
        }
    }
}
