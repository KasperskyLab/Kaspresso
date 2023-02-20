# Test apps that require permissions

In this tutorial, we will learn how to work with permissions ([Permissions](https://developer.android.com/guide/topics/permissions/overview)).

Often, in order to work correctly, an application needs access to certain functions of the mobile device: to the camera, voice recording, making calls, sending SMS messages, etc. The application can access and use them only if the user gives permission to do so.

On older devices below the sixth version of Android (API level 23), such permissions were requested at the time the application was installed, and if the user installed it, it was considered that he agreed with all the permissions, and the application would be able to use all the necessary functions. This was unsafe, as it opened up the possibility for unscrupulous developers to gain access to the microphone, camera, calls and other important components without the user noticing and use it for their own purposes.

For this reason, on newer versions, the so-called "dangerous" permissions began to be requested not at the time of installation, but while the application was running. Now the user will clearly see a dialog with a proposal to allow or deny a request to use some functionality.

For example, run the `tutorial` application on one of the latest versions of Android (API 23 and above) and press the `Make Call Activity` button

<img src="../images/permissions/main_screen.png" alt="Main Screen" width="300"/>

You will see a screen on which there are two elements - an input field and a button. In the input field, you can specify some phone number and click on the `Make Call` button to make a call

<img src="../images/permissions/make_call_screen.png" alt="Make call screen" width="300"/>

Making calls is one of the features that requires permission from the user to work. Therefore, you will see a dialog asking you to allow the application to control calls, which has "Allow" and "Reject" buttons.

<img src="../images/permissions/request_permission_1.png" alt="Request permissions" width="300"/>

If we click “Allow”, then the call will begin to the subscriber at the number that you specified in the input field

<img src="../images/permissions/call_1.png" alt="Calling" width="300"/>

The next time you open the application, the permission will no longer be requested, it is saved on the device. If you want to revoke permission, you can do so in the settings. To do this, go to the application section, find the one you need and go to the `Permissions` section

<img src="../images/permissions/deny_permission_settings.png" alt="Deny permission" width="300"/>

Here you can go to any permission and change the value from `Allow` to `Deny` or vice versa.

The second way to do this is with the adb shell command:

`adb shell pm revoke package_name permission_name`

For our application, the command will look like this:

`adb shell pm revoke com.kaspersky.kaspresso.tutorial android.permission.CALL_PHONE`

After executing the command, the application will ask for permission again the next time you try to make a call.

## Create a test

When testing applications that require permissions, there are certain considerations. Let's write a test for this screen.

First of all, let's create a Page Object of the screen with the `Make Call` button

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

object MakeCallActivityScreen : KScreen<MakeCallActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val inputNumber = KEditText { withId(R.id.input_number) }
    val makeCallButton = KButton { withId(R.id.make_call_btn) }
}
```

To get to this screen, you will need to click on the corresponding button in `MainActivity`, add this button to `MainScreen`

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
}
```

We can create a test. For now, let's just open the screen for making a call, enter some number and click on the button

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
    }
}
```

Let's run the test. Test passed successfully.

Depending on whether you have given permission or not, you may see a dialog asking permission to make calls.

At this stage, we have checked the operation of our screen, that it is possible to enter a number and click on the button, but we have not checked in any way whether a call is being made to the entered number or not. To check if a call is currently in progress, you can use `AudioManager`, this is done as follows:

```kotlin
val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
```

We can add this check in a separate step:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Check phone is calling") {
            val manager = device.context.getSystemService(AudioManager::class.java)
            Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
        }
    }
}
```

!!! info
Before running the test, remove the application from the device or revoke permissions using the adb shell command. Also make sure you are running the test on a device with API 23 and higher.

Let's run the test. Test failed.

This happened because after clicking on the button, the user was asked for permission. No one gave this permission, and the next screen was not opened.

## Testing with the TestRule

There are several options for solving the problem. The first option is to use `GrantPermissionRule`. The essence of this method is that we create a list of permissions that will be automatically allowed on the device under test.

To do this, we add a new rule before the test method:

```kotlin
@get:Rule
val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
    android.Manifest.permission.CALL_PHONE
)
```

In the `grant` method, in parentheses, we list all the required permissions separated by commas, in this case there is only one, so we leave it as it is. Then the whole test code will look like this:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CALL_PHONE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Check phone is calling") {
            val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
        }
    }
}

