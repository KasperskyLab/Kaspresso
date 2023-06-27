# Flaky Safely. Тестирование с таймаутом

В данном уроке мы научимся тестировать экраны, состояние которых меняется с течением времени. 

До сих пор во всех тестах экраны сразу имели финальный вид, все элементы отображались при их открытии, и мы могли проводить тесты. Для изменения стейта мы сами производили какие-то действия – кликали по кнопке, вводили текст в поле ввода и так далее. 

Но часто возникает ситуация, когда внешний вид экрана меняется с течением времени. Например, на старте начинается загрузка данных – отображается ProgressBar, после загрузки отображается список элементов или диалог с сообщением об ошибке, если что-то пошло не так. В таких случаях во время теста нужно проверить все промежуточные состояния, при этом не меняя их из тестового метода.

Рассмотрим пример. Откройте приложение `tutorial` и кликните по кнопке `Flaky Activity`

<img src="../images/flaky/flaky_activity_btn.png" alt="Flaky activity button" width="300"/>

На этом экране отображаются несколько `TextView`, для которых загружаются какие-то данные

<img src="../images/flaky/flaky_1.png" alt="Flaky screen 1" width="300"/>

Через одну секунду загружается текст для первого элемента

<img src="../images/flaky/flaky_2.png" alt="Flaky screen 2" width="300"/>

Еще через три секунды появляется текст у второго элемента

<img src="../images/flaky/flaky_3.png" alt="Flaky screen 3" width="300"/>

Спустя 10 секунд произойдет загрузка остальных данных и тексты появятся у всех `TextView`

<img src="../images/flaky/flaky_4.png" alt="Flaky screen 4" width="300"/>

## Тестирование FlakyScreen

Давайте напишем тест на этот экран. Как обычно начнем с создания Page Object

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.progress.KProgressBar
import io.github.kakaocup.kakao.text.KButton

object FlakyScreen : KScreen<FlakyScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val text1 = KButton { withId(R.id.text_1) }
    val text2 = KButton { withId(R.id.text_2) }
    val text3 = KButton { withId(R.id.text_3) }
    val text4 = KButton { withId(R.id.text_4) }
    val text5 = KButton { withId(R.id.text_5) }

    val progressBar1 = KProgressBar { withId(R.id.progress_bar_1) }
    val progressBar2 = KProgressBar { withId(R.id.progress_bar_2) }
    val progressBar3 = KProgressBar { withId(R.id.progress_bar_3) }
    val progressBar4 = KProgressBar { withId(R.id.progress_bar_4) }
    val progressBar5 = KProgressBar { withId(R.id.progress_bar_5) }
}

```
Для перехода на `FlakyActivity` нужно кликнуть кнопку на главном экране. Добавляем ее в PageObject `MainScreen`

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
    val flakyActivityButton = KButton { withId(R.id.flaky_activity_btn) }
}
```
Можем писать тест. Давайте сначала проверим, что экран открывается, все элементы видимы и на них отображается `ProgressBar`

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.FlakyScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class FlakyScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkFlakyScreen() = run {
        step("Open flaky screen") {
            MainScreen {
                flakyActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check initial elements") {
            FlakyScreen {
                text1.isVisible()
                text2.isVisible()
                text3.isVisible()
                text4.isVisible()
                text5.isVisible()
                progressBar1.isVisible()
                progressBar2.isVisible()
                progressBar3.isVisible()
                progressBar4.isVisible()
                progressBar5.isVisible()
            }
        }
    }
}

