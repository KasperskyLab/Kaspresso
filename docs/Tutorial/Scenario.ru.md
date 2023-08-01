# Scenario

В этом уроке мы познакомимся со сценариями (класс `Scenario` из библиотеки Kaspresso), узнаем, что это, для чего они нужны, когда их стоит использовать, а когда лучше избегать.

Открываем приложение tutorial и кликаем по кнопке `Login Acitivity`.

<img src="../images/scenario/main_screen_login_button.png" alt="Main Screen login button" width="300"/>

У нас открывается экран авторизации, где пользователь может ввести логин и пароль и нажать на кнопку `Login`

<img src="../images/scenario/login_activity.png" alt="Login activity"  width="300"/>

Если поле `username` будет содержать менее трех символов или поле `password` - менее шести символов, то при клике на кнопку `LOGIN` ничего не произойдет.

Если же данные заполнены корректно, то авторизация проходит успешно и у нас открывается экран `AfterLoginActivity`.

<img src="../images/scenario/screen_after_login.png" alt="Screen After Login" width="300"/>

Получается, что для проверки экрана `AfterLoginActivity` пользователь должен быть авторизован в приложении. Поэтому давайте первым делом протестируем авторизацию - `LoginActivity`.

## Тестирование LoginActivity

Для проверки `LoginActivity` необходимо внутри PageObject главного экрана объявить еще одну кнопку для перехода в экран авторизации.

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
}
```

Теперь создаем PageObject для `LoginActivity`, назовем `LoginScreen`:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

object LoginScreen : KScreen<LoginScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val inputUsername = KEditText { withId(R.id.input_username) }
    val inputPassword = KEditText { withId(R.id.input_password) }
    val loginButton = KButton { withId(R.id.login_btn) }
}

```

Можем создавать тест `LoginActivityTest`. Добавляем шаг – открытие целевого экрана `LoginActivity`.

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Test

class LoginActivityTest : TestCase() {

    @Test
    fun test() {
        run {
            step("Open login screen") {
                MainScreen {
                    loginActivityButton {
                        isVisible()
                        isClickable()
                        click()
                    }
                }
            }
        }
    }
}

```

Теперь, когда целевой экран открыт, можем тестировать его. На текущем этапе добавим только проверку позитивного сценария, когда пользователь успешно ввел логин и пароль:

<ol>
  <li>Все элементы видимы и кнопка кликабельна</li>
  <li>Поля ввода содержат соответствующие подсказки</li>
  <li>Если поля ввода содержат валидные данные, то происходит переход на следующий экран</li>
</ol>

Для того, чтобы проверить, какая активити сейчас открыта, можно воспользоваться методом: `device.activities.isCurrent(LoginActivity::class.java)`.

Тогда общий код тестового класса будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.login.LoginActivity
import com.kaspersky.kaspresso.tutorial.screen.LoginScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class LoginActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        run {
            val username = "123456"
            val password = "123456"

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
            step("Check current screen") {
                device.activities.isCurrent(AfterLoginActivity::class.java)
            }
        }
    }
}

```

Запускаем тест. Тест пройден успешно.

Теперь давайте добавим проверки негативного сценария - если пользователь ввел логин или пароль меньше допустимой длины. 

Здесь нужно придерживаться правила – на каждый test-case свой тестовый метод. То есть проверку на поведение при вводе некорректного логина и пароля мы будем делать не в этом же методе, а создадим отдельные в том же классе `LoginActivityTest`.

```kotlin
@Test
fun loginUnsuccessfulIfUsernameIncorrect() {
    run {
        val username = "12"
        val password = "123456"

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
        step("Check current screen") {
            device.activities.isCurrent(LoginActivity::class.java)
        }
    }
}

```

И такой же тест на то, что логин введен верно, а пароль неверно.

```kotlin
@Test
fun loginUnsuccessfulIfPasswordIncorrect() {
    run {
        val username = "123456"
        val password = "12345"

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
        step("Check current screen") {
            device.activities.isCurrent(LoginActivity::class.java)
        }
    }
}

```

Давайте переименуем первый тест, чтобы по его названию было понятно, что мы проверяем именно успешную авторизацию.