```

!!! info
Remember to revoke all permissions from the app or remove it from the device before running the test.

Let's run the test. In some cases, this test will pass, and in others it will not. We will now analyze the reason.

## FlakySafely for assertions

Remember the lesson about the `flakySafely` method. There we talked about the fact that in case of failure, all checks in Kaspresso will be restarted within a certain timeout.

In our case, we start the call and the next step is to check that the phone is really ringing. We do this through the `Assert.assertTrue(…)` method. Sometimes the device manages to dial the number before this check, and sometimes it does not. It seems that in such a situation the `flakySafely` method should work and the check should be carried out again within ten seconds, but for some reason this does not happen.

The fact is that all checks of view-elements in Kaspresso (isVisible, isClickable ...) "under the hood" use the `flakySafely` method, but if we ourselves call various checks through `assert`, then `flakySafely` will not be used and if the check fails, the test will immediately finished with failure.

Cases like this are another example of when you should explicitly call `flakySafely`

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CALL_PHONE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }
}

```
Now the test works, but it has several problems.

Firstly, after the end of the test, the call to the subscriber is still ongoing on the device. Let's add the `before` and `after` sections and in the section that runs after the test, complete the call. This can be done with the following code: `device.phone.cancelCall("111")`. This method works through adb commands, so do not forget to start the adb server.

Theoretically, you could put the call reset in a separate step and run it as the last step without moving it to the after section. But this would be a bad decision, because if any step fails and the test fails, then the device will continue the call and never reset. The advantage of the after section is that the code inside this block will be executed regardless of the result of the test.

In order not to duplicate the same number in two places, let's move it to a separate variable, then the test code will look like this:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityTest : TestCase() {

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CALL_PHONE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }
}
```

Now, after the test is completed, the call ends.

The second problem is that when using `GrantPermissionRule` we can only check the application in the state where the user has given the permission. At the same time, it is possible that the developers did not foresee the option when the permission request was rejected, then the result may be unexpected up to the point that the application will crash. We need to check these scenarios too, but using `GrantPermissionRule` for this will not work, because in this case the permission will always be approved, and in tests we will never know what the behavior will be if the request is denied.

## Testing with Device.Permissions

One of the solutions to the problem is to interact with the dialog using KAutomator, having previously found all the necessary interface elements, but this is not very convenient, and a much more convenient way has been added to the Kaspresso - `Device.Permissions`. It makes it very easy to check permission dialogs, as well as accept or reject them.

Therefore, instead of `Rule` we will use the `Permissions` object, which can be obtained from `Device`. Let's do this in a separate class so that you can keep both test cases. The class in which we are currently working will be renamed to `MakeCallActivityRuleTest`.

To do this, right-click on the file name and select `Refactor` -> `Rename`

<img src="../images/permissions/rename.png" alt="Rename" />

And enter a new class name:

<img src="../images/permissions/rename_2.png" alt="Rename" />

And create a new class `MakeCallActivityDevicePermissionsTest`. Code can be copied from the current test, except for `GrantPermissionRule`

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {
    
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }
}
```

If we run the test now, it will fail because we do not have needed permission to make calls. Let's add one more step in which we will give the appropriate permission through `device.permissions`. After specifying an object, you can put a dot and see what methods it has:

<img src="../images/permissions/device_perm_methods.png" alt="Device permission methods"/>

It is possible to check if the dialog is displayed, as well as to reject or grant permission.

```kotlin
step("Accept permission") {
    Assert.assertTrue(device.permissions.isDialogVisible())
    device.permissions.allowViaDialog()
}
```

In this way, we will make sure that the dialog is displayed and agree to making calls.

!!! info
As a reminder, the dialog will be shown on Android API version 23 and above, how to run these tests on earlier versions, we will explain at the end of this tutorial.

Here we have written `device.permissions` twice, let's shorten the code a bit by using the [apply](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/apply.html) function. And let's move the check through `assert` to the `flakySafely` method. Then the whole test code will look like this:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Accept permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    allowViaDialog()
                }
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }
}

