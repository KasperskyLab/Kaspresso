//[kaspresso](../index.md)/[com.kaspersky.kaspresso.device.network](index.md)



# Package com.kaspersky.kaspresso.device.network  


## Types  
  
|  Name|  Summary| 
|---|---|
| [Network](-network/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>The interface to work with network settings.<br><br><br><br>Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar" Methods demanding to use AdbServer in the default implementation of this interface are marked.     But nobody can't deprecate you to write implementation that doesn't require AdbServer.<br><br><br><br>  <br>Content  <br>interface [Network](-network/index.md)  <br><br><br>
| [NetworkImpl](-network-impl/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The implementation of the [Network](-network/index.md) interface.<br><br>  <br>Content  <br>class [NetworkImpl](-network-impl/index.md)(**targetContext**: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), **adbServer**: [AdbServer](../com.kaspersky.kaspresso.device.server/-adb-server/index.md), **logger**: [UiTestLogger](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [Network](-network/index.md)  <br><br><br>

