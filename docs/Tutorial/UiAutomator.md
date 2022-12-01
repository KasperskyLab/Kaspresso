# Kautomator. Тестирование сторонних приложений

В предыдущих уроках мы научились писать тесты для элементов пользовательского интерфейса, которые расположены в нашем приложении. Но часто бывают случаи, когда для полноценного тестирования этого недостаточно, и помимо нашего приложения нужно выполнить какие-то действия за его пределами. 

В качестве примера давайте проверим стартовый экран приложения Google Play в неавторизованном состоянии. 

<ol>
    <li>Открываем Google Play</li>
    <li>Проверяем, что на экране есть кнопка Sign In</li>
</ol>

<img src="../images/uiautomator/google_play_unauth.png" alt="Google play unauthorized"/>

Не забудьте перед запуском теста разлогиниться в приложении.

## Автотест Google Play

Приступаем к написанию теста – создаем класс `GooglePlayTest` и наследуемся от `TestCase`

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

class GooglePlayTest : TestCase() {
}
```

Добавляем тестовый метод

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

class GooglePlayTest : TestCase() {

    @Test
    fun testNotSignIn() = run {
        
    }
}
```

Первый шаг, который нам нужно сделать – запустить Google Play, для этого нам понадобится название пакета приложения. У Google Play это `com.android.vending`, позже мы покажем, где можно узнать эту информацию.

Это название пакета в тесте мы будем использовать несколько раз, поэтому, чтобы не дублировать код, создадим константу, куда вынесем это название:

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

class GooglePlayTest : TestCase() {

    @Test
    fun testNotSignIn() = run {
        
    }

    companion object {

        private const val GOOGLE_PLAY_PACKAGE = "com.android.vending"
    }
}
```

Для запуска любого экрана в Android нам нужен объект `Intent`. Подробнее об этом объекте вы можете прочитать [здесь]( https://developer.android.com/reference/android/content/Intent)

Получить `Intent` можно из объекта `Context`: 

```kotlin
val intent = device.targetContext.packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
```

В этой строчке мы первым делом получаем `targetContext` у объекта `device` – мы это уже делали в одном из предыдущих уроков. Затем, у `targetContext` мы получаем `packageManager`, из которого можно получить `Intent` для запуска экрана Google Play при помощи метода `getLaunchIntentForPackage`. 

Данный метод возвращает `Intent` для запуска стартового экрана приложения, пакет которого был передан в качестве параметра. Для этого мы передаем название пакета того приложения, которое хотим запустить, в данном случае Google Play.

Мы получили `Intent`, теперь с его помощью запустить экран. Для этого у объекта `targetContext` нужно вызвать метод `startActivity` и передать intent в качестве параметра:

```kotlin
val intent = device.targetContext.packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
device.targetContext.startActivity(intent)
```

В этом коде мы дважды получаем `targetContext` у объекта `device`. Чтобы не дублировать код, можно эту запись сократить, использовав функцию `with`

!!! info
    Подробнее про `with` и другие функции области видимости (англ. scope functions) вы можете почитать [в документации]( https://kotlinlang.ru/docs/scope-functions.html)

Тогда код теста будет выглядеть так:

 ```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

class GooglePlayTest : TestCase() {

    @Test
    fun testNotSignIn() = run {
        step("Open Google Play") {
            with(device.targetContext) {
                val intent = packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
                startActivity(intent)
            }
        }
    }

    companion object {

        private const val GOOGLE_PLAY_PACKAGE = "com.android.vending"
    }
}