```kotlin
@Test
fun test() 
```

Меняем на:

```kotlin
@Test
fun loginSuccessfulIfUsernameAndPasswordCorrect()
```

Запускаем тесты – они все пройдены успешно.

Обратите внимание на код, который мы используем внутри этих тестов. Для каждого теста мы делаем следующее:
<ol>
    <li>Объявляем переменные `username` и `password`, присваиваем им разные значения в зависимости от проверки, которую будем производить</li>
    <li>Открываем экран авторизации</li>
    <li>Проверяем видимость элементов</li>
    <li>Вводим логин и пароль в соответствующие поля и кликаем на кнопку "Login"</li>
    <li>Проверяем, что у нас открывается нужный экран</li>
</ol>

В зависимости от того, что мы проверяем в каждом конкретном тесте, у нас отличаются первый и последний шаги. На первом шаге мы присваиваем разные значения переменным `username` и `password`, на последнем шаге мы делаем разные проверки на то, какой экран открыт - `LoginActivity` или `AfterLoginActivity`.

При этом шаги со второго по четвертый абсолютно одинаковые для всех тестов. Это один из случаев, когда мы можем использовать класс Scenario.

## Создание Scenario

Сценарии – это классы, которые позволяют объединить в себе несколько step-ов. Например, в данном случае мы можем создать сценарий авторизации, в котором будет пройден весь процесс от старта главного экрана до клика по кнопке `Login` после ввода логина и пароля.

В пакете со всеми тестами `com.kaspersky.kaspresso.tutorial` создаем новый класс `LoginScenario` и наследуемся от класса `Scenario` из пакета `com.kaspersky.kaspresso.testcases.api.scenario`.

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.scenario.Scenario

class LoginScenario : Scenario() {
    
}

```

Здесь возникает ошибка, поскольку класс Scenario является абстрактным, и у него нужно переопределить свойство `steps`, в котором мы должны перечислить все шаги данного сценария. 

Нажимаем комбинацию клавиш `ctrl + i`, выбираем свойство, которое нужно переопределить, и нажимаем `OK`.

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class LoginScenario : Scenario() {
    override val steps: TestContext<Unit>.() -> Unit
        get() = TODO("Not yet implemented")
}

```

Теперь после указания типа `TestContext<Unit>.() -> Unit` удаляем строчку ` get() = TODO("Not yet implemented")`, ставим знак `=` и открываем фигурные скобки, в которых перечислим все необходимые шаги. 

