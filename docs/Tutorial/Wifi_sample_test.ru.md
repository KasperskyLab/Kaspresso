# Тестирование интернет-соединения и работа с классом Device

В этом уроке мы создадим тест, который проверяет работу экрана Internet Availability (`WifiActivity`)

Запускаем наше приложение tutorial и кликаем по кнопке `Internet Availability`.

<img src="../images/wifi_test/internet_availability_button.png" alt="Button Internet Availability" width="300"/>

## Ручное тестирование

Давайте сначала протестируем этот экран руками.

Изначально у нас есть кнопка `CHECK WIFI STATUS`, больше никакого текста на экране нет. На текущий момент Wifi на устройстве включен.

<img src="../images/wifi_test/first_launch_1.png" alt="Launch Wifi Test Activity" width="300"/>

<img src="../images/wifi_test/first_launch_2.png" alt="Launch Wifi Test Activity" width="300"/>

Кликаем на кнопку.

<img src="../images/wifi_test/wifi_enabled.png" alt="Wifi enabled" width="300"/>

Эта кнопка кликабельна, после клика отображается корректный статус состояния Wifi - enabled. Отключаем Wifi.

<img src="../images/wifi_test/turn_off_wifi.png" alt="Turn-off wifi" width="300"/>

Кликаем на кнопку снова и проверяем статус Wifi сейчас:

<img src="../images/wifi_test/wifi_disabled.png" alt="Wifi disabled" width="300"/>

Состояние определяется корректно. Последняя проверка - давайте перевернем устройство и убедимся, что текст на экране сохраняется.

<img src="../images/wifi_test/wifi_disabled_portrait.png" alt="Wifi disabled landscape"/>

Текст сохраняется успешно, все тесты пройдены. Теперь нам необходимо добиться такого результата, чтобы все те же проверки выполнялись в автоматическом режиме. 

## Написание автотестов

Сейчас во время теста нужно будет автоматически включать и выключать интернет, а также менять ориентацию устройства на альбомную. Это выходит за рамки ответственности нашего приложения, а значит, для тестов нам придется использовать adb-команды. Для этого необходимо, чтобы был запущен ADB-сервер. Мы разбирали этот момент в [предыдущем уроке](https://kasperskylab.github.io/Kaspresso/Tutorial/%D0%92%D1%8B%D0%BF%D0%BE%D0%BB%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5%20adb-%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4/). Если вдруг забыли, как это делается, пересмотрите его.

Сейчас в нашем тесте нужно будет на главном экране кликнуть по кнопке `Internet Availability`. А значит, необходимо доработать Page Object главного экрана, добавив туда еще одну кнопку:

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
}
```

Теперь можем добавлять новый тестовый класс. В том же пакете, где у нас лежат другие тесты, мы добавляем WifiSampleTest.

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

class WifiSampleTest: TestCase() {
    
}
```

Для проверки экрана с доступностью Internet на него нужно перейти. Для этого мы проделаем такие же шаги, как в [уроке](https://kasperskylab.github.io/Kaspresso/Tutorial/Writing_simple_test/), в котором писали наш первый автотест:

<ol>
    <li>Добавим activityRule, чтобы при запуске теста у нас открывалась MainActivity</li>
    <li>Проверим, что кнопка для перехода на экран провеки Internet видима и кликабельна</li>
    <li>Кликнем по кнопке "Internet Availability"</li>
</ol>

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleTest : TestCase() {

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
    }
}
```

Запускаем. Тест пройден успешно и экран проверки Wifi запускается. Теперь можем тестировать его.

Для полноценного тестирования этого экрана нам понадобится менять состояние подключения к Wifi, а также менять ориентацию устройства. Для этого в классе `BaseTestCase` (от которого унаследован наш класс `WifiSampleTest`) есть экземпляр класса `Device`, который так и называется `device`. Мы уже сталкивались с ним в предыдущем уроке, когда получали packageName нашего приложения.

У этого объекта есть множество полезных методов, подробно про которые вы можете почитать [тут](https://kasperskylab.github.io/Kaspresso/Wiki/Working_with_Android_OS/).

Первым делом нас интересует метод, который включает/отключает интернет. За работу с сетью отвечает объект `network`, который есть в классе `Device`.

Если мы хотим изменить состояние Wifi, то можем это сделать следующим образом:

```kotlin
/**
* В качестве параметра передаем тип boolean, false если хотим выключить Wifi, true - если хотим включить
*/
device.network.toggleWiFi(false)
```

Кроме Wifi мы можем также управлять мобильной сетью, а также интернет-подключением на устройстве в целом (Wifi + мобильная сеть). Для того чтобы посмотреть все доступные методы, можно перейти в документацию, указанную выше, но есть способ проще - после названия объекта поставить точку и посмотреть, какие методы можно вызвать у этого объекта. По их названию обычно понятно, что они делают.

<img src="../images/wifi_test/available_methods.png" alt="Available methods"/>

Давайте напишем тест, который выполнит все необходимые проверки, кроме переворота устройства - переворотом мы займемся чуть позже. Первым делом нужно создать Page Object экрана проверки интернет-подключения `WifiScreen`. Добавляем его в пакете `com.kaspersky.kaspresso.tutorial.screen`.

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object WifiScreen : KScreen<WifiScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val checkWifiButton = KButton { withId(R.id.check_wifi_btn) }
    val wifiStatus = KTextView { withId(R.id.wifi_status) }
}
```

