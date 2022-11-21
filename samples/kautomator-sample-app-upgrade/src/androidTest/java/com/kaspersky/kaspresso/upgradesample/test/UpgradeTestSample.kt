package com.kaspersky.kaspresso.upgradesample.test

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.upgradesample.common.UpdateManager.installAndLaunchMainApp
import com.kaspersky.kaspresso.upgradesample.common.UpdateManager.uninstallMainApp
import com.kaspersky.kaspresso.upgradesample.common.UpdateManager.updateAndLaunchMainApp
import com.kaspersky.kaspresso.upgradesample.screen.MainScreen
import com.kaspersky.kaspresso.upgradesample.screen.UpgradeScreen
import org.junit.Test

class UpgradeTestSample : TestCase() {

    companion object {
        private const val VERSION_TEXT_BEFORE_UPDATE = "v.1"
        private const val VERSION_TEXT_AFTER_UPDATE = "v.2"
        private const val VALUE = "Kaspresso"
    }

    @Test
    fun upgradeTest() {
        before {
            uninstallMainApp()
        }.after {
            uninstallMainApp()
        }.run {

            step("Install and launch old version of the app") {
                installAndLaunchMainApp()
            }

            step("Click on upgrade scenario button and check version") {
                MainScreen {
                    upgradeButton {
                        click()
                    }
                }
            }

            step("Check version name") {
                UpgradeScreen {
                    version {
                        hasText(VERSION_TEXT_BEFORE_UPDATE)
                    }
                }
            }

            step("Update text value") {
                UpgradeScreen {
                    input {
                        replaceText(VALUE)
                    }
                    applyBtn {
                        click()
                    }
                    value {
                        containsText(VALUE)
                    }
                }
            }

            step("Press Home button") {
                device.uiDevice.pressHome()
            }

            step("Upgrade the app and launch then") {
                updateAndLaunchMainApp()
            }

            step("Click on upgrade scenario button") {
                MainScreen {
                    upgradeButton {
                        click()
                    }
                }
            }

            step("Check if value has been restored after update") {
                UpgradeScreen {
                    version {
                        hasText(VERSION_TEXT_AFTER_UPDATE)
                    }
                    value {
                        containsText(VALUE)
                    }
                }
            }
        }
    }
}
