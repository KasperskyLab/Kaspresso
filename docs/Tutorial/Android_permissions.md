# Тестирование приложений, требующих разрешений

В этом уроке мы научимся работать с разрешениями ([Permissions]( https://developer.android.com/guide/topics/permissions/overview)). 

Часто для корректной работы приложения нужен доступ к определенным функциям мобильного устройства: к камере, записи голоса, совершению звонков, отправке SMS-сообщений и т.д. Приложение может получить доступ к ним и использовать только в том случае, если пользователь даст на это разрешение.

Для примера откройте приложение `tutorial` и нажмите кнопку `Make Call Activity` 

<img src="../images/permissions/main_screen.png" alt="Main Screen" width="300"/>

У вас откроется экран, на котором есть два элемента – поле ввода и кнопка. В поле ввода можно указать какой-то номер телефона и кликнуть на кнопку `Make Call` для осуществления вызова

<img src="../images/permissions/make_call_screen.png" alt="Make call screen" width="300"/>

Совершение звонков – одна из функций, для работы которой требуется запросить разрешение у пользователя. Поэтому у вас отобразится диалог с предложением позволить приложению управлять звонками, на котором есть кнопки «Разрешить» и «Отклонить»

<img src="../images/permissions/request_permission_1.png" alt="Request permissions" width="300"/>

Если мы нажмем “Allow”, то начнется вызов абонента по тому номеру, который вы указали в поле ввода

<img src="../images/permissions/call_1.png" alt="Calling" width="300"/>

При следующем открытии приложения разрешение больше не будет запрашиваться, оно сохраняется на устройстве. Если вы хотите отозвать разрешение, то можно это сделать в настройках. Для этого перейдите в раздел приложения, найдите нужное вам и заходите в раздел `Permissions`

<img src="../images/permissions/deny_permission_settings.png" alt="Deny permission" width="300"/>

Здесь вы сможете зайти в любое разрешение и изменить значение с `Allow` на `Deny` или наоборот.

Второй способ, как это можно сделать – при помощи adb shell команды: 

`adb shell pm revoke package_name permission_name`

Для нашего приложения команда будет выглядеть так:

`adb shell pm revoke com.kaspersky.kaspresso.tutorial android.permission.CALL_PHONE`

После выполнения команды приложение снова запросит разрешение при следующей попытке совершить звонок.

## Создаем тест

При тестировании приложений, которое требует разрешений, есть опредtленные особенности. Давайте напишем тест на данный экран.

Первым делом создадим Page Object экрана с кнопкой `Make Call`

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

object MakeCallActivityScreen : KScreen<MakeCallActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val inputNumber = KEditText { withId(R.id.input_number) }
    val makeCallButton = KButton { withId(R.id.make_call_btn) }
}
```
Чтобы попасть на этот экран, нужно будет в MainActivity кликнуть по соответствующей кнопке, добавляем эту кнопку в `MainScreen` 

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleActivityButton = KButton { withId(R.id.simple_activity_btn) }
    val wifiActivityButton = KButton { withId(R.id.wifi_activity_btn) }
    val loginActivityButton = KButton { withId(R.id.login_activity_btn) }
    val notificationActivityButton = KButton { withId(R.id.notification_activity_btn) }
    val makeCallActivityButton = KButton { withId(R.id.make_call_activity_btn) }
}
```

Можем создавать тест. Давайте пока просто откроем экран совершения звонка, введем какой-то номер и кликнем по кнопке

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
    }
}
```

Запускаем тест. Тест пройден успешно.

В зависимости от того, дали вы разрешение или нет, у вас может отобразиться диалог с запросом разрешения на совершение звонков.

На данном этапе мы проверили работу нашего экрана, что есть возможность ввести номер и кликнуть на кнопку, но никак не проверили, происходит вызов по введенному номеру или нет. Для этой проверки нам понадобится взаимодействовать уже с другим приложением, через которое осуществляется звонок. 

При помощи Ui Automator Viewer (или какого-то другого инструмента) найдем `packageName` и `id` интересующих нас элементов: TextView с введенным номером и кнопка заверешения вызова:

<img src="../images/permissions/uiautomator_1.png" alt="Ui automator 1"/>

<img src="../images/permissions/uiautomator_2.png" alt="Ui automator 2"/>

Можем создавать Page Object экрана звонка. Так как этот экран не относится к нашему приложению, то используем библиотеку Kautomator

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.common.views.UiView
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object PhoneCallScreen : UiScreen<PhoneCallScreen>() {

    override val packageName: String = "com.android.dialer"

    val contactName = UiTextView {
        withId(
            packageName = this@PhoneCallScreen.packageName,
            resourceId = "contactgrid_contact_name"
        )
    }

    val endCallButton = UiView {
        withId(
            packageName = this@PhoneCallScreen.packageName,
            resourceId = "incall_end_call"
        )
    }
}
```

