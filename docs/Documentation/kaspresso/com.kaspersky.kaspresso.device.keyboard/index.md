//[kaspresso](../index.md)/[com.kaspersky.kaspresso.device.keyboard](index.md)



# Package com.kaspersky.kaspresso.device.keyboard  


## Types  
  
|  Name|  Summary| 
|---|---|
| [Keyboard](-keyboard/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Use this API only if neither Espresso, nor UiAutomator work for some reasons. E.g. because of animations.<br><br><br><br>Using this API is highly discouraged. Consider to use the built-in API whenever it's possible as it described in the documentation for methods.<br><br><br><br>Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar" Methods demanding to use AdbServer in the default implementation of this interface are marked.     But nobody can't deprecate you to write implementation that doesn't require AdbServer.<br><br><br><br>  <br>Content  <br>interface [Keyboard](-keyboard/index.md)  <br><br><br>
| [KeyboardImpl](-keyboard-impl/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The implementation of the [Keyboard](-keyboard/index.md) interface.<br><br>  <br>Content  <br>class [KeyboardImpl](-keyboard-impl/index.md)(**adbServer**: [AdbServer](../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [Keyboard](-keyboard/index.md)  <br><br><br>

