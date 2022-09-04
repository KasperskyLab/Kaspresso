//[kaspresso](../index.md)/[com.kaspersky.kaspresso.device.location](index.md)



# Package com.kaspersky.kaspresso.device.location  


## Types  
  
|  Name|  Summary| 
|---|---|
| [Location](-location/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>The interface to work with device's location.<br><br><br><br>Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar" Methods demanding to use AdbServer in the default implementation of this interface are marked.     But nobody can't deprecate you to write implementation that doesn't require AdbServer.<br><br><br><br>  <br>Content  <br>interface [Location](-location/index.md)  <br><br><br>
| [LocationImpl](-location-impl/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The implementation of the [Location](-location/index.md) interface.<br><br>  <br>Content  <br>class [LocationImpl](-location-impl/index.md)(**adbServer**: [AdbServer](../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [Location](-location/index.md)  <br><br><br>

