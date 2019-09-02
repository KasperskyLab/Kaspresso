## **Device**

**Device** is a provider of managers for all off-screen work. 

### **Structure** 

**Device** provides these managers: 

1. `uiDevice` returns an instance of [android.support.test.uiautomator.UiDevice].
2. `apps` allows to install or uninstall applications.
3. `activities` is an interface to work with currently resumed Activities.
4. `files` provides possibility of pushing or removing files from device. Uses `adb push` and `adb rm` commands and does not require `android.permission.WRITE_EXTERNAL_STORAGE` permission. 
5. `internet` allows to toggle WiFi and network data transfer settings.
6. `keyboard` is an interface to send key events via adb. Use it only when Espresso or UiAutomator are not appropriate (e.g. screen is locked).
7. `location` emulates fake location and allows to toggle GPS setting.
8. `phone` allows to emulate incoming calls and receive SMS messages. Works only on emulators since uses `adb emu` commands.   
9. `screenshots` is an interface screenshots of currently resumed activity. Requires `android.permission.WRITE_EXTERNAL_STORAGE permission`. 
10. `accessibility` allows to enable or disable accessibility services.
11. `permissions` provides possibility of allowing or denying permission requests via default Android permission dialog. 
12. `exploit` allows to rotate device or press system buttons.

Also **Device** provides application and test contexts - `targetContext` and `context`. 

### **Usage**

Device instance available in `BaseTestContext` scope. So, you can use it in test cases or in methods that extend `BaseTestContext` class.

### **Limitations**

Most of the features that **Device** provides use adb commands. Some of them could be run only on emulator. 
Such as call emulation or sms receiving. 

All the methods which use adb commands require `android.permission.INTERNET` permission. For more information, see methods JavaDoc description.