# Kautomator. Тестирование сторонних приложений

В предыдущих уроках мы научились писать тесты для элементов пользовательского интерфейса, которые расположены в нашем приложении. Но часто бывают случаи, когда для полноценного тестирования этого недостаточно, и помимо нашего приложения нужно выполнить какие-то действия за его пределами. 

В качестве примера давайте проверим стартовый экран приложения Google Play в неавторизованном состоянии. 

<ol>
    <li>Открываем Google Play</li>
    <li>Проверяем, что на экране есть кнопка `Sign In`</li>
</ol>

<img src="../images/uiautomator/google_play_unauth.png" alt="Google play unauthorized" width="300"/>

Не забудьте перед запуском теста разлогиниться в приложении.

## Автотест на функционал Google Play

Приступаем к написанию теста – создаем класс `GooglePlayTest` и наследуемся от `TestCase`:

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

class GooglePlayTest : TestCase() {
}
```

Добавляем тестовый метод:

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

Для запуска любого экрана в Android нам нужен объект `Intent`. Чтобы получить необходимый Intent, мы будем использовать следующий код:

```kotlin
val intent = device.targetContext.packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
```
Здесь используется сразу несколько возможно незнакомых для вас объектов: [Context]( https://developer.android.com/reference/android/content/Context), [PackageManager]( https://developer.android.com/reference/android/content/pm/PackageManager) и [Intent]( https://developer.android.com/reference/android/content/Intent). Подробнее о них можно почитать в документации.

Если говорить коротко, то Context предоставляет доступ к различным ресурсам приложения и позволяет выполнять множество действий, в том числе открывать экраны при помощи Intent-ов. Intent содержит информацию о том, какой именно экран мы хотим открыть, а PackageManager в данном случае позволяет получить Intent для открытия стартового экрана конкретного приложения по названию пакета.

!!! info
    Для получения `Context` можно воспользоваться методами `targetContext` и `context` у объекта `device`. У них есть одно существенное отличие.
    Когда мы хотим проверить работу какого-то приложения и запускаем автотест, то на самом деле на устройство устанавливается два приложения: то, которое мы тестируем (в данном случае tutorial) и второе, которое запускает все тестовые сценарии. 
    Когда мы вызываем метод `targetContext`, то обращаемся к тестируемому приложению (tutorial), а если мы вызываем метод `context`, то обращение будет уже ко второму приложению, которое запускает тесты.


```kotlin
val intent = device.targetContext.packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
```

В приведенном выше коде мы первым делом получаем `targetContext` у объекта `device` – мы это уже делали в одном из предыдущих уроков. Затем, у `targetContext` мы получаем `packageManager`, из которого можно получить `Intent` для запуска экрана Google Play при помощи метода `getLaunchIntentForPackage`. 

Данный метод возвращает `Intent` для запуска стартового экрана приложения, пакет которого был передан в качестве параметра. Для этого мы передаем название пакета того приложения, которое хотим запустить, в данном случае Google Play.

Мы получили `Intent`, теперь с его помощью запустим экран. Для этого у объекта `targetContext` нужно вызвать метод `startActivity` и передать intent в качестве параметра:

```kotlin
val intent = device.targetContext.packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
device.targetContext.startActivity(intent)
```

В этом коде мы дважды получаем `targetContext` у объекта `device`. Чтобы не дублировать код, можно эту запись сократить, использовав функцию `with`

!!! info
    Подробнее про `with` и другие функции области видимости (англ. scope functions) вы можете почитать [в документации](https://kotlinlang.ru/docs/scope-functions.html).

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
Если вы не знакомы с функциями `with`, `apply`, и другими функциями области видимости, то можно обойтись и без них, в этом случае код теста будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

class GooglePlayTest : TestCase() {

    @Test
    fun testNotSignIn() = run {
        step("Open Google Play") {
            val intent = device.targetContext.packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
            device.targetContext.startActivity(intent)
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

Благодаря этой библиотеке, мы можем тестировать любые приложения, выполнять в них различные действия, несмотря на то что у нас нет доступа к их исходному коду.

!!! info
    Более подробно про UiAutomator и его возможности вы можете почитать в [документации]( https://developer.android.com/training/testing/other-components/ui-automator).

В Android SDK также встроена программа Ui Automator Viewer. Она позволяет найти id элементов, с которыми мы хотим взаимодействовать, их позицию и другие полезные атрибуты.

Для того чтобы запустить Ui Automator Viewer, нужно открыть командную строку в папке `../Android/sdk/tools/bin` и выполнить команду `uiautomatorviewer`.

У вас должно открыться вот такое окно:

<img src="../images/uiautomator/uiautomatorviewer_1.png" alt="UiAutomatorViewer first launch"/>

Если этого не произошло и в консоли отобразилась какая-то ошибка, то следует погуглить текст ошибки. 

Наиболее распространенная проблема – версия Java не совместима с uiautomatorviewer. В таком случае следует установить Java 8 (важно, чтобы данная версия была выпущена компанией Oracle) и прописать к ней путь в переменных среды. Как это сделать, мы разбирали в уроке [Выполнение adb-команд]( https://kasperskylab.github.io/Kaspresso/Tutorial/%D0%92%D1%8B%D0%BF%D0%BE%D0%BB%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5%20adb-%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4/#java-adb)

Вернемся к написанию теста. Проверять мы будем приложение Google Play, и, чтобы взаимодействовать с ним из Ui Automator Viewer, необходимо запустить его на эмуляторе и кликнуть на кнопку `Device Screenshot`:

<img src="../images/uiautomator/uiautomatorviewer_2.png" alt="UiAutomatorViewer create screenshot"/>

На некоторых версиях ОС эти иконки изначально скрыты, поэтому, если они у вас не видны, просто растяните экран.

В правой части видно всю информацию об элементах пользовательского интерфейса. Сейчас нас интересует кнопка `Sign in`. Кликаем на этот элемент и смотрим информацию о кнопке:

<img src="../images/uiautomator/uiautomator_button.png" alt="UiAutomatorViewer button info"/>

Здесь вы можете видеть некоторую полезную информацию:

<ol>
    <li>Package – название пакета приложения, которое мы указывали в тесте. Один из способов узнать его – посмотреть через эту программу</li>
    <li>Resource-id – здесь можно найти id элемента, чтобы потом по этому id найти кнопку и взаимодействовать с ней из теста. В нашем случае это невозможно, потому что в значении id указано, что имя ресурса было обфусцировано, то есть зашифровано. Поэтому поиск элемента по id для этого экрана невозможен</li>
    <li>Text – один из способов найти элемент на экране – по тексту, который на нем отображается. Получается, что сейчас найти кнопку на этом экране мы можем по атрибуту text</li>
</ol>

### Developer Assistant

Если по какой-то причине вам неудобно пользоваться Ui Automator Viewer, или вы не смогли его запустить, то можно воспользоваться приложением Developer Assistant. Его можно [скачать](https://play.google.com/store/apps/details?id=com.appsisle.developerassistant) в Google Play.

После установки и запуска Developer Assistant необходимо в настройках выбрать его, как приложение-ассистент по умолчанию. Для этого кликните на кнопку `Choose` и следуйте инструкциям:

<img src="../images/uiautomator/da_1_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_2_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_3_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_4_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_5_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_6_settings.png" alt="Developer Assistant Settings" width="300"/>

После настройки вы можете запускать анализ приложений. Открывайте приложение Google Play и осуществите долгое нажатие по кнопке `Home`:

<img src="../images/uiautomator/da_gplay_1.png" alt="Developer Assistant Google play" width="300"/>

У вас появится окно с информацией о приложении, которое можно при необходимости переместить или расширить. На вкладке `App` есть информация о приложении – название пакета, запущенная в настоящий момент Activity и т.д. 

<img src="../images/uiautomator/da_gplay_2.png" alt="Developer Assistant Google play" width="300">

На вкладке `Element` можно исследовать элементы пользовательского интерфейса.

<img src="../images/uiautomator/da_gplay_3.png" alt="Developer Assistant Google play" width="300"/>

Здесь есть все те же атрибуты, которые мы видели в `Ui Automator Viewer`.

### Dump

В некоторых случаях, о которых мы поговорим дальше в этом уроке, использовать Developer Assistant не получится, поскольку он не умеет отображать информацию о системном UI (уведомления, диалоги и т.д.). Если вы оказались в такой ситуации, что возможностей Developer Assistant недостаточно, а Ui Automator Viewer запустить не удалось, то есть третий вариант – выполнить adb shell-команду `uiautomator dump`.

Для этого на эмуляторе откройте экран, информацию о котором вам нужно получить (в данном случае Google Play). Откройте консоль и выполните команду:

```
adb shell uiautomator dump
```

<img src="../images/uiautomator/dump_1.png" alt="Uiautomator Dump"/>

На вашем эмуляторе должен был появиться файл `window_dump.xml`, который можно найти через `Device Explorer`. Если он у вас не отображается, то выберите папку `sdcard` и нажмите `Synchronize`:

<img src="../images/uiautomator/dump_2.png" alt="Uiautomator Dump"/>

Если после этих шагов файл все равно не появился, то выполните еще одну команду в консоли:

```
adb pull /sdcard/window_dump.xml
```

После этого найдите файл на вашем компьютере через `Device File Explorer` и откройте его в Android Studio:

<img src="../images/uiautomator/dump_3.png" alt="Uiautomator Dump"/>

Этот файл представляет собой описание экрана в формате xml. Тут можно также найти все необходимые объекты, их свойства и id. Если он у вас отображается в одну строчку, то следует сделать автоформатирование, чтобы было легче читать код. Для этого нажмите комбинацию клавиш `ctrl + alt + L` на Windows или `cmd + option + L` на Mac.

<img src="../images/uiautomator/dump_4.png" alt="Uiautomator Dump"/>

Можно найти кнопку логина и посмотреть все ее атрибуты. Для этого нажимаем комбинацию клавиш `ctrl + F` (или `cmd + F` на Mac) и вводим текст, который установлен на кнопке «Sign in».

<img src="../images/uiautomator/dump_5.png" alt="Uiautomator Dump"/>

## Написание теста

Теперь, когда мы нашли нужные нам элементы интерфейса, можем приступать к тестированию. Как обычно мы начнем с создания Page Object экрана Google Play

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

object GooglePlayScreen {
    
}
```