```

Запускаем. Тест пройден успешно, на устройстве открывается приложение Google Play.

Теперь нам нужно проверить, что на открывшемся экране есть кнопка с текстом `Sign in`. Это не наше приложение, у нас нет доступа к исходному коду, поэтому получить id кнопки через Layout Inspector не получится. Нужно использовать другие инструменты.

## Инструменты для работы с другими приложениями

### UIAutomator

UI Automator – это библиотека для поиска компонентов на экране и эмуляции действий пользователя (клики, свайпы, ввод текста и т.д.). Он позволяет управлять приложением так, как бы это делал пользователь – взаимодействовать с любыми его элементами.

Благодаря этой библиотеке, мы можем тестировать любые приложения, выполнять в них различные действия, несмотря на то что у нас нет доступа к его исходному коду.

!!! info
   Более подробно про UiAutomator и его возможности вы можете почитать в [документации]( https://developer.android.com/training/testing/other-components/ui-automator)

В Android SDK также встроена программа Ui Automator Viewer. Она позволяет найти id элементов, с которыми мы хотим взаимодействовать, их позицию и другие полезные атрибуты.

Для того чтобы запустить Ui Automator Viewer, нужно открыть командную строку в папке `../Android/sdk/tools/bin` и выполнить команду `uiautomatorviewer`

У вас должно открыться вот такое окно:

<img src="../images/uiautomator/uiautomatorviewer_1.png" alt="UiAutomatorViewer first launch"/>

Если этого не произошло и в консоли отобразилась какая-то ошибка, то следует погуглить тест ошибки. 

Наиболее распространенная проблема – версия Java не совместима с uiautomatorviewer. В таком случае следует установить Java 8 (важно, чтобы данная версия была выпущена компанией Oracle) и прописать к ней путь в переменных среды. Как это сделать, мы разбирали в уроке [Выполнение adb-команд]( https://kasperskylab.github.io/Kaspresso/Tutorial/%D0%92%D1%8B%D0%BF%D0%BE%D0%BB%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5%20adb-%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4/#java-adb)

Сейчас мы хотим писать тест для приложения Google Play, чтобы взаимодействовать с ним из Ui Automator Viewer, необходимо запустить его на эмуляторе и кликнуть на кнопку `Device Screenshot`

<img src="../images/uiautomator/uiautomatorviewer_2.png" alt="UiAutomatorViewer create screenshot"/>

На некоторых версиях ОС эти иконки изначально скрыты, поэтому если они у вас не видны, то просто растяните экран.

В правой части видно всю информацию об элементах пользовательского интерфейса. Сейчас нас интересует кнопка `Sign in`. Кликаем на этот элемент и смотрим информацию о кнопке:

<img src="../images/uiautomator/uiautomator_button.png" alt="UiAutomatorViewer button info"/>

Здесь вы можете видеть некоторую полезную информацию:

<ol>
    <li>Package – название пакета приложения, которое мы указывали в тесте. Один из способов узнать его – посмотреть через эту программу</li>
    <li>Resource-id – здесь можно найти id элемента, чтобы потом по этому id найти кнопку и взаимодействовать с ней из теста. В нашем случае это невозможно, потому что в значении id указано, что имя ресурса было обфусцировано, то есть зашифровано. Поэтому поиск элемента по id для этого экрана невозможен</li>
    <li>Text – один из способов найти элемент на экране – по тексту, который на нем отображается. Получается, что сейчас найти кнопку на этом экране мы можем по атрибуту text</li>
</ol>

## Developer Assistant

Если по какой-то причине вам неудобно пользоваться Ui Automator Viewer, или вы не смогли его запустить, то можно воспользоваться приложением Developer Assistant. Его можно [скачать](https://play.google.com/store/apps/details?id=com.appsisle.developerassistant) в Google Play.

После установки и запуска вам нужно в настройках установить его, как приложение-ассистент по умолчанию. Кликните на кнопку “Choose” и следуйте инструкциям:

<img src="../images/uiautomator/da_1_settings.png" alt="Developer Assistant Settings"/>

<img src="../images/uiautomator/da_2_settings.png" alt="Developer Assistant Settings"/>

<img src="../images/uiautomator/da_3_settings.png" alt="Developer Assistant Settings"/>

<img src="../images/uiautomator/da_4_settings.png" alt="Developer Assistant Settings"/>

<img src="../images/uiautomator/da_5_settings.png" alt="Developer Assistant Settings"/>

<img src="../images/uiautomator/da_6_settings.png" alt="Developer Assistant Settings"/>

После настройки вы можете запускать анализ приложений. Открывайте приложение Google Play и осуществите долгое нажатие по кнопке `Home`

<img src="../images/uiautomator/da_gplay_1.png" alt="Developer Assistant Google play"/>

У вас появится окно с информацией о приложении, которое можно при необходимости переместить или расширить. На вкладке `App` есть информация о приложении – название пакета, запущенная в настоящий момент Activity и т.д. 

<img src="../images/uiautomator/da_gplay_2.png" alt="Developer Assistant Google play"/>

На вкладке `Element` можно исследовать элементы пользовательского интерфейса.

<img src="../images/uiautomator/da_gplay_3.png" alt="Developer Assistant Google play"/>

Здесь есть все те же атрибуты, которые мы видели в `Ui Automator Viewer`

## Написание теста

Теперь, когда мы нашли нужные нам элементы интерфейса, можем приступать к тестировани. Как обычно, мы начнем с написание Page Object экрана Google Play

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

object GooglePlayScreen {
    
}

```

Ранее все Page Object-ы мы наследовали от класса `KScreen`. В этом случае нам нужно было переопределить два метода `layoutId` и `viewClass`

```kotlin
override val layoutId: Int? = null
override val viewClass: Class<*>? = null
```

Мы так делали, потому что тестировали экран, который находится внутри нашего приложения, у нас был доступ к исходному коду, макету и Activity, с которой работаем. 