!!! info
    В качестве возвращаемого типа у `steps` указано лямбда-выражение, которое является extension-функцией класса TestContext. Подробнее про [лямбда-выражения](https://kotlinlang.ru/docs/lambdas.html) и [extension-функции](https://kotlinlang.ru/docs/extensions.html) вы можете почитать в официальной документации Kotlin.

Скопируем шаги, которые повторяются в каждом тесте.

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.tutorial.screen.LoginScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen

class LoginScenario : Scenario() {

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

Теперь у нас есть сценарий авторизации, в котором мы открываем экран логина, проверяем видимость всех элементов, вводим значения логина и пароля и кликаем на кнопку `Login`. 

Но возникает одна проблема - в этом классе нет переменных `username` и `password`, которые нужно ввести в поля ввода. Мы могли бы объявить их прямо здесь внутри теста, как делали в классе `LoginActivityTest`, 

```kotlin
override val steps: TestContext<Unit>.() -> Unit = {
    val username = "123456" // Можно объявить переменные здесь
    val password = "123456"

    step("Open login screen") {
    ...
        
```

но в зависимости от проводимого теста эти значения должны отличаться, поэтому присвоить значение внутри теста мы не можем.

Поэтому вместо того, чтобы указывать логин и пароль прямо внутри сценария, мы можем их указать в качестве параметра в классе Scenario внутри конструктора. Тогда эта часть кода:

```kotlin

class LoginScenario : Scenario()

```

меняется на:

```kotlin

class LoginScenario(
    private val username: String,
    private val password: String
) : Scenario()

```

Теперь внутри теста мы не создаем логин и пароль, а используем те, что были переданы нам в качестве параметра в конструктор:

```kotlin
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
```

Тогда общий код сценария будет выглядеть так:

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

## Использование Scenario

Сценарий готов, можем его использовать в тестах. Давайте сначала используем сценарий в первом тестовом методе, а потом по аналогии сделаем и в остальных:

<ol>
  <li>Создаем step, в котором пытаемся залогиниться с корректными данными</li>
  <li>Вызываем функцию `scenario`</li>
  <li>В качестве параметра этой функции мы передаем объект LoginScenario</li>
  <li>В конструктор LoginScenario передаем корректные логин и пароль</li>
  <li>Добавляем step, в котором проверяем, что после логина открывается экран `AfterLoginActivity`</li>
</ol>

```kotlin
@Test
fun loginSuccessfulIfUsernameAndPasswordCorrect() {
    run {
        step("Try to login with correct username and password") {
            scenario(
                LoginScenario(
                    username = "123456",
                    password = "123456",
                )
            )
        }
        step("Check current screen") {
            device.activities.isCurrent(AfterLoginActivity::class.java)
        }
    }
}
```

Для остальных тестов делаем по аналогии:

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
                        password = "123456",
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
                        password = "123456",
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

Мы рассмотрели один случай, когда сценариями удобно пользоваться – когда одни и те же шаги используются в разных тестах в рамках тестирования одного экрана. Но это не единственное их предназначение. 

В приложении может быть множество экранов, попасть на которые можно только будучи авторизованным. В этом случае для каждого такого экрана придется заново описывать все шаги авторизации. Но при использовании сценариев это становится очень простой задачей.

Сейчас после входа у нас открывается экран `AfterLoginActivity`.  Давайте напишем тест для этого экрана. 

Первым делом создаем Page Object

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.edit.KEditText

object AfterLoginScreen : KScreen<AfterLoginScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val title = KEditText { withId(R.id.title) }
}

```

Добавляем тест:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class AfterLoginActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {

    }
}

```

Для того чтобы попасть на этот экран, нам нужно пройти процесс авторизации. Без использования сценариев нам бы пришлось заново выполнять все шаги – запускать главный экран, кликать на кнопку, затем вводить логин и пароль и снова кликать на кнопку. Но сейчас весь этот процесс сводится к использованию `LoginScenario`:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.AfterLoginScreen
import org.junit.Rule
import org.junit.Test

class AfterLoginActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        run {
            step("Open AfterLogin screen") {
                scenario(
                    LoginScenario(
                        username = "123456",
                        password = "123456"
                    )
                )
            }
            step("Check title") {
                AfterLoginScreen {
                    title {
                        isVisible()
                        hasText(R.string.screen_after_login)
                    }
                }
            }
        }
    }
}

```

Таким образом, благодаря использованию сценариев, код становится чистым, понятным и переиспользуемым. А для проверки экранов, доступных только авторизованным пользователям, теперь не нужно делать множество одинаковых шагов.

## Best practices

Сценарии очень удобная вещь, если ими правильно пользоваться.

<ul>
    <li>Если для выполнения разных тестов приходится делать одни и те же шаги, то это тот случай, когда стоит создать сценарий. Примеры: экраны авторизации, онбординга, оплаты покупок и т.д.</li>
    <li>Не следует использовать одни сценарии внутри других – такой код может стать сильно запутанным, это усложнит его переиспользование, ухудшит читаемость, и вы потеряете все преимущества сценариев.</li>
    <li>Создавайте сценарии только по мере необходимости. Не следует их создавать, только потому что когда-то в будущем эти шаги могут использоваться в других тестах. Если вы видите, что шаги повторяются в разных тестах, то можно создать сценарий, если нет – не стоит этого делать. Их количество в проекте должно быть минимальным.</li>
</ul>

## Итог

В сегодняшнем уроке мы узнали, что такое сценарии, научились их создавать, использовать и передавать параметры в их конструктор. Также мы рассмотрели случаи, когда их использование приносит пользу проекту, а когда наоборот – ухудшает читаемость кода, увеличивает его связность и усложняет переиспользование.

<br>


