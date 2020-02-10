# Device

**Device** is a provider of managers for all off-screen work. 

### **Structure** 

All examples are located in [device_tests](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests).
**Device** provides these managers: 

1. `apps` allows to install or uninstall applications. Uses `adb install` and `adb uninstall` commands. See the example [DeviceAppSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceAppSampleTest.kt).
2. `activities` is an interface to work with currently resumed Activities. AdbServer not required. See the example [DeviceActivitiesSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceActivitiesSampleTest.kt).
3. `files` provides the possibility of pushing or removing files from the device. Uses `adb push` and `adb rm` commands and does not require `android.permission.WRITE_EXTERNAL_STORAGE` permission. See the example [DeviceFilesSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceFilesSampleTest.kt). 
4. `internet` allows toggling WiFi and network data transfer settings. Be careful of using this interface, WiFi settings changes could not work with some Android versions. See the example [DeviceInternetSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceInternetSampleTest.kt).
5. `keyboard` is an interface to send key events via adb. Use it only when Espresso or UiAutomator are not appropriate (e.g. screen is locked). See the example [DeviceKeyboardSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceKeyboardSampleTest.kt).
6. `location` emulates fake location and allows to toggle GPS setting. See the example [DeviceLocationSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceLocationSampleTest.kt).
7. `phone` allows to emulate incoming calls and receive SMS messages. Works only on emulators since uses `adb emu` commands. See the example [DevicePhoneSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DevicePhoneSampleTest.kt).
8. `screenshots` is an interface screenshots of currently resumed activity. Requires `android.permission.WRITE_EXTERNAL_STORAGE permission`. See the example [DeviceScreenshotSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceScreenshotSampleTest.kt).
9. `accessibility` allows to enable or disable accessibility services. Available since api 24. See the example [DeviceAccessibilitySampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceAccessibilitySampleTest.kt).
10. `permissions` provides the possibility of allowing or denying permission requests via default Android permission dialog. See the example [DevicePermissionsSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DevicePermissionsSampleTest.kt).
11. `hackPermissions` provides the possibility of allowing any permission requests without default Android permission dialog. See the example [DeviceHackPermissionsSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceHackPermissionsSampleTest.kt).
12. `exploit` allows to rotate device or press system buttons. See the example [DeviceExploitSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceExploitSampleTest.kt).
13. `language` allows to switch language. See the example [DeviceLanguageSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceLanguageSampleTest.kt).
14. `logcat` provides access to adb logcat. See the example [DeviceLogcatSampleTest](../sample/src/androidTest/java/com/kaspersky/kaspressample/device_tests/DeviceLogcatSampleTest.kt).
15. `uiDevice` returns an instance of ```android.support.test.uiautomator.UiDevice```. We don't recommend to use it directly because there is **Kautomator** that offers a more readable, predictable and stable API to work outside your application.

Also **Device** provides application and test contexts - `targetContext` and `context`. 

### **Usage**

Device instance is available in `BaseTestContext` scope and `BaseTestCase` via ```device``` property.
```kotlin
@Test
fun test() =
    run {
        step("Open Simple Screen") {
            activityTestRule.launchActivity(null)
  ======>   device.screenshots.take("Additional_screenshot")  <======

            MainScreen {
                simpleButton {
                    isVisible()
                    click()
                }
            }
        }
        // ....
}
```

### **Limitations**

Most of the features that **Device** provides use of adb commands and requires AdbServer to be run. 
Some of them, such as call emulation or SMS receiving, could be executed only on emulator. All such methods are marked by annotation ```@@AdbServerMust```.

All the methods which use adb commands require `android.permission.INTERNET` permission. 
For more information, see methods JavaDoc description and [AdbServer help](./04.%20AdbServer.md)
