package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.flaky

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kautomatorsample.R
import com.kaspersky.kaspresso.kautomatorsample.flaky.FlakyActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.FlakyScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.common.utilities.getResourceString
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.lang.Error

class FlakyObjectDisabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        objectBehaviorInterceptors = mutableListOf()
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<FlakyActivity>()

    @Test
    fun test() = run {
        step("Click actions") {
            FlakyScreen {
                btn1 {
                    // error class cause of google.truth exceptions cant be accessible here
                    Assert.assertThrows(Error::class.java) {
                        hasText(getResourceString(R.string.menu_item_2))
                    }
                }
            }
        }
    }
}
