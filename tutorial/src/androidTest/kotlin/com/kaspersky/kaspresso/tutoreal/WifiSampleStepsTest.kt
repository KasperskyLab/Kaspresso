package com.kaspersky.kaspresso.tutoreal

import android.content.res.Configuration
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutoreal.screen.MainSceen
import com.kaspersky.kaspresso.tutoreal.screen.WifiScreen
import com.kaspersky.kaspresso.tutorial.MainActivity
import com.kaspersky.kaspresso.tutorial.R
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WifiSampleStepsTest: TestCase()  {

        @get:Rule
        val activityRule = activityScenarioRule<MainActivity>()

        @Test
        fun test(){

            before {
                device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
                device.network.toggleWiFi(true)

            }.after {
                device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
                device.network.toggleWiFi(true)

            }.run {

                step("Открытие главного экрана"){
                    MainSceen{
                        wifiActivityButton{
                            isVisible()
                            isClickable()
                            click()

                        }
                    }
                }

                step("Проверка WiFi статуса - портретная ориентация"){

                    WifiScreen{


                        checkWifiButton.isVisible()
                        checkWifiButton.isClickable()
                        wifiStatus.hasEmptyText()
                        checkWifiButton.click()
                        wifiStatus.hasText(R.string.enabled_status)
                        device.network.toggleWiFi(false)
                        checkWifiButton.click()
                        wifiStatus.hasText(R.string.disabled_status)



                    }
                }

                step("Проверка WiFi статуса - книжная ориентация"){


                    WifiScreen{
                        device.exploit.rotate()    //перевернуть телефон
                        Assert.assertTrue(device.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                        wifiStatus.hasText(R.string.disabled_status)
                    }

                }




            }








        }
}