Ранее все Page Object-ы мы наследовали от класса `KScreen`. В этом случае нам нужно было переопределить два свойства: `layoutId` и `viewClass`

```kotlin
override val layoutId: Int? = null
override val viewClass: Class<*>? = null
```

Мы так делали, потому что тестировали экран, который находится внутри нашего приложения, у нас был доступ к исходному коду, макету и Activity, с которой работаем. Но сейчас мы хотим протестировать экран из стороннего приложения, поэтому искать какие-то элементы в нем, кликать по кнопкам и выполнять любые другие действия с ним тем способом, который применяли в прошлых уроках, невозможно.

Для этих целей в Kaspresso есть компонент Kautomator - обертка над известным инструментом UiAutomator. Kautomator позволяет значительно упростить написание тестов, а также добавляет ряд преимуществ в сравнении с UiAutomator, о которых подробно можно почитать в [Wiki]( https://kasperskylab.github.io/Kaspresso/Wiki/Kautomator-wrapper_over_UI_Automator/).

Page object-ы для экранов сторонних приложений нужно наследовать не от `KScreen`, а от `UiScreen`. Дополнительно требуется переопределить свойство `packageName`, чтобы оно возвращало название пакета тестируемого приложения:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.screen.UiScreen

object GooglePlayScreen : UiScreen<GooglePlayScreen>() {

    override val packageName: String = "com.android.vending"
}
```

Далее все элементы пользовательского интерфейса будут представлять собой экземпляры классов с приставкой `Ui` (`UiButton`, `UiTextView`, `UiEditText`...), а не `K` (`KButton`, `KTextView`, `KEditText`...), как это было раньше. Дело в том, что сейчас мы тестируем другое приложение и нам нужна функциональность, доступная в компонентах Kautomator.

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

В фигурных скобках `UiButton {…}` нужно использовать какой-то matcher, благодаря которому мы найдем элемент на экране. Ранее мы использовали только `withId`, но сейчас id кнопки не доступен и придется использовать какой-то другой. 

Чтобы посмотреть все доступные matcher-ы, можно перейти в определение `UiButton` (удерживая `ctrl`, кликаем левой кнопкой мыши по названию класса). Внутри него вы увидите класс `UiViewBuilder`:

<img src="../images/uiautomator/ui_button.png" alt="UI Button"/>

В классе `UiViewBuilder` находится множество matcher-ов, которые вы можете использовать. Перейдя в него (удерживая `ctrl`, кликаем левой кнопкой мыши по названию класса) можно увидеть полный актуальный список: 

<img src="../images/uiautomator/matchers.png" alt="Matchers"/>

Например, можно использовать `withText`, чтобы найти элемент, содержащий определенный текст, или при помощи `withClassName` найти экземпляр какого-то класса.

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

Можем добавлять тест – проверим, что на экране Google Play отображается кнопка логина:

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

Давайте откроем наше приложение `tutorial` и перейдем на экран `Notification Activity`:

<img src="../images/uiautomator/notification_activity_btn.png" alt="Notification Activity Button" width="300"/>

Кликаем по кнопке “Show notification” – сверху отображается уведомление.

!!! info
     Подробнее про уведомления (notifications) в Android можно почитать [здесь]( https://developer.android.com/develop/ui/views/notifications).

<img src="../images/uiautomator/notification.png" alt="Notification Shown" width="300"/>

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

В Page Object главного экрана добавим кнопку открытия `NotificationActivity`:

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

Можно создавать тест, сначала просто покажем уведомление, кликнув на кнопку на главном экране.

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

Теперь давайте проверим, что заголовок и контент самого уведомления содержат необходимый текст. 

Найти id элементов при помощи `Layout Inspector` или `Developer Assistant` не получится, т.к. отображение уведомлений относится к системному UI. В этом случае нам придется использовать один из двух вариантов : запустить Ui Automator Viewer и посмотреть через него, либо выполнить команду `adb shell uiautomator dump`.

Далее мы покажем решение через `Ui Automator Viewer`, а также прикрепим скриншот, где найти View-элементы в файле `window_dump.xml`

Открываем список уведомлений и делаем скриншот:

<img src="../images/uiautomator/uiautomator_notification.png" alt="Ui automator notification"/>

При помощи команды `dump` необходимые элементы можно найти следующим образом

<img src="../images/uiautomator/dump_6.png" alt="Dump "/>

<img src="../images/uiautomator/dump_7.png" alt="Dump"/>

Здесь по названию пакета вы можете видеть, что шторка уведомлений не относится к нашему приложению, поэтому для тестирования необходимо унаследоваться от класса UiScreen и использовать Kautomator.

Создаем Page Object экрана уведомления:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"
}
```

В качестве `packageName` было указано значение, полученное с помощью `dump` или `Ui Automator Viewer`.

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

Найти элементы можно по разным критериям, например по тексту или по id. Давайте найдем элемент по его id. Вызываем matcher `withId`:

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

Первым параметром нужно передать название пакета, в ресурсах которого будет осуществлен поиск элемента. Мы могли бы передать ранее полученные значения `packageName` и `resource_id`

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"

    val title = UiTextView { withId(this@NotificationScreen.packageName, "android:id/title") }
    val content = UiTextView { withId(this@NotificationScreen.packageName, "android:id/text") }
}
```

Но в таком случае элементы не будут найдены.  Схема `id` элемента, который мы ищем на экране другого приложения, выглядит так: `package_name:id/resource_id`. Эта строка будет сформирована из двух параметров, которые мы передали в метод `withId`. Вместо `package_name` будет подставлено имя пакета ` com.android.systemui `, вместо `resource_id` – идентификатор `android:id/title`. В итоге получившийся resource_id будет выглядеть так: `com.android.systemui:id/android:id/title`. Получается, что символы `:id/` будут добавлены за нас, а передавать нам нужно только то, что правее косой черты, это и есть идентификатор:

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

Обратите внимание на то, что первая часть (`package_name`) отличается от того, что указано в `Ui Automator Viewer`, мы указали название пакета `com.android.systemui`, а в программе написано `android`. 

<img src="../images/uiautomator/uiautomator_package.png" alt="Ui automator package" />

Дело в том, что в каждом приложении могут быть свои ресурсы, и в этом случае первая часть идентификатора ресурса будет содержать имя пакета того приложения, где ресурс создан, а также приложение может использовать ресурсы системы Android. Они являются общими для разных приложений и имеют название пакета `android`. 

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

В этом уроке мы научились запускать тесты для сторонних приложений, а также узнали, как можно проверить системный UI при помощи `UiAutomator`, а точнее его обертки - `Kautomator`. Кроме того, мы познакомились с программами, позволяющими анализировать UI приложений, даже если у нас нет доступа к их исходному коду – это Ui Automator Viewer, Developer Assistant и UiAutomator Dump.

<br>


