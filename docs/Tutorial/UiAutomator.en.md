# Kautomator. Third Party Application Testing

In previous lessons, we learned how to write tests for user interface elements that are located in our application. But there are often cases when this is not enough for full-fledged testing, and in addition to our application, we need to perform some actions outside of it.

As an example, let's check the start screen of the Google Play app in an unauthorized state.

<ol>
    <li>Open Google Play</li>
    <li>Check that there is a `Sign In` button on the screen</li>
</ol>

<img src="../images/uiautomator/google_play_unauth.png" alt="Google play unauthorized" width="300"/>

Do not forget to log out before starting the test.

## Autotest for Google Play functionality

Let's start writing a test - create a class `GooglePlayTest` and inherit it from `TestCase`:

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

class GooglePlayTest : TestCase() {

}
```

Add a test method:

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

The first step we need to take is to launch the Google Play application, for this we need the name of its package. Google Play's package name is `com.android.vending`, later we will show where you can find this information.

We will use this package name in the test several times, therefore, in order not to duplicate the code, we will create a constant where we will put this name:

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

To launch any screen in Android, we need an `Intent` object. To get the required Intent we will use the following code:

```kotlin
val intent = device.targetContext.packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
```

Here several objects that may be unfamiliar to you are used at once: [Context]( https://developer.android.com/reference/android/content/Context), [PackageManager]( https://developer.android.com/reference/android/content/pm/PackageManager) and [Intent](https://developer.android.com/reference/android/content/Intent). You can read more about them in the documentation.

In short, Context provides access to various application resources and allows you to perform many actions, including opening screens using Intents. The Intent contains information about the screen we want to open, and the PackageManager in this case allows you to get an Intent to open the start screen of a particular application by its package name.

!!! info
    To get the `Context`, you can use the `targetContext` and `context` methods of the `device` object. They have one significant difference.
    When we want to check the operation of some application and run an autotest, in fact, two applications are installed on the device: the one that we are testing (in this case, the tutorial) and the second, which runs all the test scripts.
    When we call the `targetContext` method, we refer to the application under test (tutorial), and if we call the `context` method, then the call will be to the second application that runs the tests.

```kotlin
val intent = device.targetContext.packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
```

In the above code we first get the `targetContext` from the `device` object, like we already did in one of the previous lessons. Then, from `targetContext` we get `packageManager`, from which we can get the `Intent` to launch the Google Play screen using the `getLaunchIntentForPackage` method.

This method returns an `Intent` to launch the start screen of the application whose package was passed as a parameter. To do this, we pass the package name of the application we want to run, in this case, Google Play.

We got `Intent`, now we use it to launch the screen. To do this, call the `startActivity` method on the `targetContext` object and pass intent as a parameter:

```kotlin
val intent = device.targetContext.packageManager.getLaunchIntentForPackage(GOOGLE_PLAY_PACKAGE)
device.targetContext.startActivity(intent)
```

In this code, we get the `targetContext` twice from the `device` object. In order not to duplicate code, you can shorten this entry by using the `with` function

!!! info
    You can read more about `with` and other scope functions in [documentation](https://kotlinlang.ru/docs/scope-functions.html).

Then the test code will look like this:

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

If you are not familiar with the `with`, `apply`, and other scope functions, you can rewrite code without them, in which case the test code will look like this:

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

Let's launch the test. Test passed successfully, the Google Play app opens on the device.

Now we need to check that there is a button with the text `Sign in` on the opened screen. This is not our application, we do not have access to the source code, so getting the button id through the Layout Inspector will not work. You need to use other tools.

## Tools for working with other applications

### UIAutomator

UI Automator is a library for finding components on the screen and emulating user actions (clicks, swipes, text input, etc.). It allows you to manage the application the way the user would do it, i.e. to interact with any of its elements.

Thanks to this library, we can test any applications and perform various actions in them, despite the fact that we do not have access to their source code.

!!! info
    You can read more about UiAutomator and its capabilities in [documentation]( https://developer.android.com/training/testing/other-components/ui-automator).

The Android SDK also includes the Ui Automator Viewer. It allows us to find the IDs of the elements we want to interact with, their position and other useful attributes.

In order to launch Ui Automator Viewer, you need to open a command line in the `../Android/sdk/tools/bin` folder and execute the command `uiautomatorviewer`.

You should have a window like this:

<img src="../images/uiautomator/uiautomatorviewer_1.png" alt="UiAutomatorViewer first launch"/>

If this did not happen and some error was displayed in the console, then you should google the error text.

The most common problem is that the Java version is not compatible with uiautomatorviewer. In this case, you should install Java 8 (use only released by Oracle) and set the path to it in environment variables. How to do this, we discussed in the lesson [Executing adb commands]( https://kasperskylab.github.io/Kaspresso/Tutorial/%D0%92%D1%8B%D0%BF%D0%BE%D0%BB%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5%20adb-%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4/#java-adb).

Let's get back to writing the test. We will check the Google Play application, and in order to interact with it from the Ui Automator Viewer, you need to run it on the emulator and click on the `Device Screenshot` button:

<img src="../images/uiautomator/uiautomatorviewer_2.png" alt="UiAutomatorViewer create screenshot"/>

On some OS versions, these icons are initially hidden, so if you don't see them, just stretch the program window.

On the right side, you can see all the information about the user interface elements. Now we are interested in the `Sign in` button. We click on this element and look at the information about the button:

<img src="../images/uiautomator/uiautomator_button.png" alt="UiAutomatorViewer button info"/>

Here you can see some useful information:

<ol>
    <li>Package is the name of the application package that we specified in the test. One way to find it out is through this program</li>
    <li>Resource-id is the element id you can use to search for the button and interact with it from the test. In our case, it is not possible, because the id value indicates that the resource name has been obfuscated, that is, encrypted. Therefore, it is not possible to search for an element by id for this screen</li>
    <li>Text - one way to find an element on the screen is by the text that is displayed on it. It turns out that we can find the button on this screen by the text attribute</li>
</ol>

### Developer Assistant

If for some reason you are not comfortable using the Ui Automator Viewer, or you are unable to launch it, then you can use the Developer Assistant application. It can be [downloaded](https://play.google.com/store/apps/details?id=com.appsisle.developerassistant) on Google Play.

After installing and launching Developer Assistant, you need to select it in the settings as the default assistant application. To do this, click on the `Choose` button and follow the instructions:

<img src="../images/uiautomator/da_1_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_2_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_3_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_4_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_5_settings.png" alt="Developer Assistant Settings" width="300"/>

<img src="../images/uiautomator/da_6_settings.png" alt="Developer Assistant Settings" width="300"/>

Once configured, you can run application analysis. Open the Google Play app and long press the `Home` button:

<img src="../images/uiautomator/da_gplay_1.png" alt="Developer Assistant Google play" width="300"/>

You will see a window with information about the application, which you can move or expand if necessary. The `App` tab contains information about the application: package name, currently running Activity, etc.

<img src="../images/uiautomator/da_gplay_2.png" alt="Developer Assistant Google play" width="300">

The `Element` tab allows you to explore the user interface elements.

<img src="../images/uiautomator/da_gplay_3.png" alt="Developer Assistant Google play" width="300"/>

The `Sign in` button has all the same attributes that we saw in `Ui Automator Viewer`.

### Dump

In some cases, which we'll talk about later in this tutorial, you won't be able to use the Developer Assistant because it can't display information about the system UI (notifications, dialogs, etc.). If you find yourself in such a situation that the Developer Assistant capabilities are not enough, and the Ui Automator Viewer failed to start, then there is a third option: run the adb shell command `uiautomator dump`.

To do this, on the emulator, open the screen that you need to get information about (in this case, Google Play). Open the console and run the command:

```
adb shell uiautomator dump
```

<img src="../images/uiautomator/dump_1.png" alt="Uiautomator Dump"/>

A `window_dump.xml` file should have appeared on your emulator, which can be found through the `Device Explorer`. If it is not displayed for you, then select the `sdcard` folder and click `Synchronize`:

<img src="../images/uiautomator/dump_2.png" alt="Uiautomator Dump"/>

If after these steps the file still does not appear, then run one more command in the console:

```
adb pull /sdcard/window_dump.xml
```

After that find the file on your computer via `Device File Explorer` and open it in Android Studio:

<img src="../images/uiautomator/dump_3.png" alt="Uiautomator Dump"/>

This file is a description of the screen in xml format. Here you can also find all the necessary objects, their properties and IDs. If you have it displayed in one line, then you should auto-format the file to make it easier to read the code. To do this, press the key combination `ctrl + alt + L` on Windows or `cmd + option + L` on Mac.

<img src="../images/uiautomator/dump_4.png" alt="Uiautomator Dump"/>

You can find the login button and see all its attributes. To do this, press the key combination `ctrl + F` (or `cmd + F` on Mac) and enter the text that is set on the "Sign in" button.

<img src="../images/uiautomator/dump_5.png" alt="Uiautomator Dump"/>

## Writing a test

We have found the interface elements we need, and now we can start testing. As usual, we'll start by creating a Page Object for the Google Play screen.

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

object GooglePlayScreen {
    
}
```