Но сейчас мы работаем с экраном, который находится в другом приложении, поэтому искать какие-то элементы в нем, кликать по кнопкам и выполнять любые другие действия с ним мы не можем. Вместо нас это будет делать библиотека UiAutomator.

UiAutomator может взаимодействовать с любыми приложениями, поэтому для тестирования мы будем использовать его.  При этом писать тесты с использованием этой библиотеки не просто, особенно, если вы не разработчик. Также такой код довольно сложный для восприятия, что может затруднить дальнейшую поддержку и расширение теста.

Поэтому в Kaspresso был добавлен Kautomator -  обертка над UiAutomator, которая позволяет значительно упростить написание тестов, а также добавляет ряд преимуществ, о которых подробно можно почитать в [Wiki]( https://kasperskylab.github.io/Kaspresso/Wiki/Kautomator-wrapper_over_UI_Automator/).

Теперь Page Object `GooglePlayScreen` мы наследуем не от `KScreen`, а от `UiScreen` и переопределяем метод `packageName`, чтобы он возвращал название пакета тестируемого приложения

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.screen.UiScreen

object GooglePlayScreen : UiScreen<GooglePlayScreen>() {

    override val packageName: String = "com.android.vending"
}
```

Далее все элементы пользовательского интерфейса будут представлять собой экземпляры классов с приставкой `Ui` (`UiButton`, `UiTextView`, `UiEditText`...), а не `K` (`KButton`, `KTextView`, `KEditText`...), как это было раньше. Дело в том, что сейчас мы тестируем другое приложение и нам нужна функциональность, доступная в компонентах Kautomator, поэтому будем работать с классами из этой бибилотеки.

На этом экране нас интересует кнопка `signIn`, добавляем ее:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object GooglePlayScreen : UiScreen<GooglePlayScreen>() {

    override val packageName: String = "com.android.vending"

    val signInButton = UiButton { }
}
```

Внутри нужно использовать какой-то matcher, благодаря которому мы найдем элемент на экране. Ранее мы использовали только `withId`, но сейчас id кнопки не доступен и придется использовать какой-то другой. 

Чтобы посмотреть все доступные matcher-ы, можно перейти в определение `UiButton` (удерживая `ctrl`, кликаем левой кнопкой мыши по названию класса). Внутри него вы увидите класс `UiViewBuilder` – переходите в него

<img src="../images/uiautomator/ui_button.png" alt="UI Button"/>

Здесь находится множество matcher-ов, которые вы можете использовать. 

<img src="../images/uiautomator/matchers.png" alt="Matchers"/>

Например, можно использовать `withText`, чтобы найти элемент, содержащий этот текст, или при помощи `withClassName` найти экземпляр какого-то класса.

Давайте найдем кнопку по тексту, который на ней указан

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object GooglePlayScreen : UiScreen<GooglePlayScreen>() {

    override val packageName: String = "com.android.vending"

    val signInButton = UiButton { withText("Sign in") }
}
```

Можем добавлять тест – проверим, что на экране Google Play отображается кнопка логина

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.GooglePlayScreen
import org.junit.Test

class GooglePlayTest : TestCase() {

    @Test
    fun testNotSignIn() = run {
        step("Open Google Play") {
            with(device.targetContext) {
                val intent = packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
                startActivity(intent)
            }
        }
        step("Check sign in button visibility") {
            GooglePlayScreen {
                signInButton.isDisplayed()
            }
        }
    }

    companion object {

        private const val GOOGLE_PLAY_PACKAGE = "com.android.vending"
    }
}
```

Запускаем. Тест пройден успешно.

## Тестирование системного UI

Мы рассмотрели один вариант, когда для тестирования нужно использовать UI automator – если мы взаимодействуем со сторонним приложением. Но это не единственный случай, когда его стоит применять.

Давайте откроем наше приложение `tutorial` и перейдем на экран `Notification Activity`

<img src="../images/uiautomator/notification_activity_btn.png" alt="Notification Activity Button"/>

Кликаем по кнопке “Show notification” – сверху отображается уведомление

<img src="../images/uiautomator/notification.png" alt="Notification Shown"/>

Давайте попробуем протестировать этот экран.

Сначала создадим Page Object для экрана с кнопкой «Показать уведомление». Этот экран находится в нашем приложении, значит можем унаследоваться от `KScreen`. Id кнопки можем найти через Layout Inspector

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton

object NotificationActivityScreen : KScreen<NotificationActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val showNotificationButton = KButton { withId(R.id.show_notification_button) }
}
```

В Page Object главного экрана добавим кнопку открытия `NotificationActivity`

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
}
```

Можно создавать тест, сначала просто покажем уведомление, кликнув на кнопку на главном экране

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NotificationActivityScreen
import org.junit.Rule
import org.junit.Test

class NotificationActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotification() = run {
        step("Open notification activity") {
            MainScreen {
                notificationActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Show notification") {
            NotificationActivityScreen {
                showNotificationButton.isVisible()
                showNotificationButton.isClickable()
                showNotificationButton.click()
            }
        }
    }
}
```

Запускаем. Тест пройден успешно, уведомление отображается.

Теперь давайте проверим тексты в самом уведомлении, что заголовок и контент содержат необходимый текст. 

Найти id элементов при помощи "Layout Inspector" или "Developer Assistant" не получится, т.к. отображение уведомлений относится к системному UI. В этом случае нам придется запустить Ui Automator Viewer и посмотреть через него.

Открываем список уведомлений и делаем скриншот

<img src="../images/uiautomator/uiautomator_notification.png" alt="Ui automator notification"/>

Здесь по названию пакета вы можете видеть, что шторка уведомлений не относится к нашему приложению, поэтому для тестирования необходимо унаследоваться от класса UiScreen и использовать Kautomator.

Создаем Page Object экрана уведомления:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"
}
```

В качестве `packageName` было указано значение, полученное через Ui Automator Viewer.

Объявляем элементы, с которыми будем взаимодействовать.

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"

    val title = UiTextView { }
    val content = UiTextView { }
}
```

Найти элементы можно по разным критериям, например по тексту или по id. Давайте найдем элемент по его id. Вызываем matcher `withId`

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"

    val title = UiTextView { withId("", "") }
    val content = UiTextView { withId("", "") }
}
```

Первым параметром нужно передать название пакета, в ресурсах которого будет осуществлен поиск элемента. Мы могли бы передать значение переменной `packageName` и `resource_id`, который получили через Ui Automator Viewer

```
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"

    val title = UiTextView { withId(this@NotificationScreen.packageName, "android:id/title") }
    val content = UiTextView { withId(this@NotificationScreen.packageName, "android:id/text") }
}
```

Но в таком случае элементы найдены не будут и вот по какое причине:

Схема `id` элемента, который мы ищем на экране другого приложения выглядит так: `package_name:id/resource_id`

Эта строка будет сформирована из двух параметров, которые мы передали в метод `withId`. Вместо package_name будет подставлено имя пакета ` com.android.systemui `, вместо resource_id – идентификатор `android:id/title`

В итоге получившийся resource_id будет выглядеть так: `com.android.systemui:id/android:id/title`

Получается, что символы `:id/` будут добавлены за нас, а передавать нам нужно только то, что правее косой черты, это и есть идентификатор:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"

    val title = UiTextView { withId(this@NotificationScreen.packageName, "title") }
    val content = UiTextView { withId(this@NotificationScreen.packageName, "text") }
}
```

Теперь полный resource_id выглядит так: `com.android.systemui:id/title` и ` com.android.systemui:id/text`

Обратите внимание на то, что первая часть (package_name) отличается от того, что указано в Ui Automator Viewer, мы указали название пакета `com.android.systemui`, а в программе написано `android`. 

<img src="../images/uiautomator/uiautomator_package.png" alt="Ui automator package" />

Дело в том, что в каждом приложении могут быть свои ресурсы, и в этом случае первая часть идентификатора ресурса будет содержать имя пакета того приложения, где ресурс создан, а также приложение может использовать ресурсы системы Android. Они являются общими для разных приложений и содержат название пакета `android`. 

Это как раз такой случай, поэтому в качестве первого параметра мы указываем `android`.

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"

    val title = UiTextView { withId("android", "title") }
    val content = UiTextView { withId("android", "text") }
}
```

Теперь можем добавлять проверки на данный экран. Убедимся, что в заголовке и в теле уведомления установлены правильные тексты:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NotificationActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.NotificationScreen
import org.junit.Rule
import org.junit.Test

class NotificationActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotification() = run {
        step("Open notification activity") {
            MainScreen {
                notificationActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Show notification") {
            NotificationActivityScreen {
                showNotificationButton.isVisible()
                showNotificationButton.isClickable()
                showNotificationButton.click()
            }
        }
        step("Check notification texts") {
            NotificationScreen {
                title.isDisplayed()
                title.hasText("Notification Title")
                content.isDisplayed()
                content.hasText("Notification Content")
            }
        }
    }
}
```

Запускаем. Тест пройден успешно.

## Итог

В этом уроке мы научились запускать тесты в сторонних приложениях, а также узнали, как можно проверить системный UI при помощи UiAutomator, а точнее его обертки - Kautomator. Кроме того, мы познакомились с программами, позволяющими анализировать UI приложений, даже если у нас нет доступа к их исходному коду – это Ui Automator Viewer и Developer Assistant.


<br>

