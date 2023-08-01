# Выделение секций и шагов

## Улучшаем код

В прошлом уроке мы написали тест на экран проверки доступности интернета, код тестового класса выглядел вот так:

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

И мы говорили о том, что одна из проблем этого кода заключается в том, что его сложно читать и поддерживать даже на данном этапе, а если функциональность экрана расширится и нам придется добавлять еще тесты, то код станет абсолютно нечитаемым.

На самом деле обычно любые тесты (в т.ч. ручные) выполняются по test-кейсам. То есть у тестировщика есть последовательность шагов, которые он выполняет для проверки работоспособности экрана. В нашем случае у нас есть эта последовательность шагов, но записана она сплошным текстом и непонятно, где завершается один шаг и начинается другой. Мы можем решить эту проблему при помощи комментариев. 

Давайте скопируем этот класс `WifiSampleTest` и вставим в этот же пакет, но уже с другим названием `WifiSampleWithStepsTest`. Это нужно для того, чтобы вы потом смогли сравнить новую и старую реализации этого теста. Код `WifiSampleTest` мы сегодня менять не будем. Теперь в новом классе `WifiSampleWithStepsTest` мы добавляем комментарии к каждому шагу.

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleWithStepsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        // Step 1. Open target screen
        MainScreen {
            wifiActivityButton {
                isVisible()
                isClickable()
                click()
            }
        }
        WifiScreen {
            // Step 2. Check correct wifi status
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            checkWifiButton.isVisible()
            checkWifiButton.isClickable()
            wifiStatus.hasEmptyText()
            checkWifiButton.click()
            wifiStatus.hasText(R.string.enabled_status)
            device.network.toggleWiFi(false)
            checkWifiButton.click()
            wifiStatus.hasText(R.string.disabled_status)

            // Step 3. Rotate device and check wifi status
            device.exploit.rotate()
            wifiStatus.hasText(R.string.disabled_status)
        }
    }
}
```

Это немного улучшит читаемость кода, но всех проблем не решит. Например, у вас какой-то тест упадет, как вы узнаете, на каком шаге это произошло? Вам придется исследовать логи, пытаясь понять, что пошло не так. Было бы гораздо лучше, если бы в логах отображались записи вроде `Step 1 started -> ... -> Step 1 succeed` или `Step 2 started -> ... -> Step 2 failed`. Это позволит немедленно определить по записям в логе, на каком этапе возникла проблема. 

Для этого мы можем сами добавить вывод в лог для каждого шага до и после его выполнения и обернуть это все в блок `try catch`, чтобы фиксировать падение теста в логах. В этом случае наш тест выглядел бы следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.util.Log
import androidx.test.core.app.takeScreenshot
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleWithStepsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        try {
            Log.i("KASPRESSO", "Step 1. Open target screen -> started")
            MainScreen {
                wifiActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
            Log.i("KASPRESSO", "Step 1. Open target screen -> succeed")
        } catch (e: Throwable) {
            Log.i("KASPRESSO", "Step 1. Open target screen -> failed")
            takeScreenshot()
        }
        WifiScreen {
            try {
                Log.i("KASPRESSO", "Step 2. Check correct wifi status -> started")
                device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
                checkWifiButton.isVisible()
                checkWifiButton.isClickable()
                wifiStatus.hasEmptyText()
                checkWifiButton.click()
                wifiStatus.hasText(R.string.enabled_status)
                device.network.toggleWiFi(false)
                checkWifiButton.click()
                wifiStatus.hasText(R.string.disabled_status)
                Log.i("KASPRESSO", "Step 2. Check correct wifi status -> succeed")
            } catch (e: Throwable) {
                Log.i("KASPRESSO", "Step 2. Check correct wifi status -> failed")
            }

            try {
                Log.i("KASPRESSO", "Step 3. Rotate device and check wifi status -> started")
                device.exploit.rotate()
                wifiStatus.hasText(R.string.disabled_status)
                Log.i("KASPRESSO", "Step 3. Rotate device and check wifi status -> succeed")
            } catch (e: Throwable) {
                Log.i("KASPRESSO", "Step 3. Rotate device and check wifi status -> failed")
                takeScreenshot()
            }
        }
    }
}
```

Давайте включим интернет на устройстве и проверим работу нашего теста.

Запускаем. Тест пройден успешно. 

Теперь давайте посмотрим логи. Для этого откройте вкладку `Logcat` в нижней части Android Studio

<img src="../images/steps/logcat.png" alt="Logcat"/>

Здесь отображается множество логов и найти наши довольно сложно. Мы можем отфильтровать логи по тэгу, который указали ("KASPRESSO"). Для этого кликните на стрелку в правой верхней части `Logcat` и выберите пункт `Edit Configuration`

