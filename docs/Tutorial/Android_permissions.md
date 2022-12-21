# Тестирование приложений, требующих разрешений

В этом уроке мы научимся работать с разрешениями ([Permissions]( https://developer.android.com/guide/topics/permissions/overview)). 

Часто для корректной работы приложению нужен доступ к определенным функциям мобильного устройства: к камере, записи голоса, совершению звонков, отправке SMS-сообщений и т.д. Приложение может получить доступ к ним и использовать только в том случае, если пользователь даст на это разрешение.

На старых устройствах ниже шестой версии Android (API level 23) такие разрешения запрашивались в момент установки приложения и, если пользователь установил его, то считалось, что он согласен со всеми разрешениями, и приложение будет иметь возможность использовать все необходимые функции. Это было небезопасно, так как открывало возможность недобросовестным разработчикам незаметно для пользователя получать доступ к микрофону, камере, звонкам и другим важным компонентам и использовать в своих целях. 

По этой причине на более новых версиях так называемые «опасные» разрешения стали запрашиваться не в момент установки, а во время работы приложения. Теперь пользователь явно будет видеть диалог с предложением разрешить или отклонить запрос на использование какой-то функциональности.

Для примера запустите приложение `tutorial` на одной из последних версий Android (API 23 и выше) и нажмите кнопку `Make Call Activity` 

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

При тестировании приложений, которое требует разрешений, есть определенные особенности. Давайте напишем тест на данный экран.

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
Чтобы попасть на этот экран, нужно будет в `MainActivity` кликнуть по соответствующей кнопке, добавляем эту кнопку в `MainScreen` 

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

На данном этапе мы проверили работу нашего экрана, что есть возможность ввести номер и кликнуть на кнопку, но никак не проверили, происходит вызов по введенному номеру или нет. Для того чтобы проверить, происходит ли в данный момент вызов можно использовать `AudioManager`, делается это следующим образом:

```kotlin
val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
```
Можем добавить эту проверку отдельным шагом:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
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
        step("Check phone is calling") {
            val manager = device.context.getSystemService(AudioManager::class.java)
            Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
        }
    }
}
```

!!! info
     Перед запуском теста удалите приложение с устройства или отзовите разрешения при помощи adb shell команды. Также убедитесь, что вы запускаете тест на устройстве с API 23 и выше

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

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
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
        step("Check phone is calling") {
            val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
        }
    }
}

```

!!! info
      Перед запуском теста не забудьте отозвать все разрешения у приложения или удалить его с устройства.

Запускаем. В некоторых случаях этот тест будет пройдет успешно, а в некоторых – нет. Причину мы сейчас разберем.

## FlakySafely для assertions

Вспомните урок про метод `flakySafely`. Там мы говорили о том, что в случае неудачи все проверки в Kaspresso будут запускаться заново в течение определенного таймаута.

В нашем случае мы стартуем звонок и следующим шагом проверяем, что телефон действительно звонит. Делаем это мы через метод `Assert.assertTrue(…)`. Иногда устройство успевает осуществить набор номера до этой проверки, а иногда нет. Кажется, что в такой ситуации должен отрабатывать метод `flakySafely` и проверка должна быть проведена заново в течение десяти секунд, но почему-то этого не происходит.

Дело в том, что все проверки view-элементов в Kaspresso (isVisible, isClickable…) «под капотом» используют метод `flakySafely`, но если мы сами вызываем различные проверки через `assert`, то `flakySafely` использован не будет и, если проверка выполнится неудачно, то тест сразу завершится с ошибкой.

Такие случаи – это еще один пример, когда стоит явно вызывать `flakySafely`

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
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
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }
}

```
Сейчас тест работает, но в нем есть несколько проблем. 

Во-первых, после окончания теста на устройстве все еще продолжается вызов абонента. Давайте добавим секции `before` и `after` и в секции, которая выполняется после теста, завершим вызов. Это можно сделать при помощи следующего кода:  `device.phone.cancelCall("111")`. Работает этот метод посредством adb-команд, поэтому не забывайте запускать adb-сервер. 

Теоретически, вы могли бы сброс звонка вынести в отдельный step и запускать его последним шагом, не вынося в секцию after. Но это было бы плохим решением, поскольку в случае, если какой-то шаг завершится с ошибкой, и тест будет провален, то на устройстве будет продолжен вызов и никогда не сбросится. Преимущество секции after в том, что код внутри этого блока выполнится независимо от результата теста.

Чтобы не дублировать один и тот же номер в двух местах, давайте вынесем его в отдельную переменную, тогда код теста будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CALL_PHONE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
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
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }
}
```

Теперь после выполнения теста звонок завершается. 

Вторая проблема – при использовании `GrantPermissionRule` мы можем проверить приложение только в состоянии, когда пользователь дал разрешение. При этом есть вероятность, что разработчики не предусмотрели вариант, когда запрос разрешения был отклонен, тогда результат может быть неожиданным вплоть до того, что приложение будет крашиться. Необходимо проверять и такие сценарии, но использовать для этого `GrantPermissionRule` не получится, так как в этом случае разрешение всегда будет одобрено, и в тестах мы никогда не узнаем, какое будет поведение, если запрос отклонить.

## Тестирование при помощи Device.Permissions