```

Let's run the test. Test passed successfully.

Now we can easily write a test for the fact that the call is not made if permission was not given. To do this, instead of `allowViaDialog` you need to specify `denyViaDialog`.

You also need to change the checks in the test itself, and do not forget to remove the code from the `after` function in the new method, since after the permission is denied, the call will not be made, and after the test, you no longer need to reset the call.

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Accept permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    allowViaDialog()
                }
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }

    @Test
    fun checkCallIfPermissionDenied() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Deny permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    denyViaDialog()
                }
            }
        }
        step("Check stay on the same screen") {
            MakeCallActivityScreen {
                inputNumber.isDisplayed()
                makeCallButton.isDisplayed()
            }
        }
    }
}
```

## Testing against different API versions

On modern versions of the Android OS (API 23 and higher), permissions are requested from the user during the application through a dialog. But in earlier versions, they were requested at the time of installation of the application, and during operation it was considered that the user agreed with all the required permissions.

Therefore, if you run the test on devices with API below version 23, then there will be no request for permissions, so the dialog check is not required.

In the test using `GrantPermissionRule` no changes are required, on older versions the permission is always there, so this annotation will not affect the test in any way. But in the test using `device.permissions`, changes need to be made, because here we are explicitly checking the operation of the dialog.

There are several options here. Firstly, on such devices it makes no sense to test the application if the permission was denied, so this test should simply be skipped. To do this, you can use the `@SuppressSdk` annotation. Then the code of the `checkCallIfPermissionDenied` method will change to:

```kotlin
@SdkSuppress(minSdkVersion = 23)
@Test
fun checkCallIfPermissionDenied() = run {
    step("Open make call activity") {
        MainScreen {
            makeCallActivityButton {
                isVisible()
                isClickable()
                click()
            }
        }
    }
    step("Check UI elements") {
        MakeCallActivityScreen {
            inputNumber.isVisible()
            inputNumber.hasHint(R.string.phone_number_hint)
            makeCallButton.isVisible()
            makeCallButton.isClickable()
            makeCallButton.hasText(R.string.make_call_btn)
        }
    }
    step("Try to call number") {
        MakeCallActivityScreen {
            inputNumber.replaceText(testNumber)
            makeCallButton.click()
        }
    }
    step("Deny permission") {
        device.permissions.apply {
            flakySafely {
                Assert.assertTrue(isDialogVisible())
                denyViaDialog()
            }
        }
    }
    step("Check stay on the same screen") {
        MakeCallActivityScreen {
            inputNumber.isDisplayed()
            makeCallButton.isDisplayed()
        }
    }
}
```

Now this test will be performed only on new versions of the Android OS, and on older versions it will be skipped.

The second solution for the problem is to skip certain steps or replace them with others, depending on the API level. For example, in the `checkSuccessCall` method on old devices, we can skip the step with checking the dialog, for this use the following code:

```kotlin
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    step("Accept permission") {
        device.permissions.apply {
            flakySafely {
                Assert.assertTrue(isDialogVisible())
                allowViaDialog()
            }
        }
    }
}
```

The rest of the code can be left untouched and the test will run successfully on both new and old devices, just in one case permission will be requested, in the other it won't.

The final test code will now look like this:

```kotlin
package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.SdkSuppress
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            step("Accept permission") {
                device.permissions.apply {
                    flakySafely {
                        Assert.assertTrue(isDialogVisible())
                        allowViaDialog()
                    }
                }
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }

    @SdkSuppress(minSdkVersion = 23)
    @Test
    fun checkCallIfPermissionDenied() = run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Deny permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    denyViaDialog()
                }
            }
        }
        step("Check stay on the same screen") {
            MakeCallActivityScreen {
                inputNumber.isDisplayed()
                makeCallButton.isDisplayed()
            }
        }
    }
}

```

## Summary

In this tutorial, we have looked at two options for working with Permissions: `GrantPermissionRule` and `device.permissions`.

We also learned that the second option is preferable for a number of reasons:

<ol>
<li>The Permissions object makes it possible to test whether a dialog requesting permission is displayed</li>
<li>When using Permissions, we can test the application's behavior not only when accepting a permission, but also when denying it</li>
<li>Tests with the GrantPermissionRule will fail if the permission was previously denied. You will need to reinstall the application or cancel previously issued permissions through the adb shell command </li>
<li>If you revoke the permission using the adb shell command while the test is running, then the test will work correctly if the Permissions object is used, but a crash will occur if the GrantPermissionRule is used</li>
</ol>

