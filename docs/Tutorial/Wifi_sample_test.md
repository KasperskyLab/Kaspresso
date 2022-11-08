# WORK IN PROGRESS

<br> Сославшись на прошлый урок рассказать о device. Описать его возможности (https://kasperskylab.github.io/Kaspresso/Wiki/Working_with_Android_OS/)
<br> Рассказать, что часть команд под копотом использует adb, поэтому adbserver должен быть запущен (полезно сразу его запускать, даже если тест не связан с adb. Сказать, что мы планируем добавить автозапуск adbserver при старте теста
<br> Разобрать функционал примера с Wifi и код теста

```kotlin
package com.kaspersky.kaspresso

import android.content.ActivityNotFoundException
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.screen.MainScreen
import com.kaspersky.kaspresso.screen.WifiScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import com.kaspersky.kaspresso.tutorial.R
import org.junit.Rule
import org.junit.Test

class WifiSampleTest: TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        MainScreen {
            wifiActivityButton {
                isVisible()
                isClickable()
                click()
            }
        }
        WifiScreen {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.exploit.setAutoRotationEnabled(true)
            title.isVisible()
            btn.isClickable()
            title.hasEmptyText()
            tryToggleNetwork(false)
            btn.perform {
                click()
            }
            title.hasText(R.string.disabled_status)
            device.exploit.rotate()
            title.hasText(R.string.disabled_status)
        }
    }

    private fun tryToggleNetwork(shouldEnable: Boolean) {
        try {
            if (shouldEnable) {
                device.network.enable()
            } else {
                device.network.disable()
            }
        } catch (ex: ActivityNotFoundException) { // There's no WIFI activity on AVD with API < 25
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) return
            throw ex
        }
    }
}
```

<br> Разобрать этот код. 

<br> Закончить проблемами этого теста: тест стал довольно большим, написан сплошником без секций, после теста интернет будет выключен. Сказать, как это можно решить костыльно, сказать, что в следующем уроке будет приведено правильное решение
