# Wifi Test and work with devices

В сегодняшнем уроке мы создадим тест, который проверяет работу экрана Internet Availability (`WifiActivity`)

Запускаем наше приложение tutorial и кликаем по кнопке `Internet Availability`

!!! image internet_availability_button

## Ручное тестирование

Давайте сначала протестируем этот экран руками

Изначально у нас есть кнопка `CHECK WIFI STATUS`, больше никакого текста на экране нет. На текущий момент Wifi на устройстве включен

!!! image first_launch_1 и first_launch_2

Кликаем на кнопку

!!! image wifi_enabled

Эта кнопка кликабельно, после клика отображается корректный статус состояния Wifi - enabled. Отключаем Wifi

!!! image turn_off_wifi

Кликаем на кнопку снова и проверям статус Wifi сейчас

!!! image wifi_disabled

Состояние определяется корректно. Последняя проверка - давайте перевернем устройство и убедимся, что текст на экране сохраняется

!!! image wifi_disabled_portrait

Текст сохраняется успешно, все тесты пройдены. Теперь нам необходимо добиться такого результата, чтобы все те же проверки выполнялись в автоматическом режиме. 

## Автотесты

Сейчас во время теста нужно будет автоматически включать и выключать интернет, а также менять ориентацию устройства на альбомную. Это выходит за рамки ответственности нашего приложения, а значит для тестов нам придется использовать adb-команды. Для этого необходимо, чтобы был запущен ADB-сервер. Мы разбирали этот момент в [предыдущем уроке](https://kasperskylab.github.io/Kaspresso/Tutorial/%D0%92%D1%8B%D0%BF%D0%BE%D0%BB%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5%20adb-%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4/). Если вдруг забыли, как это делается, пересмотрите его.

Сейчас в нашем тесте нужно будет на главном экране кликнуть по кнопке `Internet Availability`. А значит необходимо доработать Page Object главного экрана, добавив туда еще одну кнопку:

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

Теперь можем добавлять новый тестовый класс. В том же пакете, где у нас лежат другие тесты, мы добавляем WifiSampleTest

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

class WifiSampleTest: TestCase() {
    
}
```

Для проверки экрана с доступностью Wifi на него нужно перейти. Для этого мы проделаем такие же шаги, как в [уроке](https://kasperskylab.github.io/Kaspresso/Tutorial/Writing_simple_test/), в котором писали наш первый автотест:

<ol>
    <li>Добавим activityRule, чтобы при запуске теста у нас открывалась MainActivity</li>
    <li>Проверим, что кнопка для перехода на экран провеки Wifi видима и кликабельна</li>
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

Для полноценного тестирования этого экрана нам понадобится менять состояние подключения к Wifi, а также менять ориентацию устройства. Для этого в классе BaseTestCase (от которого унаследован наш класс WifiSampleTest) есть экземпляр класса `Device`, который так и называется `device`. Мы уже сталкивались с ним в предыдущем уроке, когда получали packageName нашего приложения.

У этого объекта есть множество полезных методов, подробно про которые вы можете почитать [тут](https://kasperskylab.github.io/Kaspresso/Wiki/Working_with_Android_OS/)

Первым делом нас интересует метод, который включает/отключает интернет. За работу с сетью отвечает объект `network`, который есть в классе `Device`.

Если мы хотим изменить состояние Wifi, то можем это сделать следующим образом:

```kotlin
/**
* В качестве параметра передаем тип boolean, false если хотим выключить WIFI, true - если хотим включить
*/
device.network.toggleWiFi(false)
```

Кроме WIFI мы можем также управлять мобильной сетью, а также интернет-подключением на устройстве в целом (Wifi + мобильная сеть). Для того чтобы посмотреть все доступные методы можно перейти в документацию, указанную выше, но есть способ проще - после названия объекта поставить точку и посмотреть, какие методы можно вызвать у этого объекта. По их названию обычно понятно, что они делают.

!!! image available_methods

Давайте напишем тест, который выполнит все необходимые проверки, кроме переворота устройства - им займемся чуть позже. Первым делом нужно создать Page Object экрана проверки интернет-подключения `WifiScreen`

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