Теперь добавляем шаги:

<ol>
    <li>Проверяем, что кнопка видима и кликабельна</li>
    <li>Проверяем, что заголовок не содержит текст</li>
    <li>Кликаем по кнопке</li>
    <li>Проверяем, что текст в заголовке стал "enabled"</li>
    <li>Отключаем Wifi</li>
    <li>Кликаем по кнопке</li>
    <li>Проверяем, что текст в заголовке стал "disabled"</li>
</ol>

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleTest : TestCase() {

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
            checkWifiButton.isVisible()
            checkWifiButton.isClickable()
            wifiStatus.hasEmptyText()
            device.network.toggleWiFi(true)
            checkWifiButton.click()
            wifiStatus.hasText("enabled")
            device.network.toggleWiFi(false)
            checkWifiButton.click()
            wifiStatus.hasText("disabled")
        }
    }
}
```

Вспоминаем, что использовать захардкоженные строки не стоит, лучше вместо них использовать строковые ресурсы.

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleTest : TestCase() {

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
}
```
!!! info
    Не забывайте перед запуском теста включить Wifi на устройстве, т.к. после каждого запуска он у вас будет выключен и при втором прогоне тест не пройдет.

Теперь нам нужно научиться переворачивать устройство, чтобы выполнить остальные проверки. За переворот устройства отвечает объект `exploit` из класса `Device`, про который вы также можете подробнее почитать в [документации](https://kasperskylab.github.io/Kaspresso/Wiki/Working_with_Android_OS/)

Весь процесс теста теперь будет выглядеть следующим образом:

<ol>
    <li>Устанавливаем на устройстве книжную ориентацию</li>
    <li>Проверяем, что кнопка видима и кликабельна</li>
    <li>Проверяем, что заголовок не содержит текст</li>
    <li>Кликаем по кнопке</li>
    <li>Проверяем, что текст в заголовке стал "enabled"</li>
    <li>Отключаем Wifi</li>
    <li>Кликаем по кнопке</li>
    <li>Проверяем, что текст в заголовке стал "disabled"</li>
    <li>Переворачиваем устройство</li>
    <li>Проверяем, что текст в заголовке сохранился "disabled"</li>
</ol>

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleTest : TestCase() {

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
            checkWifiButton.isVisible()
            checkWifiButton.isClickable()
            wifiStatus.hasEmptyText()
            checkWifiButton.click()
            wifiStatus.hasText(R.string.enabled_status)
            device.network.toggleWiFi(false)
            checkWifiButton.click()
            wifiStatus.hasText(R.string.disabled_status)
            device.exploit.rotate()
            wifiStatus.hasText(R.string.disabled_status)
        }
    }
}
```
Запускаем. Тест пройден успешно.

# Итог

Итак, в этом уроке мы попрактиковались с объектом `device`, научились менять статус интернет-соединения и ориентацию экрана из тестового класса. При этом тест запускается, все проверки завершаются успешно, но в нашем коде есть несколько серьезных проблем:

<ul>
    <li>Тест не разбит на шаги. В итоге мы имеем большое полотно кода, в котором достаточно сложно разобраться</li>
    <li>Тест выполняется успешно только в том случае, если мы предварительно включили интернет на устройстве. При этом, при каждом следующем запуске тест будет падать из-за того, что внутри него Wifi отключается</li>
</ul>

В следующих уроках мы узнаем, как можно улучшить этот код и решить возникшие проблемы.

<br>


