# Scenario

В сегодняшнем уроке мы познакомимся со сценариями, узнаем, что это, для чего они нужны, когда их стоит использовать, а когда лучше избегать.

Открываем приложение tutorial и кликаем по кнопке “Login Acitivity”.

<img src="../images/scenario/main_screen_login_button.png" alt="Main Screen login button"/>

У нас открывается экран авторизации, где пользователь может ввести логин и пароль и нажать на кнопку “Login”

<img src="../images/scenario/login_activity.png" alt="Login activity "/>

Если поле `username` будет содержать менее трех символов или поле `password` менее шести символов, то при клике на кнопку `LOGIN` ничего не произойдет.

Если же данные заполнены корректно, то авторизация проходит успешно и у нас открывается экран AfterLoginActivity

<img src="../images/scenario/screen_after_login.png" alt="Screen After Login "/>

Получается, что для проверки экрана AfterLoginActivity пользователь должен быть авторизован в приложении. Поэтому давайте первым делом протестируем авторизацию - LoginActivity

## Тестирование LoginActivity

Для проверки LoginActivity необходимо внутри PageObject главного экрана объявить еще одну кнопку - кнопка для перехода в экран авторизации

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

Теперь создаем PageObject для LoginActivity, назовем LoginScreen

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

Можем создавать тест LoginActivityTest. Сразу добавляем шаг – открытие целевого экрана LoginActivity

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Test

class LoginActivityTest : TestCase() {

    @Test
    fun test() {
        run {
            step("Open target screen") {
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

<ul>
  <li>Все элементы видимы и кнопка кликабельна</li>
  <li>Поля ввода содержат соответствующие подсказки</li>
  <li>Если поля ввода содержат валидные данные, то происходит переход на следующий экран</li>
</ul>

Для того, чтобы проверить, какая активити сейчас открыта можно воспользоваться методом: `device.activities.isCurrent(LoginActivity::class.java)`

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
            step("Open target screen") {
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
                    }
                    inputPassword {
                        isVisible()
                    }
                    loginButton {
                        isVisible()
                        isClickable()
                    }
                }
            }
            step("Check successful login if password and login are correct") {
                val username = "123456"
                val password = "123456"

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
                    device.activities.isCurrent(AfterLoginActivity::class.java)
                }
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
        step("Open target screen") {
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
                }
                inputPassword {
                    isVisible()
                }
                loginButton {
                    isVisible()
                    isClickable()
                }
            }
        }
        step("Check unsuccessful login if username is too short") {
            val username = "12"
            val password = "123456"

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
                device.activities.isCurrent(LoginActivity::class.java)
            }
        }
    }
}

```

И такой же тест на то что логин введен верно, а пароль неверно

```kotlin
@Test
fun loginUnsuccessfulIfPasswordIncorrect() {
    run {
        step("Open target screen") {
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
                }
                inputPassword {
                    isVisible()
                }
                loginButton {
                    isVisible()
                    isClickable()
                }
            }
        }
        step("Check unsuccessful login if password is too short") {
            val username = "123456"
            val password = "12345"

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
                device.activities.isCurrent(LoginActivity::class.java)
            }
        }
    }
}

```

Давайте переименуем первый тест, чтобы по его названию было понятно, что мы проверяем именно успешную авторизацию.

```kotlin
@Test
    fun test() 
```

Меняем на

```kotlin
@Test
    fun loginSuccessfulIfUsernameAndPasswordCorrect()
```

Запускаем тесты – они все пройдены успешно.

Обратите внимание на код, который мы используем внутри этих тестов – для каждого теста мы сначала открываем главный экран и кликаем на кнопку логин, затем проверяем видимость элементов на экране `LoginActivity`, потом вводим логин и пароль, после этого проверяем на каком экране находится пользователь.

Получается, что мы пишем несколько одинаковых шагов для каждого теста. Это один из случаев, когда мы можем использовать сценарии.

## Создание и использование Scenario

Сценарии – это классы, которые позволяют объединить в себе несколько step-ов. Например, в данном случае мы можем создать сценарий авторизации, в котором будет пройден весь процесс от старта главного экрана до клика по кнопке `Login` после ввода логина и пароля.

В пакете со всеми тестами `com.kaspersky.kaspresso.tutorial` создаем новый класс `LoginScenario` и наследуемся от класса Scenario из пакета `com.kaspersky.kaspresso.testcases.api.scenario`

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.scenario.Scenario

class LoginScenario : Scenario() {
    
}

```

Здесь возникает ошибка, поскольку класс Scenario является абстрактным, и у него нужно переопределить один метод `steps`, в котором мы должны перечислить все шаги данного сценария. 

Нажимаем комбинацию клавиш `ctrl + i`, выбираем метод, который нужно переопределить и нажимаем `OK`

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class LoginScenario : Scenario() {
    override val steps: TestContext<Unit>.() -> Unit
        get() = TODO("Not yet implemented")
}

```

Теперь после указания типа `Unit` удаляем строчку ` get() = TODO("Not yet implemented")`, ставим знак `=` и открываем фигурные скобки, в которых перечислим все необходимые шаги. 

Скопируем их из какого-нибудь предыдущего теста.

```kotlin
```







