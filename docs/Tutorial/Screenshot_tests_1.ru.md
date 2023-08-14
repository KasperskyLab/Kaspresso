# Screenshot-тесты. Часть 1. Простой screenshot тест

В этом уроке мы научимся писать screenshot-тесты, узнаем, зачем они нужны, и как правильно разрабатывать приложение, чтобы его можно было покрыть тестами.

## Продвинутый уровень

Ранее для успешного прохождения уроков было достаточно базовых навыков программирования на Kotlin, знания Android-разработки не требовались. Однако сегодня мы начинаем углубленное изучение фреймворка Kaspresso, и для последующих тем потребуется более глубокое понимание устройства приложений, архитектурного шаблона MVVM, применения Dependency Injection и других концепций.

Если у вас возникают трудности с пониманием этих тем, вы все равно можете приступить к прохождению уроков, чтобы иметь представление о возможностях Kaspresso. Однако имейте в виду, что часть материала может быть непонятной на данном этапе.

## Тестирование LoginActivity на разных локалях

Чтобы узнать, зачем нужны скриншот-тесты, разберем небольшой пример. Представим, что наше приложение должно быть локализовано на французский язык. Для этого в проекте были добавлены переводы в файл `strings.xml` в папку `values-fr`.

<img src="../images/screenshot_tests_1/french.png" alt="French resources"/>

Давайте установим на устройстве французский язык

<img src="../images/screenshot_tests_1/fr_locale.png" alt="Install french locale" width="300"/>

и запустим LoginActivityTest.

<img src="../images/screenshot_tests_1/success_tests.png" alt="Tests completed successfully"/>

Тест пройден успешно, значит теоретически это приложение рабочее, и его можно раскатывать на пользователей. Но давайте откроем `LoginActivity` вручную (французский язык должен быть установлен на устройстве) и посмотрим, как выглядит этот экран.

<img src="../images/screenshot_tests_1/todo_on_screen.png" alt="Todo instead of strings" width="300"/>

Видим, что вместо корректных текстов здесь указано «TODO: add french locale». Похоже, что разработчики во время добавления строк оставили комментарий, чтобы добавить переводы в будущем, но забыли это сделать, поэтому приложение выглядит некорректно. Обнаружить эту ошибку тесты не могут, потому что они не знают, какой должен быть текст на французском языке. По этой причине приложение работает неправильно, но тесты проходят успешно.

## Screenshot-тесты, как решение проблемы со строками

Возникшую проблему могут решить скриншот-тесты. Их суть заключается в том, что для всех экранов, где пользователю отображаются строки, создаются так называемые «скриншотилки» – классы, которые делают скриншоты экрана во всех необходимых состояниях и для всех поддерживаемых языков.

После выполнения таких тестов скриншоты складываются в определенные папки. Затем их можно посмотреть и убедиться, что для всех локалей и для всех состояний используются корректные значения.

Для создания screenshot-тестов можно воспользоваться уже написанными ранее тестами, внеся в них несколько изменений. В таком случае будут выполняться те же проверки, что и раньше, но также добавится сохранение скриншотов на определенных этапах. Так можно сделать, но это не считается хорошей практикой.

Дело в том, что screenshot-тесты предназначены для того, чтобы предоставить снимки определенного экрана во всех возможных состояниях и для всех локалей. В некоторых случаях получение всех возможных состояний экрана может занять длительное время. 

К примеру, вам нужно узнать, как будет выглядеть экран, если пользователь только что прошел процесс регистрации. Тогда, для того чтобы получить снимок экрана, вам придется проходить регистрацию заново, причем делать это для каждой локали. Тогда один прогон теста может занять несколько минут вместо двух-трех секунд. 

По этой причине screenshot-тесты обычно делают максимально "легковесными":

Во-первых, вместо того, чтобы проходить весь процесс от старта приложения до открытия нужного экрана, мы сразу будем открывать [Activity]( https://developer.android.com/reference/android/app/Activity) или [Fragment]( https://developer.android.com/guide/fragments), скриншоты которого хотим получить.

Во-вторых, мы не будем добавлять проверки элементов или выполнять шаги, имитирующие действия пользователя, как мы делали ранее. Наши цели –

<ol>
<li>Открыть экран</li>
<li>Установить нужное состояние</li>
<li>Сделать скриншот</li>
<li>При необходимости изменить состояние и снова сделать скриншот </li>
</ol> 

Дальше нужно поменять локаль и повторить все перечисленные действия.

Подробнее про состояния (или стейты, как их часто называют), и как их правильно устанавливать, мы поговорим в следующем уроке, а сейчас напишем простой screenshot-тест, который откроет экран LoginActivity, сделает скриншот, затем сменит язык на устройстве на французский и снова сделает скриншот.

## Простой screenshot-тест

Создание screenshot-теста начинается так же, как мы делали ранее – в папке тестов создаем новый класс. Классы для скриншотов обычно называются с окончанием `Screenshots`. Давайте все скриншот-тесты будем хранить в отдельном пакете, назовем его screenshot_tests.

В этом пакете создаем класс `LoginActivityScreenshots`

<img src="../images/screenshot_tests_1/create_screenshot_test.png" alt="Creating screenshot test class"/>

У тестов, которые мы сейчас будем создавать, есть особенности: во-первых, они должны запускаться для разных локалей, во-вторых, полученные скриншоты должны быть размещены в удобной структуре папок – для каждого языка своя папка. По этим причинам тестовый класс мы унаследуем от класса `DocLocScreenshotTestCase`, а не от `TestCase`, как мы это делали ранее

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase

class LoginActivityScreenshots : DocLocScreenshotTestCase() {

}

```

В качестве параметра конструктору нужно передать список локалей, для которых будут делаться скриншоты. В данном случае нас интересует английский и французский языки, устанавливаем их. Делается это следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase

class LoginActivityScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

}

```
Порядок, в котором будут перечислены языки, не имеет значения, тест будет запущен для каждого языка поочерёдно.

Как мы говорили ранее, здесь мы не будем проходить весь процесс от старта приложения до открытия необходимого экрана. Вместо этого мы сразу создадим `Rule`, в котором укажем, что при старте теста должен быть открыт экран `LoginActivity`

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.login.LoginActivity
import org.junit.Rule

class LoginActivityScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoginActivity>()
}

