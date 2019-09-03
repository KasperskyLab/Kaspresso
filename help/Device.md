## **Device**

**Device** is a provider of managers for all off-screen work. 

### **Structure** 

**Device** provides these managers: 

1. `uiDevice` returns an instance of [android.support.test.uiautomator.UiDevice].
2. `apps` allows to install or uninstall applications. Uses `adb install` and `adb uninstall` commands. See example [com.kaspersky.kaspressample.device_tests.DeviceAppSampleTest]
3. `activities` is an interface to work with currently resumed Activities. AdbServer not required. [com.kaspersky.kaspressample.device_tests.DeviceActivitiesSampleTest]
4. `files` provides possibility of pushing or removing files from device. Uses `adb push` and `adb rm` commands and does not require `android.permission.WRITE_EXTERNAL_STORAGE` permission. [com.kaspersky.kaspressample.device_tests.DeviceFilesSampleTest] 
5. `internet` allows to toggle WiFi and network data transfer settings. Be careful of using this interface, WiFi settings changes could not work with some Android versions. [com.kaspersky.kaspressample.device_tests.DeviceInternetSampleTest] 
6. `keyboard` is an interface to send key events via adb. Use it only when Espresso or UiAutomator are not appropriate (e.g. screen is locked). [com.kaspersky.kaspressample.device_tests.DeviceKeyboardSampleTest]
7. `location` emulates fake location and allows to toggle GPS setting. [com.kaspersky.kaspressample.device_tests.DeviceLocationSampleTest]]
8. `phone` allows to emulate incoming calls and receive SMS messages. Works only on emulators since uses `adb emu` commands. [com.kaspersky.kaspressample.device_tests.DevicePhoneSampleTest]  
9. `screenshots` is an interface screenshots of currently resumed activity. Requires `android.permission.WRITE_EXTERNAL_STORAGE permission`. [com.kaspersky.kaspressample.device_tests.DeviceFilesSampleTest]
10. `accessibility` allows to enable or disable accessibility services. Available since api 24. [com.kaspersky.kaspressample.device_tests.DeviceAccessibilitySampleTest]
11. `permissions` provides possibility of allowing or denying permission requests via default Android permission dialog. [com.kaspersky.kaspressample.device_tests.DevicePermissionsSampleTest]
12. `exploit` allows to rotate device or press system buttons. [com.kaspersky.kaspressample.device_tests.DevicePermissionsSampleTest]

Also **Device** provides application and test contexts - `targetContext` and `context`. 

### **Usage**

Device instance available in `BaseTestContext` scope. So, you can use it in test cases or in methods that extend `BaseTestContext` class.

### **Limitations**

Most of the features that **Device** provides use adb commands and requires AdbServer to be run. 
Some of them, such as call emulation or sms receiving, could be executed only on emulator. 

All the methods which use adb commands require `android.permission.INTERNET` permission. For more information, see methods JavaDoc description and **AdbServer** part of [help/HowToWrite.md] doc.