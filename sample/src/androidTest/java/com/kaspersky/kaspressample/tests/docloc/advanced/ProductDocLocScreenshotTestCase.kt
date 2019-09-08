package com.kaspersky.kaspressample.tests.docloc.advanced

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspressample.docloc.FragmentTestActivity
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import java.io.File
import org.junit.Rule

open class ProductDocLocScreenshotTestCase : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,ru"
) {
    @get:Rule
    val activityTestRule = ActivityTestRule(FragmentTestActivity::class.java, false, true)

    protected val activity: FragmentTestActivity
        get() = activityTestRule.activity
}