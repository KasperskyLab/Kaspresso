//[kaspresso](../index.md)/[com.kaspersky.kaspresso.device.server](index.md)



# Package com.kaspersky.kaspresso.device.server  


## Types  
  
|  Name|  Summary| 
|---|---|
| [AdbServer](-adb-server/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>This is a comfortable wrapper to work with AdbServer repository.<br><br><br><br>Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java -jar path_to_file/adbserver-desktop.jar"<br><br><br><br>Important notes:<br><br><ol><li>Real connection is established only after a call one of methods of the interface except disconnectServer(). So it's lazy wrapper. Keep it in your mind when you decide to put custom implementation od AdbServer.</li><li>Kaspresso calls disconnectServer() after each test if the connection was established during the test.</li></ol><br><br>  <br>Content  <br>interface [AdbServer](-adb-server/index.md)  <br><br><br>
| [AdbServerImpl](-adb-server-impl/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The implementation of [AdbServer](-adb-server/index.md) interface. Encapsulates all work with adb server. Please, pay attention to the field AdbServerLogsType that provides several types to show logs from adb-server (device part). More details are available in AdbServerLogsType.<br><br>  <br>Content  <br>class [AdbServerImpl](-adb-server-impl/index.md)(**logLevel**: LogLevel, **logger**: [UiTestLogger](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [AdbServer](-adb-server/index.md)  <br><br><br>