Previously, we inherited all Page Objects from the `KScreen` class. In this case, we needed to override two properties: `layoutId` and `viewClass`

```kotlin
override val layoutId: Int? = null
override val viewClass: Class<*>? = null
```

We did this because we were testing the screen that is inside our application, we had access to the source code, the layout and the Activity we are working with. But now we want to test the screen from a third-party application, so it is impossible to search for some elements in it, click on buttons and perform any other actions with it the way that we did in previous lessons.

For these purposes, Kaspresso has the Kautomator component - a wrapper over the well-known UiAutomator tool. Kautomator makes writing tests much easier, and also adds a number of advantages compared to UiAutomator, which you can read about in detail in the [Wiki](https://kasperskylab.github.io/Kaspresso/Wiki/Kautomator-wrapper_over_UI_Automator/).

Page objects for screens of third-party applications should not inherit from `KScreen`, but from `UiScreen`. Additionally, you need to override the `packageName` property so that it returns the package name of the application under test:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.screen.UiScreen

object GooglePlayScreen : UiScreen<GooglePlayScreen>() {

    override val packageName: String = "com.android.vending"
}
```

Further, all user interface elements will be instances of classes with the prefix `Ui` (`UiButton`, `UiTextView`, `UiEditText`...), and not `K` (`KButton`, `KTextView`, `KEditText`. ..) as it was before. The point is that we are currently testing another application and we need the functionality available in the Kautomator components.

On this screen, we are interested in the `signIn` button, add it:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object GooglePlayScreen : UiScreen<GooglePlayScreen>() {

    override val packageName: String = "com.android.vending"

    val signInButton = UiButton { }
}
```

In curly brackets `UiButton {...}` we need to use some kind of matcher, thanks to which we will find the element on the screen. Previously, we used only `withId`, but now the id of the button is not available and we will have to use some other option.

To see all available matchers, you can go to the `UiButton` definition (hold `ctrl` and left-click on the class name). Inside it you will see the class `UiViewBuilder`.

<img src="../images/uiautomator/ui_button.png" alt="UI Button"/>

The `UiViewBuilder` class contains many matchers that you can use. By going to its definition (holding `ctrl`, left-clicking on the class name), you can see the full up-to-date list:

<img src="../images/uiautomator/matchers.png" alt="Matchers"/>

For example, you can use `withText` to find the element containing specific text, or use `withClassName` to find an instance of some class.

Let's find the button by the text that is displayed on it.

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object GooglePlayScreen : UiScreen<GooglePlayScreen>() {

    override val packageName: String = "com.android.vending"

    val signInButton = UiButton { withText("Sign in") }
}
```

We can add a test. Let's check that the login button is displayed on the Google Play screen:

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

Let's launch the test. It passed successfully.

## Testing the system UI

We have considered one option when we need to use the UI automator for testing: if we are interacting with a third-party application. But this is not the only case when it should be used.

Let's open our `tutorial` application and go to the `Notification Activity` screen:

<img src="../images/uiautomator/notification_activity_btn.png" alt="Notification Activity Button" width="300"/>

Click on the “Show notification” button - a notification is displayed on top.

!!! info
    You can read more about notifications in Android [here](https://developer.android.com/develop/ui/views/notifications).

<img src="../images/uiautomator/notification.png" alt="Notification Shown" width="300"/>

Let's try to test this screen.

First, let's create a Page Object for the screen with the "Show Notification" button. This screen is in our application, so we can inherit from `KScreen`. Button id can be found through the Layout Inspector:

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

In the Page Object of the main screen, add a button to open `NotificationActivity`:

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

You can create a test, first just show a notification by clicking on the button on the main screen:

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

Let's launch the test. It passed successfully, notification is displayed.

Now let's check that the title and content of the notification contain the required text.

Finding the id of the elements using the `Layout Inspector` or `Developer Assistant` will not work, because display of notifications belongs to the system UI. In this case, we will have to use one of two options: launch the Ui Automator Viewer and look through it, or run the `adb shell uiautomator dump` command.

Next, we will show the solution through the `Ui Automator Viewer`, and also attach a screenshot of where to find the View elements in the `window_dump.xml` file

Open the list of notifications and take a screenshot:

<img src="../images/uiautomator/uiautomator_notification.png" alt="Ui automator notification"/>

Using the `dump` command, the necessary elements can be found as follows

<img src="../images/uiautomator/dump_6.png" alt="Dump "/>

<img src="../images/uiautomator/dump_7.png" alt="Dump"/>

Here, by the package name, you can see that the notification drawer does not belong to our application, so for testing it is necessary to inherit from the UiScreen class and use Kautomator.

Create a Page Object of the notification screen:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"
}
```

`packageName` was set to the value obtained by `dump` or `Ui Automator Viewer`.

We declare the elements with which we will interact.

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

You can find elements by different criteria, for example, by text or by id. Let's find an element by its id. Call matcher `withId`:

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

The first parameter to pass is the package name of the application in whose resources the element will be searched. We could pass the previously obtained `packageName` and `resource_id` values:

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

But in this case, the elements will not be found. The `id` scheme of the element we are looking for on the screen of another application looks like this: `package_name:id/resource_id`. This string will be formed from the two parameters that we passed to the `withId` method. Instead of `package_name` the package name ` com.android.systemui ` will be substituted, instead of `resource_id` the identifier `android:id/title` will be substituted. The resulting resource_id will look like this: `com.android.systemui:id/android:id/title`. It turns out that the characters `:id/` will be added for us, and we only need to pass what is to the right of the slash, which will be the correct identifier:

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

Now the full `resource_id` looks like this: `com.android.systemui:id/title` and ` com.android.systemui:id/text`.

Please note that the first part (`package_name`) is different from what is specified in the `Ui Automator Viewer`, we specified the package name `com.android.systemui`, and the program says `android`.

<img src="../images/uiautomator/uiautomator_package.png" alt="Ui automator package" />

The reason is that each application can have its own resources, in which case the first part of the resource identifier will contain the package name of the application where the resource was created, and the application can also use the resources of the Android system. They are shared between different applications and contain the package name `android`.

This is exactly the case, so we specify `android` as the first parameter.

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

Now we can add checks to this screen. Let's make sure that the correct texts are set in the title and in the body of the notification:

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

Let's launch the test. It passed successfully.

## Summary

In this lesson, we learned how to run tests for third-party applications, and also learned how you can test the system UI using `UiAutomator`, or rather its wrapper `Kautomator`. In addition, we got to know the programs that allow us to analyze the UI of applications, even if we do not have access to their source code: these are `Ui Automator Viewer`, `Developer Assistant` and `UiAutomator Dump`.

<br>


