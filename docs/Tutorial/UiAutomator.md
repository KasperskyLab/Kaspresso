# Work in progress


<br> Рассказать, что зачастую приходится тестировать функционал, который находится либо в другом приложении, либо системных диалогов и окон.

<br> Рассмотреть примеры

```kotlin
package com.kaspersky.kaspresso

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.screen.AlertDialogActivityScreen
import com.kaspersky.kaspresso.screen.AlertDialogScreen
import com.kaspersky.kaspresso.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import org.junit.Rule
import org.junit.Test

class AlertDialogTest: TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkTitleAndMessage() = run {

        step("Open target screen") {
            MainScreen {
                alertDialogActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }

        step("Open dialog") {
            AlertDialogActivityScreen {
                showDialogButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check title and message") {
            AlertDialogScreen {
                dialog {
                    title {
                        hasText("Title")
                    }

                    message {
                        hasText("Message")
                    }

                    positiveButton {
                        isClickable()
                        click()
                    }
                }
            }
        }
    }
}
```

```kotlin
package com.kaspersky.kaspresso

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.screen.GplayScreen
import org.junit.Test

class GplayTest: TestCase() {

    @Test
    fun testNotSignedIn() = run {
        step("forceStop Gplay application") {
            adbServer.performShell("am force-stop $GPLAY_PACKAGE")
            adbServer.performShell("pm clear $GPLAY_PACKAGE")
        }
        step("open Gplay app") {
            with(device.targetContext) {
                val intent = packageManager.getLaunchIntentForPackage(GPLAY_PACKAGE)
                startActivity(intent)
            }
            device.uiDevice.waitForIdle()
        }

        step("verify sign in needed") {
            GplayScreen {
                signInButton.isDisplayed()
            }
        }
    }

    companion object {
        private const val GPLAY_PACKAGE = "com.android.vending"
    }
}
```
<br> рассказать, что важно все равно выделять в Page object-ы структуру экрана, полезно было бы дать ссылку на различие KScreen и UiScreen https://kasperskylab.github.io/Kaspresso/Wiki/Page_object_in_Kaspresso/#what-is-the-difference-between-kscreen-and-uiscreen 
<br> рассказать особенности UiScreen, как искать элементы - порекомендовать отличные инструкменты поиска. Сказать, что можно использовать различные матчеры (по id, по тексту), рассказать о нестабильности поиска по id (на разных девайсах может быть по-разному, но это все еще лучше текста в каких-то случаях)