```

Следующее действие, происходящее на экране – загрузка текста для первого элемента. Нам нужно проверить, что на данном этапе первый `TextView` содержит текст “TEXT 1”. Эту проверку нужно сделать после того, как загрузка будет завершена.

Получается, что следующим шагом мы должны добавить необходимые проверки, и, если они завершатся неудачно, то нужно выполнять их снова в течение какого-то времени. В данном случае загрузка первого текста занимаете около одной секунды после открытия экрана, поэтому мы можем добавить таймаут в 1-3 секунды, в течение которых проверки будут повторяться. Если в течение этого времени методы вернут корректное значение, то тест завершится успешно, если же по истечении таймаута условие так и не будет выполнено, то тест будет «красным».

Для того, чтобы добавить таймаут, необходимо использовать метод `flakySafely`, где в круглых скобках указывается время в миллисекундах, в течение которого будут происходить попытки пройти тест. Тогда код теста будет выглядеть следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.FlakyScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class FlakyScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkFlakyScreen() = run {
        step("Open flaky screen") {
            MainScreen {
                flakyActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check initial elements") {
            FlakyScreen {
                text1.isVisible()
                text2.isVisible()
                text3.isVisible()
                text4.isVisible()
                text5.isVisible()
                progressBar1.isVisible()
                progressBar2.isVisible()
                progressBar3.isVisible()
                progressBar4.isVisible()
                progressBar5.isVisible()
            }
        }
        step("Check first element after loading") {
            FlakyScreen {
                flakySafely(3000) {
                    text1.hasText(R.string.text_1)
                    progressBar1.isGone() // Проверяем, что ProgressBar невидимый
                }
            }
        }
    }
}
```
Запускаем. Тест пройден успешно.

## Когда следует использовать flakySafely

