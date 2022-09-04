//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.server](../index.md)/[AdbServerImpl](index.md)



# AdbServerImpl  
 [androidJvm] 

The implementation of [AdbServer](../-adb-server/index.md) interface. Encapsulates all work with adb server. Please, pay attention to the field AdbServerLogsType that provides several types to show logs from adb-server (device part). More details are available in AdbServerLogsType.

class [AdbServerImpl](index.md)(**logLevel**: LogLevel, **logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [AdbServer](../-adb-server/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [AdbServerImpl](-adb-server-impl.md)|  [androidJvm] fun [AdbServerImpl](-adb-server-impl.md)(logLevel: LogLevel, logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [disconnectServer](disconnect-server.md)| [androidJvm]  <br>Brief description  <br><br><br>Disconnect from AdbServer. The method is called by Kaspresso after each test.<br><br>  <br>Content  <br>open override fun [disconnectServer](disconnect-server.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [performAdb](perform-adb.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Performs adb commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>open override fun [performAdb](perform-adb.md)(vararg commands: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<Out [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>
| [performCmd](perform-cmd.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Executes shell commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>open override fun [performCmd](perform-cmd.md)(vararg commands: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<Out [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>
| [performShell](perform-shell.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Performs shell commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>open override fun [performShell](perform-shell.md)(vararg commands: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<Out [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

