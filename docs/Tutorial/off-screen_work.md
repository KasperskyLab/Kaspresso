# Тестирование за рамками приложения

<br/> Тестовый сценарий может захватывать проверку и взаимодействие с элементами вне тестируемого приложения (проверить открытие другого приложения, нажать на элемент в тулбаре, проверить отображение системных диалоговых окон). Для таких сценариев Kaspresso предлагает KAutomator - обертку над UiAutomator. KAutomator - удобный и простой DSL для UI Automator на Kotlin, позволяющий ускорять работу UI Automator. 


## Реализуем тест, проверяющий запрос авторизации при открытии Google play.

<br/> Как и для обычного теста, при необходимости взаимодействия с UI-элементами другого приложения, нужно начать с создания `Screen` следуя паттерну [PageObject](https://azamatcherchesov.github.io/github_pages/Documentation/PageObject/). `GplayScreen` наследуется от класса `UiScreen`

```` Kotlin
object GplayScreen: UiScreen<GplayScreen>() {
    override val packageName: String = "com.android.vending"

    val signInButton get() = UiButton { withText(".*Sign in.*|.*Войти.*".toPattern(Pattern.CASE_INSENSITIVE)) }
}

````
<br/> Переопределение `packageName` необходимо для связывания `GplayScreen` с соответствующим пакетом для удобства дальнейшей поддержки теста. <br/> 
> Для выяснения точного названия пакета можно воспользоваться Android Monitor из Android SDK

<br/> Затем декларируем UI-элементы нужного экрана через поиск с использованием matcher-ов. UiAutomator предоставляет поддержку различных типов View:
<br/> * `UiView`
<br/> * `UiEditText`
<br/> * `UiTextView`
<br/> * `UiButton`
<br/> * `UiCheckbox`
<br/> * `UiChipGroup`
<br/> * `UiSwitchView`
<br/> * `UiScrollView`
<br/> * and more
<br/> Как и с UiAutomator можно комбинировать несколько matcher-ов для поиска нужной View:
````Kotlin
val email = UiEditText {
    withId(this@FormScreen.packageName, "email")
    withText(this@FormScreen.packageName, "matsyuk@kaspresso.com")
}
````
## Рассмотрим код теста
<br/> Представленный ниже код теста состоит из 3 шагов:
<br/> * Принудительной остановкой приложения Google Play
<br/> * Открытия приложения Google Play
<br/> * Проверки запроса авторизации
```` Kotlin
class GplayTest: TestCase() {

    @Test
    fun testNotSignedIn() = run {
        step("forceStop Gplay application") {
            adbServer.performShell("am force-stop $GPLAY_PACKAGE")
            adbServer.performShell("pm clear $GPLAY_PACKAGE")
        }
        step("open Gplay app") {
            with(device.targetContext) {
                val intent = packageManager.getLaunchIntentForPackage(GPLAY_PACKAGE)
                startActivity(intent)
            }
            device.uiDevice.waitForIdle()
        }

        step("verify sign in needed") {
            GplayScreen {
                signInButton.isDisplayed()
            }
        }
    }

    companion object {
        private const val GPLAY_PACKAGE = "com.android.vending"
    }
}

```` 
> Для успешного выполнения этого теста потребуется разрешение на Интернет и запущенный AdbServer

<br/> Работу первого шага не будем детально разбирать (примеры с `adbServer` можно найти в примере [Выполнение команд adb в тесте Kaspresso](https://azamatcherchesov.github.io/github_pages/Tutorial/adb_commands/#adb)). Вызов `device.targetContext` возвращет котекст тестируемого приложения, с помощью которого осуществляется открытие приложения Google Play.
<br/> UI-элементы другого приложения можно искать не только по тексту. Доступны несколько видов matcher-ов:
<br/> * `withId`
<br/> * `withText`
<br/> * `withPackage`
<br/> * `withContentDescription`
<br/> * `textStartsWith`
<br/> * and more
<br/> Чтобы понять, по каким параметрам можно обратиться к нужному UI-элементу, рекомендуем воспользоваться Layout Inspector и Uiautomatorviewer в Android Studio