Один из вариантов решения проблемы - взаимодействовать с диалогом при помощи KAutomator, предварительно найдя все необходимые элементы интерфейса, но это не слишком удобно, и в Kaspresso был добавлен намного более удобный способ - `Device.Permissions`. Он позволяет очень просто проверять диалоги разрешений, а также соглашаться с ними или отклонять. 

Поэтому вместо `Rule` мы будем использовать объект `Permissions`, который можно получить у `Device`. Давайте сделаем это в отдельном классе, чтобы у вас сохранились оба варианта тестов. Класс, в котором мы сейчас работаем, переименуем в `MakeCallActivityRuleTest`. 

Чтобы это сделать, кликните правой кнопкой на название файла и выберите `Refactor` -> `Rename`

 <img src="../images/permissions/rename.png" alt="Rename" />

И введите новое название класса:

<img src="../images/permissions/rename_2.png" alt="Rename" />

И создаем новый класс `MakeCallActivityDevicePermissionsTest`. Код можно скопировать из текущего теста, за исключением `GrantPermissionRule`

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {
    
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
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
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
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

Таким образом мы убедимся, что диалог отображается и дадим согласие на осуществление звонков. 

!!! info
     Напоминаем, что диалог будет показан на версии Android API 23 и выше, как выполнять эти тесты на более ранних версиях, мы разберем в конце этого урока

Тут мы дважды написали `device.permissions`, давайте немного сократим код, применив функцию [apply]( https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/apply.html). А также проверку через `assert` давайте вынесем в метод `flakySafely`. Тогда весь код теста будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
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
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Accept permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    allowViaDialog()
                }
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }
}

```

Запускаем. Тест пройден успешно.

Теперь мы можем с легкостью написать тест на то, что звонок не осуществляется, если разрешение дано не было. Для этого вместо `allowViaDialog` нужно указать `denyViaDialog`. 

Также нужно изменить проверки в самом тесте, и не забудьте в новом методе удалить код из функции `after`, так как после отклонения разрешения звонок осуществлен не будет, и после теста сбрасывать звонок больше не нужно.

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
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
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Accept permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    allowViaDialog()
                }
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
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
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Deny permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    denyViaDialog()
                }
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

Поэтому, если вы запускаете тест на устройствах с API ниже 23-ой версии, то никакого запроса разрешений не будет, соответственно проверка диалога не требуется. 

В тесте с использованием `GrantPermissionRule` никаких изменений не требуется, на старых версиях разрешение всегда есть, поэтому данная аннотация на работе теста никак не скажется. Но в тесте с использованием `device.permissions` изменения сделать необходимо, так как здесь мы явно проверяем работу диалога.

Вариантов здесь несколько. Во-первых, на таких устройствах нет смысла проверять работу приложения, если разрешение было отклонено, поэтому данный тест нужно просто пропускать. Для этого можно воспользоваться аннотацией `@SuppressSdk`. Тогда код метода `checkCallIfPermissionDenied` изменится на:

```kotlin
@SdkSuppress(minSdkVersion = 23)
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
            inputNumber.replaceText(testNumber)
            makeCallButton.click()
        }
    }
    step("Deny permission") {
        device.permissions.apply {
            flakySafely {
                Assert.assertTrue(isDialogVisible())
                denyViaDialog()
            }
        }
    }
    step("Check stay on the same screen") {
        MakeCallActivityScreen {
            inputNumber.isDisplayed()
            makeCallButton.isDisplayed()
        }
    }
}
```
Теперь данный тест будет выполняться только на новых версиях ОС Android, а на старых будет пропускаться.

Второй вариант решения проблемы – пропускать какие-то определенные шаги или заменять их другими в зависимости от уровня API. Например, в методе `checkSuccessCall` на старых девайсах мы можем пропустить шаг с проверкой диалога, для этого использовать такой код:

```kotlin
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    step("Accept permission") {
        device.permissions.apply {
            flakySafely {
                Assert.assertTrue(isDialogVisible())
                allowViaDialog()
            }
        }
    }
}
```
Остальную часть кода можно не трогать и тест будет успешно прогоняться, как на новых, так и на старых устройствах, просто в одном случае разрешение будет запрошено, в другом – нет.

Финальный код теста теперь будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.SdkSuppress
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
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
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            step("Accept permission") {
                device.permissions.apply {
                    flakySafely {
                        Assert.assertTrue(isDialogVisible())
                        allowViaDialog()
                    }
                }
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }

    @SdkSuppress(minSdkVersion = 23)
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
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Deny permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    denyViaDialog()
                }
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


## Итог

В этом уроке мы рассмотрели два варианта работы с Permissions: `GrantPermissionRule` и `device.permissions`. 

Также мы узнали, что второй вариант предпочтительнее по ряду причин:

<ol>
<li>Объект Permissions дает возможность проверять отображение диалога с запросом разрешения</li>
<li>При использовании Permissions мы можем проверить поведение приложения не только при принятии разрешения, но также и при его отклонении</li> 
<li>Тесты с применением GrantPermissionRule не будут работать, если разрешение было ранее отклонено. Потребуется переустановка приложения либо отмена выданных ранее разрешений через adb shell команду</li> 
<li>Если во время выполнения теста отозвать разрешение при помощи adb shell команды, то в случае использования объекта Permissions тест будет работать корректно, а в случае использования GrantPermissionRule произойдет краш</li> 
</ol>

