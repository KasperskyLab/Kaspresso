package com.kaspersky.kaspresso.sample_upgrade_tests.common

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext

open class UpgradeTestCase : TestCase() {

    companion object {
        private const val MAIN_APP_PACKAGE_ID = "com.kaspersky.kaspressample"
        private const val OLD_VERSION_FILE = "artifacts/upgrade_test_v1.apk"
        private const val NEW_VERSION_FILE = "artifacts/upgrade_test_v2.apk"
    }

    fun BaseTestContext.installAndLaunchMainApp() {
        device.apps.install(OLD_VERSION_FILE)

        with(device.targetContext) {
            val intent = packageManager.getLaunchIntentForPackage(MAIN_APP_PACKAGE_ID)
            startActivity(intent)
        }

        Thread.sleep(5_000)
    }

    fun BaseTestContext.updateAndLaunchMainApp() {
        adbServer.performAdb("install -r $NEW_VERSION_FILE")

        with(device.targetContext) {
            val intent = packageManager.getLaunchIntentForPackage(MAIN_APP_PACKAGE_ID)
            startActivity(intent)
        }

        Thread.sleep(5_000)
    }

    fun BaseTestContext.uninstallMainApp() {
        device.apps.uninstallIfExists(MAIN_APP_PACKAGE_ID)
    }
}