В созданный нами тест можем добавлять проверку, что в TextView указан тот же номер, что мы вводили на нашем экране.

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.PhoneCallScreen
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Check call screen") {
            PhoneCallScreen {
                contactName.isDisplayed()
                contactName.hasText("111")
                endCallButton.isDisplayed()
                endCallButton.isClickable()
            }
        }
    }
}
```

!!! info
     Перед запуском теста отзовите разрешения при помощи adb shell команды или просто удалите приложение с устройства. Также убедитесь, что вы запускаете тест на устройстве с API 23 и выше

Запускаем тест. Тест провален.

Это произошло, потому что после клика по кнопке у пользователя было запрошено разрешение. Никто этого разрешения не дал, и следующий экран открыт не был. 

## Тестирование при помощи TestRule

Есть несколько вариантов решения проблемы. Первый вариант – использовать `GrantPermissionRule`. Суть этого способа заключается в том, что мы создаем список разрешений, которые будут автоматически разрешены на тестируемом устройстве.

Для этого перед тестовым методом мы добавляем новое правило:

```kotlin
@get:Rule
val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
    android.Manifest.permission.CALL_PHONE
)
```

В методе `grant` в круглых скобках мы через запятую перечисляем все требуемые разрешения, в данном случае оно всего одно, поэтому оставляем в таком виде. Тогда весь код теста будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.PhoneCallScreen
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CALL_PHONE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Check call screen") {
            PhoneCallScreen {
                contactName.isDisplayed()
                contactName.hasText("111")
                endCallButton.isDisplayed()
                endCallButton.isClickable()
            }
        }
    }
}
```

Перед запуском теста не забудьте отозвать все разрешения у приложения или удалить его с устройства.

Запускаем. Тест пройден успешно, но с ним есть определенные проблемы.

Во-первых, после окончания теста на устройстве все еще продолжается вызов абонента. Давайте добавим секции `before` и `after` и в секции, которая выполняется после теста, завершим вызов.

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.PhoneCallScreen
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CALL_PHONE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = before {

    }.after {
        PhoneCallScreen {
            endCallButton.click()
        }
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Check call screen") {
            PhoneCallScreen {
                contactName.isDisplayed()
                contactName.hasText("111")
                endCallButton.isDisplayed()
                endCallButton.isClickable()
            }
        }
    }
}
```

Теперь после выполнения теста звонок завершается. 

Вторая проблема – при использовании `GrantPermissionRule` мы можем проверить приложение только в состоянии, когда пользователь дал разрешение. При этом есть вероятность, что разработчики не предусмотрели вариант, когда запрос разрешения был отклонен, тогда результат может быть неожиданным вплоть до того, что приложение будет крашиться. Необходимо проверять и такие сценарии, но использовать для этого `GrantPermissionRule` не получится, так как в этом случае разрешение всегда будет одобрено, и в тестах мы никогда не узнаем, какое будет поведение, если запрос отклонить.


## Тестирование при помощи Device.Permissions

Для таких сценариев вместо `Rule` мы будем использовать объект `Permissions`, который можно получить у `Device`. Давайте сделаем это в отдельном классе, чтобы у вас сохранились оба варианта тестов. Класс, в котором мы сейчас работаем, переименуем в `MakeCallActivityRuleTest`. И создаем новый класс `MakeCallActivityDevicePermissionsTest`. Код можно скопировать из текущего теста, за исключением `GrantPermissionRule`

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.PhoneCallScreen
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = before {

    }.after {
        PhoneCallScreen {
            endCallButton.click()
        }
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Check call screen") {
            PhoneCallScreen {
                contactName.isDisplayed()
                contactName.hasText("111")
                endCallButton.isDisplayed()
                endCallButton.isClickable()
            }
        }
    }
}
```

Если мы запустим тест сейчас, то он завершится неудачно, т.к. мы не дали разрешений на совершение звонков. Давайте добавим еще один step, в котором дадим соответствующее разрешение через `device.permissions`. После указания объекта можно поставить точку и посмотреть, какие у него есть методы:

<img src="../images/permissions/device_perm_methods.png" alt="Device permission methods"/>

Есть возможность проверить, отображается ли диалог, а также отклонить или дать разрешение.

```kotlin
step("Accept permission") {
    Assert.assertTrue(device.permissions.isDialogVisible())
    device.permissions.allowViaDialog()
}
```