Наш тест завершается успешно. Теперь давайте проверим, что будет, если мы уберем вызов метода `flakySafely`

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.FlakyScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class FlakyScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkFlakyScreen() = run {
        step("Open flaky screen") {
            MainScreen {
                flakyActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check initial elements") {
            FlakyScreen {
                text1.isVisible()
                text2.isVisible()
                text3.isVisible()
                text4.isVisible()
                text5.isVisible()
                progressBar1.isVisible()
                progressBar2.isVisible()
                progressBar3.isVisible()
                progressBar4.isVisible()
                progressBar5.isVisible()
            }
        }
        step("Check first element after loading") {
            FlakyScreen {
                text1.hasText(R.string.text_1)
                progressBar1.isGone() // Проверяем, что ProgressBar невидимый
            }
        }
    }
}
```
Запускаем. Тест все равно завершается успешно.

Казалось бы, мы не установили никакой таймаут, проверка должна была завершиться неудачей, но тест «зеленый». Дело в том, что в Kaspresso все проверки неявно используют метод `flakySafely` с каким-то таймаутом (в текущей версии Kaspresso таймаут составляет 10 секунд).

Вы могли обратить внимание, что если какой-то тест выполняется успешно, то приложение сразу закрывается, и Android Studio выводит сообщение об успешном прогоне тестов. Но если какая-то проверка завершается неудачей, то сообщение об ошибке появляется не сразу, а через несколько секунд – причина заключается в использовании flakySafely. Тест завершился неудачно и в течение 10 секунд еще несколько раз перезапускается.

Поэтому `flakySafely` добавлять нужно только в том случае, если дефолтный таймаут вам по каким-то причинам не подходит, и его нужно изменить на другой. Хороший случай использования увеличенного таймаута – когда на экране происходит загрузка данных из сети. Сервер может долго возвращать ответ, при этом тест не должен падать из-за медленно работающего backend-а.

На следующем шаге, через 3 секунды загружается второй текст. Три секунды укладывается в дефолтный таймаут, значит явно использовать `flakeSafely` с другим таймаутом не имеет смысла

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.FlakyScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class FlakyScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkFlakyScreen() = run {
        step("Open flaky screen") {
            MainScreen {
                flakyActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check initial elements") {
            FlakyScreen {
                text1.isVisible()
                text2.isVisible()
                text3.isVisible()
                text4.isVisible()
                text5.isVisible()
                progressBar1.isVisible()
                progressBar2.isVisible()
                progressBar3.isVisible()
                progressBar4.isVisible()
                progressBar5.isVisible()
            }
        }
        step("Check first element after loading") {
            FlakyScreen {
                text1.hasText(R.string.text_1)
                progressBar1.isGone()
            }
        }
        step("Check second element after loading") {
            FlakyScreen {
                text2.hasText(R.string.text_2)
                progressBar2.isGone()
            }
        }
    }
}
```
Следующий шаг – через 10 секунд после загрузки данных для второго элемента, текст появляется во всех остальных `TextView`. 10 секунд – приблизительное время загрузки данных, оно может быть больше или меньше этого значения, поэтому стандартный таймаут нам не подойдет. В таких случаях нужно явно вызывать `flakySafely`, передавая увеличенный таймаут, давайте передадим 15 секунд

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.FlakyScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class FlakyScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkFlakyScreen() = run {
        step("Open flaky screen") {
            MainScreen {
                flakyActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check initial elements") {
            FlakyScreen {
                text1.isVisible()
                text2.isVisible()
                text3.isVisible()
                text4.isVisible()
                text5.isVisible()
                progressBar1.isVisible()
                progressBar2.isVisible()
                progressBar3.isVisible()
                progressBar4.isVisible()
                progressBar5.isVisible()
            }
        }
        step("Check first element after loading") {
            FlakyScreen {
                text1.hasText(R.string.text_1)
                progressBar1.isGone()
            }
        }
        step("Check second element after loading") {
            FlakyScreen {
                text2.hasText(R.string.text_2)
                progressBar2.isGone()
            }
        }
        step("Check left elements after loading") {
            FlakyScreen {
                flakySafely(15000) {
                    text3.hasText(R.string.text_3)
                    progressBar3.isGone()
                    text4.hasText(R.string.text_4)
                    progressBar4.isGone()
                    text5.hasText(R.string.text_5)
                    progressBar5.isGone()
                }
            }
        }
    }
}
``` 

## Thread.sleep vs FlakySafely

В некоторых тестах можно увидеть такой код `Thread.sleep(delay_in_millis)`, который используется для решения проблем с таймаутом вместо `flakySafely`. Этот код останавливает поток на то время, которое было передано в качестве параметра. То есть тест в этом месте прекратит свое выполнение и будет ждать в течение какого-то времени, после завершения таймаута тест продолжит работу.

На первый взгляд может показаться, что в этих способах нет разницы, и делают они одно и то же. Но на самом деле в них есть существенное отличие. Если вы используете `flakySafely`, то независимо от таймаута после успешного прохождения проверки тест продолжит выполняться. А при использовании `Thread.sleep` в любом случае тест будет ждать, пока таймаут не завершится. 

В обычном случае все проверки в Kaspresso используют `flakySafely` с таймаутом 10 секунд, но, несмотря на это, тесты завершаются очень быстро, потому что, если метод вернул корректное значение, то никакого ожидания не будет. Если же все эти методы заменить на `Thread.sleep`, то каждая такая проверка будет занимать минимум 10 секунд и тесты будут прогоняться очень длительное время.

## Какой таймаут указывать?

Зная о преимуществах `flakySafely`, которые мы только что обсудили, может возникнуть желание для всех тестов указывать очень большой таймаут просто на всякий случай. Но так делать не стоит по нескольким причинам.

Во-первых, если приложение действительно работает некорректно, и какие-то тесты будут падать, то их прохождение будет значительно дольше, чем при стандартном таймауте.

Во-вторых, в приложении могут быть какие-то ошибки, которые приводят к тому, что оно работает значительно медленнее, чем ожидается. В таком случае мы могли бы узнать о проблеме из автотестов, но при слишком большом таймауте она останется незамеченной.

Поэтому в большинстве случаев вам подойдет стандартный таймаут, и явно указывать его не придется. В остальных случаях указывайте таймаут, который будет приемлемым для пользователя.

## Особенности работы со ScrollView

Вы могли обратить внимание, что все элементы на экране не помещаются, поскольку занимают довольно много места по высоте, поэтому весь контент был добавлен в [ScrollView]( https://developer.android.com/reference/android/widget/ScrollView), чтобы экран можно было скроллить. 

Мы можем добавить проверку на то, что при открытии экрана первый элемент отображается, а последний – нет. Использовать метод `isVisible` в данном случае будет неправильно, поскольку даже если на экране объект не поместился, но он видимый, то проверка вернет `true`. Вместо этого можно использовать методы `isDisplayed` и `isNotDisplayed`, которые нужны как раз в таких случаях – когда нужно узнать, что элемент действительно видно на экране.

Тогда код теста будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.FlakyScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class FlakyScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkFlakyScreen() = run {
        step("Open flaky screen") {
            MainScreen {
                flakyActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check display of elements") {
            FlakyScreen {
                text1.isDisplayed()
                text5.isNotDisplayed()
            }
        }
        step("Check initial elements") {
            FlakyScreen {
                text1.isVisible()
                text2.isVisible()
                text3.isVisible()
                text4.isVisible()
                text5.isVisible()
                progressBar1.isVisible()
                progressBar2.isVisible()
                progressBar3.isVisible()
                progressBar4.isVisible()
                progressBar5.isVisible()
            }
        }
        step("Check first element after loading") {
            FlakyScreen {
                text1.hasText(R.string.text_1)
                progressBar1.isGone()
            }
        }
        step("Check second element after loading") {
            FlakyScreen {
                text2.hasText(R.string.text_2)
                progressBar2.isGone()
            }
        }
        step("Check left elements after loading") {
            FlakyScreen {
                flakySafely(15000) {
                    text3.hasText(R.string.text_3)
                    progressBar3.isGone()
                    text4.hasText(R.string.text_4)
                    progressBar4.isGone()
                    text5.hasText(R.string.text_5)
                    progressBar5.isGone()
                }
            }
        }
    }
}
``` 
Тест пройден успешно. Теперь давайте изменим проверку пятого элемента списка. Теперь вместо метода `isNotDisplayed` мы используем `isDisplayed`. 

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.FlakyScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class FlakyScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkFlakyScreen() = run {
        step("Open flaky screen") {
            MainScreen {
                flakyActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check display of elements") {
            FlakyScreen {
                text1.isDisplayed()
                text5.isDisplayed()
            }
        }
        step("Check initial elements") {
            FlakyScreen {
                text1.isVisible()
                text2.isVisible()
                text3.isVisible()
                text4.isVisible()
                text5.isVisible()
                progressBar1.isVisible()
                progressBar2.isVisible()
                progressBar3.isVisible()
                progressBar4.isVisible()
                progressBar5.isVisible()
            }
        }
        step("Check first element after loading") {
            FlakyScreen {
                text1.hasText(R.string.text_1)
                progressBar1.isGone()
            }
        }
        step("Check second element after loading") {
            FlakyScreen {
                text2.hasText(R.string.text_2)
                progressBar2.isGone()
            }
        }
        step("Check left elements after loading") {
            FlakyScreen {
                flakySafely(15000) {
                    text3.hasText(R.string.text_3)
                    progressBar3.isGone()
                    text4.hasText(R.string.text_4)
                    progressBar4.isGone()
                    text5.hasText(R.string.text_5)
                    progressBar5.isGone()
                }
            }
        }
    }
}

