# Использование Kaspresso Scenario

<br/> При увеличении количества тестов увеличивается и повторение кода в тестах. Возникает желание вынести повторяющийся код в общее место. Этим местом может быть родительский абстрактный базовый класс, который наследуется от класса `TestCase`. Мы рекомендуем не допускать глубокого наследования и предпочитать композицию наследованию. Для этого в Kaspresso существует Scenario - абстракция, в которую можно вынести повторяющиеся шаги в тестах. При включении Scenario в тест, шаги Scenario будут встраиваться в последовательность шагов самого теста. 
<br/> В качестве примера для Scenario можно привести Login. Если в большом количестве тестов требуется выполнить вход в аккаунт, то можно повторяющуюся последовательность шагов вынести в общий Scenraio. 

```kotlin
class LoginScenario(private val account: Account): Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
        MainScreen {
            menuButton {
                click()
            }
            accountIcon {
                click()
            }
        }
        LoginScreen {
            signIn(account.login, account.password)
        }
        LoginProgressDialogScreen {
            waitForDisappear()
        }
    }
}
```
<br> При наследовании Scenario необходимо переопределить последовательность шагов, общие для нескольких тестов.
<br/> Использование из кода теста:
```kotlin
@Test
fun test() =
    before {
    
    }.after {

    }.run {
        step("Some step") {
            SomeScreen {
                ...
                scenario(MykSignInScenarioWithActivation(testAccount1))
                ...
            }
        }
    }
```
<br/> При вызове `scenario(MykSignInScenarioWithActivation(testAccount1))` его шаги будут встроены в последовательность шагов самого теста.