Таким образом мы убедимся, что диалог отображается и дадим согласие на осуществление звонков. Тут мы дважды написали `device.permissions`, давайте немного сократим код, применив функцию `apply`. Тогда весь код тесты будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.PhoneCallScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = before {

    }.after {
        PhoneCallScreen {
            endCallButton.click()
        }
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Accept permission") {
            device.permissions.apply {
                Assert.assertTrue(isDialogVisible())
                allowViaDialog()
            }
        }
        step("Check call screen") {
            PhoneCallScreen {
                contactName.isDisplayed()
                contactName.hasText("111")
                endCallButton.isDisplayed()
                endCallButton.isClickable()
            }
        }
    }
}
```

Запускаем. Тест пройден успешно.

Теперь мы можем с легкостью написать тест на то, что звонок не осуществляется, если разрешение дано не было. Для этого вместо `allowViaDialog` нужно указать `denyViaDialog`. 

Также нужно изменить проверки в самом тесте, и не забудьте в новом методе удалить код из функции `after`, так как после отклонения разрешения звонок осуществлен не будет, и после теста кликать на кнопку сброса звонка больше не нужно.

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.PhoneCallScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = before {
    }.after {
        PhoneCallScreen {
            endCallButton.click()
        }
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Accept permission") {
            device.permissions.apply {
                Assert.assertTrue(isDialogVisible())
                allowViaDialog()
            }
        }
        step("Check call screen") {
            PhoneCallScreen {
                contactName.isDisplayed()
                contactName.hasText("111")
                endCallButton.isDisplayed()
                endCallButton.isClickable()
            }
        }
    }

    @Test
    fun checkCallIfPermissionDenied() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Deny permission") {
            device.permissions.apply {
                Assert.assertTrue(isDialogVisible())
                denyViaDialog()
            }
        }
        step("Check stay on the same screen") {
            MakeCallActivityScreen {
                inputNumber.isDisplayed()
                makeCallButton.isDisplayed()
            }
        }
    }
}
```


## Тестирование на разных версиях API

На современных версиях ОС Android (API 23 и выше) разрешения у пользователя запрашиваются во время работы приложения посредством диалога. Но в более ранних версиях они запрашивались в момент установки приложения, а во время работы считалось, что пользователь согласился со всеми требуемыми разрешениями.

Поэтому, если вы запускаете тест на устройствах с API ниже 23-ой версии, то никакого запроса разрешений не будет, соответственно проверка диалога и установка `GrantPermissionRule` не требуется. 

В таких случаях можно воспользоваться функцией `assumeTrue`. В качестве переметра мы передаем какое-то выражение, которое возвращает `true` или `false`. Если это выражение вернет `true`, то тест продолжит свое выполнение, если же оно вернет `false`, то тест завершит свою работу успешно и дальнейшие шаги выполняться не будут.

Мы можем указать вот такой код перед каждым тестом:

`Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)`

Тогда на устройствах ниже 23-ей версии тесты выполняться не будут, но при этом останутся «зелеными». Весь код теста будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.PhoneCallScreen
import org.junit.Assert
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() {
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        before {
        }.after {
            PhoneCallScreen {
                endCallButton.click()
            }
        }.run {
            step("Open make call activity") {
                MainScreen {
                    makeCallActivityButton {
                        isVisible()
                        isClickable()
                        click()
                    }
                }
            }
            step("Check UI elements") {
                MakeCallActivityScreen {
                    inputNumber.isVisible()
                    inputNumber.hasHint(R.string.phone_number_hint)
                    makeCallButton.isVisible()
                    makeCallButton.isClickable()
                    makeCallButton.hasText(R.string.make_call_btn)
                }
            }
            step("Try to call number") {
                MakeCallActivityScreen {
                    inputNumber.replaceText("111")
                    makeCallButton.click()
                }
            }
            step("Accept permission") {
                device.permissions.apply {
                    Assert.assertTrue(isDialogVisible())
                    allowViaDialog()
                }
            }
            step("Check call screen") {
                PhoneCallScreen {
                    contactName.isDisplayed()
                    contactName.hasText("111")
                    endCallButton.isDisplayed()
                    endCallButton.isClickable()
                }
            }
        }
    }

    @Test
    fun checkCallIfPermissionDenied() {
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        run {
            step("Open make call activity") {
                MainScreen {
                    makeCallActivityButton {
                        isVisible()
                        isClickable()
                        click()
                    }
                }
            }
            step("Check UI elements") {
                MakeCallActivityScreen {
                    inputNumber.isVisible()
                    inputNumber.hasHint(R.string.phone_number_hint)
                    makeCallButton.isVisible()
                    makeCallButton.isClickable()
                    makeCallButton.hasText(R.string.make_call_btn)
                }
            }
            step("Try to call number") {
                MakeCallActivityScreen {
                    inputNumber.replaceText("111")
                    makeCallButton.click()
                }
            }
            step("Deny permission") {
                device.permissions.apply {
                    Assert.assertTrue(isDialogVisible())
                    denyViaDialog()
                }
            }
            step("Check stay on the same screen") {
                MakeCallActivityScreen {
                    inputNumber.isDisplayed()
                    makeCallButton.isDisplayed()
                }
            }
        }
    }
}
```


## Итог

В этом уроке мы рассмотрели два варианта работы с Permissions: `GrantPermissionRule` и `device.permissions`. 

Также мы узнали, что второй вариант предпочтительнее по ряду причин:

<ol>
<li>Объект Permissions дает возможность проверять отображение диалога с запросом разрешения</li>
<li>При использовании Permissions мы можем проверить поведение приложения не только при принятии разрешения, но также и при его отклонении</li> 
<li>Тесты с применением GrantPermissionRule не будут работать, если разрешение было ранее отклонено. Потребуется переустановка приложения либо отмена выданных ранее разрешений через adb shell команду</li> 
<li>Если во время выполнения теста отозвать разрешение при помощи adb shell команды, то в случае использования объекта Permissions тест будет работать корректно, а в случае использования GrantPermissionRule произойдет краш</li> 
</ol>




 