```

В этом классе мы можем использовать такие же методы, какие использовали в других тестах. Давайте создадим один step, в котором проверим только исходное состояние экрана. Назовем метод takeScreenshots()

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.login.LoginActivity
import org.junit.Rule
import org.junit.Test

class LoginActivityScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoginActivity>()

    @Test
    fun takeScreenshots() = run {
        step("Take initial state screenshots") {

        }
    }
}

```

Чтобы сделать скриншоты и сохранить их в правильные папки на устройстве, необходимо вызвать метод `captureScreenshot`. В качестве параметра методу необходимо передать название файла, это может быть любая строка – по этому имени вы сможете найти скриншот на устройстве.

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.login.LoginActivity
import org.junit.Rule
import org.junit.Test

class LoginActivityScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoginActivity>()

    @Test
    fun takeScreenshots() = run {
        step("Take initial state screenshots") {
            captureScreenshot("Initial state")
        }
    }
}

```

Разрешение на доступ к файлам здесь давать не нужно, это реализовано «под капотом». На данном этапе мы сделали все необходимое, чтобы получить скриншоты экрана и посмотреть, как выглядит приложение на разных локалях, но желательно сделать еще одно изменение.

Сейчас у нас открывается нужный экран, и сразу делается скриншот, поэтому есть вероятность, что какие-то данные на экране не успеют загрузиться, и снимок будет сделан до того, как мы увидим нужные нам элементы.

Чтобы решить эту проблему, перед тем, как делать скриншот, мы дождемся загрузки всех необходимых элементов интерфейса. Для всех объектов `LoginScreen` мы сделаем проверку на `isVisible`. Это проверка в своей реализации использует `flakySafely`, поэтому даже если данные мгновенно загружены не будут, то тест будет ждать, пока условие не выполнится в течение нескольких секунд.

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.login.LoginActivity
import com.kaspersky.kaspresso.tutorial.screen.LoginScreen
import org.junit.Rule
import org.junit.Test

class LoginActivityScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoginActivity>()

    @Test
    fun takeScreenshots() = run {
        step("Take initial state screenshots") {
            LoginScreen {
                inputUsername.isVisible()
                inputPassword.isVisible()
                loginButton.isVisible()
                captureScreenshot("Initial state")
            }
        }
    }
}
```

Запускаем тест. Тест пройден успешно. В `Device File Explorer` в папке `sdcard/Documents/screenshots` вы сможете найти все скриншоты, при этом для каждой локали была создана своя папка, и вы сможете просмотреть, как выглядит ваше приложение на разных языках.

<img src="../images/screenshot_tests_1/screenshot_test.png" alt="Screenshot test results"/>

<img src="../images/screenshot_tests_1/initial_en.png" alt="Initial state en" width="300"/>

<img src="../images/screenshot_tests_1/initial_fr.png" alt="Initial state fr" width="300"/>

Теперь, просмотрев скриншоты, можно увидеть проблему в приложении из-за отсутствия необходимых переводов строк и исправить ошибку, добавив необходимые значения в файл `values-fr/strings.xml`.

!!! info 
    Возможно, на некоторых устройствах при смене локали у вас возникнет проблема с заголовком экрана – весь контент на экране будет корректно переведен на необходимый язык, а заголовок останется прежним. Проблема связана с [багом в библиотеке Google]( https://issuetracker.google.com/issues/246092030). Его уже пофиксили, как только опубликуют соответствующий релиз, внесем изменения в Kaspresso.


## Итог

В данном уроке мы рассмотрели: зачем нужны скриншот-тесты, как их писать и где смотреть результаты выполнения тестов.

Тема screenshot-тестов довольно обширная, и для более комфортного освоения мы ее разбили на несколько частей. В следующем уроке мы более подробно разберем тему стейтов, как их правильно устанавливать, и что нужно учитывать при разработке приложения, чтобы его можно было покрыть тестами.