<img src="../images/steps/edit_configuration.png" alt="Edit configuration"/>

У вас откроется окно создания фильтра. Добавьте название фильтра, а также тэг, который нас интересует:

<img src="../images/steps/create_filter.png" alt="Create filter"/>

Теперь у нас отображается только полезная информация. Давайте очистим лог

<img src="../images/steps/clear_logcat.png" alt="Clear logcat"/>

и запустим тест еще раз. Не забываем перед этим включать интернет на устройстве. Читаем логи:

<img src="../images/steps/log_step_1.png" alt="Log step 1"/>

Здесь идут логи, которые мы добавили - шаг 1 запущен, затем выполняются проверки, затем шаг 1 завершился успешно.

Смотрим дальше:

<img src="../images/steps/log_step_2.png" alt="Log step 2"/>

<img src="../images/steps/log_step_3.png" alt="Log step 2"/>

Со вторым и третьим шагами также все хорошо. Нам понятно, когда и какой шаг начинает выполнение, видны конкретные действия, которые в данный момент выполняет тест, и виден результат работы теста.

Теперь давайте выключим интернет и запустим тест еще раз. По нашей логике тест должен завершиться неудачно.

Несмотря на то, что тест должен был завершиться с ошибкой, все тесты зеленые. Смотрим в лог - сейчас нас интересует step 2, который должен был завершиться неудачно из-за того, что изначально интернет на устройстве выключен

<img src="../images/steps/log_step_2_failed.png" alt="Log step 2 failed"/>

Судя по логам, `step 2` действительно завершился неудачно. Был проверен статус заголовка, текст не совпал, программа осуществила еще несколько попыток проверить, что текст на заголовке содержит текст `enabled`, но все эти попытки не увенчались успехом и шаг завершился с ошибкой. Почему в этом случае тесты у нас зеленые?

Дело в том, что если тест завершается неудачно, то бросается исключение, и если это исключение никто не обработал в блоке try catch, то тесты будут красными. А мы в коде обрабатываем все исключения для того, чтобы сделать запись в лог о том, что тест завершился с ошибкой.

```kotlin
try {
        ...
} catch (e: Throwable) {
    /**
     * Мы обработали исключение и дальше оно проброшено не будет, поэтому такой 
     * тест считается выполненным успешно
     */
    Log.i("KASPRESSO", "Step 2. Check correct wifi status -> failed")
}
```

Для решения этой проблемы необходимо после вывода в лог сообщения об ошибке бросить это исключение дальше, чтобы тест упал. Делается это при помощи ключевого слова `throw`. Тогда код теста будет выглядеть следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.util.Log
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleWithStepsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        try {
            Log.i("KASPRESSO", "Step 1. Open target screen -> started")
            MainScreen {
                wifiActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
            Log.i("KASPRESSO", "Step 1. Open target screen -> succeed")
        } catch (e: Throwable) {
            Log.i("KASPRESSO", "Step 1. Open target screen -> failed")
            throw e
        }
        WifiScreen {
            try {
                Log.i("KASPRESSO", "Step 2. Check correct wifi status -> started")
                device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
                checkWifiButton.isVisible()
                checkWifiButton.isClickable()
                wifiStatus.hasEmptyText()
                checkWifiButton.click()
                wifiStatus.hasText(R.string.enabled_status)
                device.network.toggleWiFi(false)
                checkWifiButton.click()
                wifiStatus.hasText(R.string.disabled_status)
                Log.i("KASPRESSO", "Step 2. Check correct wifi status -> succeed")
            } catch (e: Throwable) {
                Log.i("KASPRESSO", "Step 2. Check correct wifi status -> failed")
                throw e
            }

            try {
                Log.i("KASPRESSO", "Step 3. Rotate device and check wifi status -> started")
                device.exploit.rotate()
                wifiStatus.hasText(R.string.disabled_status)
                Log.i("KASPRESSO", "Step 3. Rotate device and check wifi status -> succeed")
            } catch (e: Throwable) {
                Log.i("KASPRESSO", "Step 3. Rotate device and check wifi status -> failed")
                throw e
            }
        }
    }
}
```
Запускаем тест еще раз. Теперь он завершается с ошибкой и мы имеем понятные логи, где сразу видно, на каком шаге произошла ошибка. После `step 2` в логах больше ничего нет.

Код, который мы написали, рабочий, но очень громоздкий, и нам приходится для каждого шага писать целое полотно одинакового кода (логи, блоки try catch и т.д). 

## Steps

Для того чтобы упростить написание тестов и сделать код более читаемым и расширяемым, в Kaspresso были добавлены step-ы. У них "под капотом" реализовано все то, что мы сейчас писали вручную.

Чтобы использовать step-ы, необходимо вызвать метод `run {}` и в фигурных скобках перечислить все шаги, которые будут выполнены во время теста. Каждый шаг нужно вызывать внутри функции step.

Давайте напишем это в коде. Для начала удаляем все лишнее - логи и блоки try catch.

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleWithStepsTest : TestCase() {

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

Теперь в начале теста мы вызываем метод run, внутри которого для каждого шага вызываем функцию `step`. Этой функции в качестве параметра передаем название шага. 

```kotlin
@Test
    fun test() {
        run {
            step("Open target screen") {
                ...
            }
            step("Check correct wifi status") {
                ...
            }
            step("Rotate device and check wifi status") {
                ...
            }
        }
    }
