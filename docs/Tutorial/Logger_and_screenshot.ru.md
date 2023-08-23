# Логирование и скриншоты

В этом уроке мы научимся выявлять причины падающих тестов путем добавления дополнительных логов и скриншотов.

Вспомним пример, который уже использовался в одном из предыдущих уроков. Открываем приложение tutorial 

<img src="../images/logs/main_screen.png" alt="Tutorial main screen" width="300"/>

и кликаем на кнопку `Login Activity`

<img src="../images/logs/login_activity.png" alt="Login Activity" width="300"/>

На этом экране можно ввести логин и пароль, и, если они будут корректные, то откроется экран после авторизации. Корректными в данном случае считаются: логин длиной от трех символов, пароль – от шести.

<img src="../images/logs/after_auth.png" alt="Screen after auth" width="300"/>

## Внешняя система для тестовых данных

Мы уже писали тесты для этого экрана, они находятся в классе `LoginActivityTest`

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.login.LoginActivity
import org.junit.Rule
import org.junit.Test

class LoginActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun loginSuccessfulIfUsernameAndPasswordCorrect() {
        run {
            step("Try to login with correct username and password") {
                scenario(
                    LoginScenario(
                        username = "123456",
                        password = "123456"
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }

    @Test
    fun loginUnsuccessfulIfUsernameIncorrect() {
        run {
            step("Try to login with incorrect username") {
                scenario(
                    LoginScenario(
                        username = "12",
                        password = "123456"
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(LoginActivity::class.java)
            }
        }
    }

    @Test
    fun loginUnsuccessfulIfPasswordIncorrect() {
        run {
            step("Try to login with incorrect password") {
                scenario(
                    LoginScenario(
                        username = "123456",
                        password = "12345",
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(LoginActivity::class.java)
            }
        }
    }
}

```

В этом тесте мы сами создаем логин и пароль, с которыми будем авторизоваться. Но довольно распространенной является ситуация, когда данные для теста мы получаем из какой-то внешней системы. Например, для целей тестирования в проекте может быть поднят REST-API сервис, который генерирует данные для авторизациии, которые мы будем использовать.

Давайте смоделируем эту ситуацию. Создадим класс, который возвращает данные для входа – логин и пароль.

В пакете `com.kaspersky.kaspresso.tutorial` создадим еще один пакет `data`

<img src="../images/logs/create_package.png" alt="Create package 1"/>

<img src="../images/logs/create_package_2.png" alt="Create package 2"/>

В созданном пакете добавляем класс `TestData`, тип выбираем `Object`

<img src="../images/logs/create_class.png" alt="Create class"/>

Как мы уже говорили ранее – здесь мы будем только моделировать ситуацию, когда данные для теста получаем из внешней системы. В созданном классе у нас будет два метода: один из них возвращает логин, другой – пароль. В реальных проектах эти данные мы бы запрашивали с сервера. Сейчас мы сами укажем, какие логин и пароль вернет система, но представляем, что для нас это «черный ящик», и мы не знаем, какие значения будут получены.

Добавляем в этом классе два метода. Пусть они возвращают корректные логин и пароль:

```kotlin
package com.kaspersky.kaspresso.tutorial.data

object TestData {

    fun generateUsername(): String = "Admin"

    fun generatePassword(): String = "123456"
}
```
Теперь давайте создадим отдельный класс теста, в котором будем выполнять проверку успешного логина с помощью данных, полученных от класса `TestData`. Тестовый класс назовем `LoginActivityGeneratedDataTest`. Можем скопировать проверку успешного логина из класса `LoginActivityTest`

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import org.junit.Rule
import org.junit.Test

class LoginActivityGeneratedDataTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun loginSuccessfulIfUsernameAndPasswordCorrect() {
        run {
            step("Try to login with correct username and password") {
                scenario(
                    LoginScenario(
                        username = "123456",
                        password = "123456"
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }
}

```

Здесь мы используем захардкоженные логин и пароль, давайте получим их из класса `TestData`

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.data.TestData
import org.junit.Rule
import org.junit.Test

class LoginActivityGeneratedDataTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun loginSuccessfulIfUsernameAndPasswordCorrect() {
        run {
            val username = TestData.generateUsername()
            val password = TestData.generatePassword()
            step("Try to login with correct username and password") {
                scenario(
                    LoginScenario(
                        username = username,
                        password = password
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }
}

```
Запускаем. Тест пройден успешно.

## Анализ упавших тестов

Мы проверили, что, если система возвращает корректные данные, то тест проходит успешно. Давайте внесем изменения в класс `TestData`, чтобы он возвращал неверные значения

```kotlin
package com.kaspersky.kaspresso.tutorial.data

object TestData {

    fun generateUsername(): String = "Adm"

    fun generatePassword(): String = "123"
}

```
Запускаем тест еще раз. На этот раз тест падает. 

Мы уже говорили о том, что в реальных проектах влиять на внешнюю систему мы не можем, и иногда она может возвращать некорректные данные, из-за чего тест будет падать. Если тест упал, то нужно провести анализ и определить, в чем была проблема: в тестах, в неправильно работающем приложении или во внешней системе. Давайте попробуем определить это из логов. Открываем Logcat и фильтруем лог по тэгу `KASPRESSO`

<img src="../images/logs/logcat.png" alt="Test failed"/>

Что мы отсюда видим? Первый шаг теста - авторизация (`LoginScenario`) выполнен успешно, а проверка на то, что после успешного логина открыт нужный экран – провалилась.

При этом, отсюда совершенно неясно, почему проблема возникла. Мы не видим, с какими данными была попытка залогиниться, действительно ли они корректные, и непонятно, как решать возникшую проблему. Результат был бы более понятный, если бы в логах содержалась информация – какие конкретно логин и пароль были использованы во время тестирования. 

## Добавление логов

Для того чтобы выводить различную информацию в Logcat, мы можем воспользоваться классом `Log` из пакета `android.util`. Для этого у класса `Log` необходимо вызвать один из публичных статических методов: `i` (info), `d` (debug), `w` (warning), `e` (error). Все эти методы по сути делают одно и то же - выводят сообщение в журнал, но среди них есть отличие. Для того чтобы упростить поиск и чтение логов, их делят на несколько уровней:

<ul>
<li>Debug — сообщения для отладки программы</li>
<li>Error — серьезные ошибки, возникшие во время работы программы</li>
<li>Warning — предупреждения. Программа может продолжать работу, но рекомендуется обратить внимание на какую-то проблему</li>
<li>Info — простые сообщения, содержащие различного рода информацию. Система работает нормально</li>
</ul>

В зависимости от типа сообщения, которое вы хотите вывести в журнал, необходимо вызвать метод с соответствующим уровнем логирования. 

!!! info
    Более подробную информацию про уровни логирования и вывод сообщений в Logcat можно почитать в [официальной документации]("https://developer.android.com/studio/debug/logcat")

Например, в нашем случае мы хотим в журнале показать данные, которые использовались при авторизации - это простое информационное сообщение, которое не говорит об ошибках в работе программы или каких-то предупреждениях, а также не используется для отладки, поэтому нам подойдет уровень логирования `info` - метод `Log.i()`.

В качестве параметра этому методу нужно передать два аргумента типа String - две строчки: 

<ol>
<li>Тэг. По этому тэгу мы будем искать нужное нам сообщение в журнале.</li>
<li>Текст сообщения</li>
</ol>

Раньше необходимые сообщения в журнале мы искали по тэгу "KASPRESSO", можем указать его в качестве тэга, а в качестве сообщения выведем данные, использованные при авторизации.

Логин и пароль у нас генерируются перед шагом `step("Try to login with correct username and password")` можем в этом месте вывести в лог сообщение о том, какие именно данные были сгенерированы

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.util.Log
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.data.TestData
import org.junit.Rule
import org.junit.Test

class LoginActivityGeneratedDataTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun loginSuccessfulIfUsernameAndPasswordCorrect() {
        run {
            val username = TestData.generateUsername()
            val password = TestData.generatePassword()

            Log.i("KASPRESSO","Generated data. Username: $username, Password: $password")

            step("Try to login with correct username and password") {
                scenario(
                    LoginScenario(
                        username = username,
                        password = password
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }
}

```

В этой строчке `Log.i("Generated data. Username: $username, Password: $password")` мы вызываем метод `i` (уровень логирования info) у класса `Log`, в качестве тэга передаем "KASPRESSO", а в качестве сообщения передаем строку `"Generated data. Username: $username, Password: $password")`, где вместо `$username` и `$password` будут подставлены значения переменных логин и пароль.

!!! info
    Подробнее о том, как формировать строку с использованием переменных и методов, можно почитать в [документации]( https://kotlinlang.org/docs/strings.html#string-templates)

Давайте запустим тест еще раз и посмотрим логи:

<img src="../images/logs/custom_log.png" alt="Custom Log"/>

После `TEST SECTION` видно наш лог, который выводится с тэгом `KASPRESSO`. В этом логе видно, что сгенерированные данные некорректные (пароль слишком короткий), а значит тест падает из-за внешней системы, и решать проблему нужно именно в ней.

Если вы не хотите смотреть полностью весь лог, и вас интересуют только сообщения, добавленные вами, то вы можете использовать любой другой тэг. Для таких ситуаций удобно использовать тэг "KASPRESSO_TEST", тогда ваши логи будут отображаться в общем журнале вместе с другими сообщениями, если отфильтровать их по тэгу "KASPRESSO", при этом вы в любой момент сможете оставить только ваши сообщения, отфильтровав их по тэгу "KASPRESSO_TEST"

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.util.Log
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.data.TestData
import org.junit.Rule
import org.junit.Test

class LoginActivityGeneratedDataTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun loginSuccessfulIfUsernameAndPasswordCorrect() {
        run {
            val username = TestData.generateUsername()
            val password = TestData.generatePassword()

            Log.i("KASPRESSO_TEST","Generated data. Username: $username, Password: $password")

            step("Try to login with correct username and password") {
                scenario(
                    LoginScenario(
                        username = username,
                        password = password
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }
}

```

<img src="../images/logs/custom_log_test.png" alt="Custom Log"/>

Добавление собственных логов используется очень часто на практике, поэтому для удобства в Kaspresso был добавлен класс `UiTestLogger`, в котором вывод сообщений в лог с тэгом "KASPRESSO_TEST" реализован под капотом. В самих тестах вам достаточно обратиться к объекту `testLogger`, вызвав метод с необходимым уровнем логирования. При использовании этого метода больше не нужно передавать тэг, достаточно указать только текст сообщения.

В нашем случае логирование выглядело бы следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.data.TestData
import org.junit.Rule
import org.junit.Test

class LoginActivityGeneratedDataTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun loginSuccessfulIfUsernameAndPasswordCorrect() {
        run {
            val username = TestData.generateUsername()
            val password = TestData.generatePassword()

            testLogger.i("Generated data. Username: $username, Password: $password")

            step("Try to login with correct username and password") {
                scenario(
                    LoginScenario(
                        username = username,
                        password = password
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }
}

```

Теперь указывать тэг вручную не нужно, по умолчанию будет использован "KASPRESSO_TEST".

## Скриншоты

Логи действительно очень полезны при анализе тестов и поиске ошибок, но бывают случаи, когда одних логов недостаточно. Например, во время выполнения теста на экране мог отобразиться системный диалог, который помешал дальнейшему выполнению теста и привел к ошибке, или тест по какой-то причине не нашел нужного текста на экране. В таких ситуациях определить проблему по одним логам бывает невозможно. Если бы во время теста на каждом шаге сохранялся скриншот, и потом мы могли бы посмотреть их в какой-то папке, то поиск ошибок был бы намного проще.

В Kaspresso есть возможность во время теста делать скриншоты на любом шаге, для этого достаточно вызвать метод `device.screenshots.take("file_name")`. Вместо `file_name` нужно указать название файла скриншота, по которому вы сможете его найти. Давайте в каждый шаг `LoginScenario` мы добавим скриншоты, чтобы потом проанализировать все, что происходило на экране.

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.tutorial.screen.LoginScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen

class LoginScenario(
    private val username: String,
    private val password: String
) : Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
        step("Open login screen") {
            device.screenshots.take("before_open_login_screen")
            MainScreen {
                loginActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
            device.screenshots.take("after_open_login_screen")
        }
        step("Check elements visibility") {
            device.screenshots.take("check_elements_visibility")
            LoginScreen {
                inputUsername {
                    isVisible()
                    hasHint(R.string.login_activity_hint_username)
                }
                inputPassword {
                    isVisible()
                    hasHint(R.string.login_activity_hint_password)
                }
                loginButton {
                    isVisible()
                    isClickable()
                }
            }
        }
        step("Try to login") {
            LoginScreen {
                inputUsername {
                    replaceText(username)
                    device.screenshots.take("setup_username")
                }
                inputPassword {
                    replaceText(password)
                    device.screenshots.take("setup_password")
                }
                loginButton {
                    click()
                    device.screenshots.take("after_click_login")
                }
            }
        }
    }
}

```

Для того чтобы скриншоты сохранились на устройстве, у приложения должно быть дано разрешение на чтение и запись в файловую систему смартфона. Поэтому в тестовом классе мы дадим соответствующее разрешение через `GrantPermissionRule`

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.data.TestData
import org.junit.Rule
import org.junit.Test

class LoginActivityGeneratedDataTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @Test
    fun loginSuccessfulIfUsernameAndPasswordCorrect() {
        run {
            val username = TestData.generateUsername()
            val password = TestData.generatePassword()

            testLogger.i("Generated data. Username: $username, Password: $password")

            step("Try to login with correct username and password") {
                scenario(
                    LoginScenario(
                        username = username,
                        password = password
                    )
                )
            }
            step("Check current screen") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }
}

```

Запускаем тест еще раз.

После выполнения теста перейдите в `Device File Explorer` и откройте папку `sdcard/Documents/screenshots`. Если она у вас не отображается, то кликните правой кнопкой по папке `sdcard` и нажмите `Synchronize`

<img src="../images/logs/screenshots.png" alt="Screenshots"/>

Здесь по скриншотам можно определить, в чем проблема – на этапе установки пароля количество введенных символов – 3

<img src="../images/logs/setup_password.png" alt="Setup password"/>

Так, проанализировав скриншоты, можно определить, какая ошибка возникла в момент проведения тестов.

!!! info
    Один из способов сделать скриншот – вызвать метод `device.uiDevice.takeScreenshot`. Это метод из библиотеки `uiautomator` и использовать его напрямую никогда не следует. 

    Во-первых, скриншот, сделанный при помощи Kaspresso (`device.screenshots.take`), будет лежать в нужной папке, которую легко найти по названию теста, и файлы для каждого теста и шага будут находиться в своих папках с понятными названиями, а в случае с `uiautomator` находить нужные скриншоты будет проблематично. 

    Во-вторых, в Kaspresso сделано множество удобных доработок по работе со скриншотами таких как: масштабирование, настройка качества фото, полноэкранные скрины (когда весь контент не помещается на экране) и так далее. 

    Поэтому для скриншотов всегда используйте только объекты Kaspresso `device.screenshots`.

## Настройка Kaspresso.Builder

Теоретически, все тесты, которые вы пишете, могут упасть. В таких случаях хотелось бы всегда иметь возможность посмотреть скриншоты, чтобы понять, что пошло не так. Как этого добиться? Как вариант – во все шаги всех тестов добавлять вызов метода, который делает скриншот, но это не слишком удобно.

Поэтому в Kaspresso была добавлена возможность настройки параметров теста при создании тестового класса. Для этого в конструктор `TestCase` можно передать объект `Kaspresso.Builder`, у которого можно указать различные настройки.

<img src="../images/logs/test_case_params.png" alt="Test Case Params"/>

!!! info
    Чтобы посмотреть параметры, которые принимает метод или конструктор, можно кликнуть левой кнопкой мыши внутри круглых скобок и нажать комбинацию клавиш `ctrl + P` (или `cmd + P` на Mac)

Если этот параметр не передавать, оставив конструктор пустым, то будет использоваться значение по умолчанию `Kaspresso.Builder.simple()`. В этом варианте билдера автоматическое сохранение скриншотов не реализовано. Мы можем добавить множество разных настроек, подробнее о которых можно почитать в [Wiki](https://kasperskylab.github.io/Kaspresso/ru/Wiki/Kaspresso_configuration/).

Сейчас нас интересует добавление скриншотов, если тесты упали. Самый простой вариант сделать это – использовать `advanced` builder вместо `simple`. Делается это следующим образом:

```kotlin
class LoginActivityGeneratedDataTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.advanced()
)

```
В этом случае вызов методов, которые делают скриншоты, можно убрать, они будут сохранены автоматически, если тест упадет.

!!! info
    Обратите внимание, что разрешения на доступ к файловой системе нужны обязательно, без них скриншоты сохранены не будут

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.tutorial.screen.LoginScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen

class LoginScenario(
    private val username: String,
    private val password: String
) : Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
        step("Open login screen") {
            MainScreen {
                loginActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check elements visibility") {
            LoginScreen {
                inputUsername {
                    isVisible()
                    hasHint(R.string.login_activity_hint_username)
                }
                inputPassword {
                    isVisible()
                    hasHint(R.string.login_activity_hint_password)
                }
                loginButton {
                    isVisible()
                    isClickable()
                }
            }
        }
        step("Try to login") {
            LoginScreen {
                inputUsername {
                    replaceText(username)
                }
                inputPassword {
                    replaceText(password)
                }
                loginButton {
                    click()
                }
            }
        }
    }
}

```

Запускаем тест. Он завершился неудачно, и на устройстве появились скриншоты (не забывайте нажимать `Synchronize`):

<img src="../images/logs/advanced_builder.png" alt="Advanced Builder"/>

При использовании `advanced` builder-а появляется еще несколько изменений. Кроме скриншотов добавляются также файлы с логами, иерархией View и другое.

Если вам не нужны все эти артефакты, то можно изменить только определенные настройки простого builder-а

!!! info
    Если вы испытываете сложности с кастомизацией builder-ов, то используйте `advanced` builder для получения скриншотов

## Interceptors

Следует помнить, что в предыдущих тестах кроме выполнения наших методов «под капотом» происходило много дополнительных действий: запись логов для каждого шага, неявный вызов flakySafely, автоматический скролл до элемента, если проверка выполнилась неуспешно, и так далее.

Все это работало благодаря `Interceptor`-ам. `Interceptor` — это класс, который перехватывает вызываемые нами действия и добавляет в них какую-то функциональность. Таких классов в Kaspresso достаточно много, подробнее о них вы можете почитать в [документации](https://kasperskylab.github.io/Kaspresso/ru/Wiki/Kaspresso_configuration/)

Нас интересует добавление скриншотов, за это отвечают классы `ScreenshotStepWatcherInterceptor`, `ScreenshotFailStepWatcherInterceptor` и `TestRunnerScreenshotWatcherInterceptor`.

<ul>
<li>ScreenshotStepWatcherInterceptor – добавляет скриншоты независимо от того, шаг завершился с ошибкой или нет
</li>
<li>ScreenshotFailStepWatcherInterceptor – добавляет скриншот только того шага, который завершился с ошибкой
</li>
<li>TestRunnerScreenshotWatcherInterceptor – добавляет скриншот, если произошла ошибка в секции <code>before</code> или <code>after</code>
</li>
</ul>

Если тест падает, то удобно смотреть не только шаг, на котором произошла ошибка, но и предыдущие – таким образом разобраться в проблеме бывает гораздо проще. Поэтому мы добавим первый вариант `Interceptor`-а, который скриншотит все шаги, независимо от результата. Делается это следующим образом:

```kotlin
class LoginActivityGeneratedDataTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        stepWatcherInterceptors.add(ScreenshotStepWatcherInterceptor(screenshots))
    }
)
```
Здесь мы сначала получаем builder по умолчанию `Kaspresso.Builder.simple()`, вызываем у него метод `apply` 

```kotlin
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        
    }
```

и в фигурных скобках добавляем все необходимые настройки. 

В данном случае мы получаем все `Interceptor`-ы, которые перехватывают событие выполнения шагов (`step`) 

```kotlin
   stepWatcherInterceptors
```

и добавляем туда `ScreenshotStepWatcherInterceptor`. 

```kotlin
   stepWatcherInterceptors.add(ScreenshotStepWatcherInterceptor(...))
```

Этому `interseptor`-у в качестве параметра конструктора нужно передать реализацию интерфейса `Screenshots`, то есть экземпляр класса, который реализует данный интерфейс и, соответственно, умеет делать скриншоты. Такой объект уже есть в `Kaspresso.Builder`, называется он `screenshots`. Мы вызвали функцию `apply` у `Kaspresso.Builder`, поэтому, находясь внутри этой функции, мы можем напрямую обращаться к переменным и методам данного `builder`-а. Обращаемся к переменной `screenshots`, передавая ее в качестве параметра. 

```kotlin
class LoginActivityGeneratedDataTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        stepWatcherInterceptors.add(ScreenshotStepWatcherInterceptor(screenshots))
    }
)
```

Теперь, когда мы добавили данный `Interceptor`, после каждого шага теста, независимо от результата его выполнения, на устройстве будут сохранены скриншоты.

Запускаем. Тест завершился неудачно, и на устройстве были сохранены скриншоты

<img src="../images/logs/customized_builder.png" alt="Customized Builder"/>

Давайте вернем корректную реализацию класса `TestData`

```kotlin
package com.kaspersky.kaspresso.tutorial.data

object TestData {

    fun generateUsername(): String = "Admin"

    fun generatePassword(): String = "123456"
}

```

Запустим тест еще раз. Тест пройден успешно, и все скриншоты сохранены на устройстве.

!!! info
    Обратите внимание на то, что скриншоты сохраняются на тестируемом устройстве. Поэтому, если вы делаете скриншоты для каждого шага независимо от результата, то размер артефактов после прогона тестов может быть очень большим. Это может стать проблемой, особенно если ваши тесты запускаются на CI. Поэтому злоупотреблять скриншотами не следует, используйте их сохранение только в случае необходимости. 

## Итог

В этом уроке мы узнали, как в наши тесты добавить логирование и скриншоты. Узнали, в каких случаях стандартных логов бывает недостаточно, научились настраивать `Kaspresso.Builder`, добавляя в него различные `Interceptor`-ы.


<br>
