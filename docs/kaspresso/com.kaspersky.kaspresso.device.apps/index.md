//[kaspresso](../index.md)/[com.kaspersky.kaspresso.device.apps](index.md)



# Package com.kaspersky.kaspresso.device.apps  


## Types  
  
|  Name|  Summary| 
|---|---|
| [Apps](-apps/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>The interface to work with installer, launcher and package manager.<br><br><br><br>Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar" Methods demanding to use AdbServer in the default implementation of this interface are marked.     But nobody can't deprecate you to write implementation that doesn't require AdbServer.<br><br><br><br>  <br>Content  <br>interface [Apps](-apps/index.md)  <br><br><br>
| [AppsImpl](-apps-impl/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The implementation of the [Apps](-apps/index.md) interface.<br><br>  <br>Content  <br>class [AppsImpl](-apps-impl/index.md)(**logger**: [UiTestLogger](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), **context**: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), **uiDevice**: UiDevice, **adbServer**: [AdbServer](../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [Apps](-apps/index.md)  <br><br><br>