```

Внутри каждого step-а мы указываем действия, которые требуются на этом шаге. То же самое, что мы делали раньше. Тогда код теста будет выглядеть следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleWithStepsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        run {
            step("Open target screen") {
                MainScreen {
                    wifiActivityButton {
                        isVisible()
                        isClickable()
                        click()
                    }
                }
            }
            step("Check correct wifi status") {
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
                }
            }
            step("Rotate device and check wifi status") {
                WifiScreen {
                    device.exploit.rotate()
                    wifiStatus.hasText(R.string.disabled_status)
                }
            }
        }
    }
}
```

Включаем интернет на устройстве и запускаем тест. Тест пройден успешно. Смотрим логи:

<img src="../images/steps/log_with_steps.png" alt="Log with steps"/>

Таким образом, благодаря использованию step-ов, не только наш код стал более понятным и легким для восприятия, но также и логи имеют понятную структуру и позволяют быстро определить, какие этапы выполнялись и какой результат этих операций.

Давайте еще раз запустим этот тест теперь уже с выключенным интернетом. Тест падает. Смотрим логи.

<img src="../images/steps/test_failed_with_steps.png" alt="Test fail with steps"/>

Теперь искать ошибку в тесте становится гораздо проще благодаря понятным логам.

## Секции Before и After

Наш код стал гораздо лучше, но осталась одна важная проблема: необходимо, чтобы перед каждым тестом устройство приходило в дефолтное состояние - интернет должен быть включен и установлена книжная ориентация.

В Kaspresso есть возможность добавить блоки `before` и `after`. Код внутри блока `before` будет выполняться перед тестом - здесь мы можем установить настройки по умолчанию. Код внутри блока `after` будет выполнен после теста. Во время выполнения теста состояние телефона может меняться: мы можем выключить интернет, сменить ориентацию, но после теста нужно вернуть исходное состояние. Делать это мы будем внутри блока `after`.

Тогда код теста будет выглядеть следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Rule
import org.junit.Test

class WifiSampleWithStepsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        before {
            /**
             * Перед тестом устанавливаем книжную ориентацию и включаем Wifi
             */
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.after {
            /**
             * После теста возвращаем исходное состояние
             */
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.run {
            step("Open target screen") {
                MainScreen {
                    wifiActivityButton {
                        isVisible()
                        isClickable()
                        click()
                    }
                }
            }
            step("Check correct wifi status") {
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
            step("Rotate device and check wifi status") {
                WifiScreen {
                    device.exploit.rotate()
                    wifiStatus.hasText(R.string.disabled_status)
                }
            }
        }
    }
}
```

Тест практически готов, можем добавить одно небольшое улучшение. Сейчас после переворота устройства мы проверяем, что текст остался прежним, но не проверяем, что ориентация действительно поменялась. Получается, что если метод `device.expoit.rotate()` по какой-то причине не сработал, то ориентация не поменяется и проверка на текст будет бесполезной. Давайте добавим проверку, что ориентация девайса стала альбомной.

`Assert.assertTrue(device.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)`

Теперь полный код теста выглядит так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.res.Configuration
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.WifiScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WifiSampleWithStepsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        before {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.after {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            device.network.toggleWiFi(true)
        }.run {
            step("Open target screen") {
                MainScreen {
                    wifiActivityButton {
                        isVisible()
                        isClickable()
                        click()
                    }
                }
            }
            step("Check correct wifi status") {
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
            step("Rotate device and check wifi status") {
                WifiScreen {
                    device.exploit.rotate()
                    Assert.assertTrue(device.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                    wifiStatus.hasText(R.string.disabled_status)
                }
            }
        }
    }
}
```

## Итог

В этом уроке мы значительно улучшили наш код, он стал чище, понятнее, и его стало легче поддерживать. Это стало возможным благодаря таким функциям Kaspresso, как `step`, `before` и `after`. Также мы научились выводить сообщения в лог, а также читать логи, фильтровать их и анализировать.

<br>


