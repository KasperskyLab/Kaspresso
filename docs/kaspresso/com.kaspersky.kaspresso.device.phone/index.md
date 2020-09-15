//[kaspresso](../index.md)/[com.kaspersky.kaspresso.device.phone](index.md)



# Package com.kaspersky.kaspresso.device.phone  


## Types  
  
|  Name|  Summary| 
|---|---|
| [Phone](-phone/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>The interface to work with telephony.<br><br><br><br>Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar" Methods demanding to use AdbServer in the default implementation of this interface are marked.     But nobody can't deprecate you to write implementation that doesn't require AdbServer.<br><br><br><br>  <br>Content  <br>interface [Phone](-phone/index.md)  <br><br><br>
| [PhoneImpl](-phone-impl/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The implementation of the [Phone](-phone/index.md) interface.<br><br>  <br>Content  <br>class [PhoneImpl](-phone-impl/index.md)(**adbServer**: [AdbServer](../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [Phone](-phone/index.md)  <br><br><br>