```
Кажется, что тест должен завершиться неудачно, так как изначально пятого элемента на экране не видно. Запускаем. Тест пройден успешно.

Причина такого поведения в реализации проверок в библиотеке Kaspresso. Если мы проверяем элемент, который находится внутри [ScrollView]( https://developer.android.com/reference/android/widget/ScrollView), и эта проверка завершается неудачно, то внутри теста автоматически будет осуществлен скролл до данного элемента, и проверка выполнится снова. Таким образом была решена проблема, когда при нормальном поведении приложения тесты падали, только потому что не смогли проверить элемент, который в данный момент не виден на экране.

Получается, что была выполнена проверка `text5.isDisplayed`, она завершилась неудачно и экран был прокручен вниз и проверка запустилась снова. Теперь элемент действительно был виден на экране, поэтому тест завершился успешно.

При написании тестов на экраны, которые можно скроллить, учитывайте особенности работы с ними в Kaspresso.

## Итог

В этом уроке мы рассмотрели следующие моменты:

<ol>
<li>Метод `flakySafely` для проверки экрана с изменяющимся состоянием</li>
<li>Установка разных таймаутов для различных операций</li>
<li>Особенности работы Kaspresso на экранах, которые можно скроллить</li>
<li>Отличия методов Thread.sleep и flakySafely</li
</ol>

