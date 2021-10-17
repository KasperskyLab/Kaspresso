package com.kaspersky.kaspressample.sharedtest

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

const val ASSERT_PACKAGE_NAME = "com.kaspersky.kaspressample"
const val CACHE_DIR_NAME = "cache"

@RunWith(AndroidJUnit4::class)
class SharedInstrumentationTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SharedTestActivity>()

    @Test
    fun test() = run {
        step("Check Application context and its some functionality") {
            val packageName = device.context.packageName
            assertTrue("Original package name == $packageName, but the expected one must contain $ASSERT_PACKAGE_NAME", packageName.contains(ASSERT_PACKAGE_NAME))
        }

        step("Check FileSystem availability through targetContext") {
            val cacheDirName = device.targetContext.applicationContext.cacheDir.name
            assertEquals("Original cache directory name == $cacheDirName, but the expected one = $CACHE_DIR_NAME", CACHE_DIR_NAME, cacheDirName)
        }
    }
}
