//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.server](../index.md)/[AdbServer](index.md)



# AdbServer  
 [androidJvm] 



This is a comfortable wrapper to work with AdbServer repository.



Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java -jar path_to_file/adbserver-desktop.jar"



Important notes:

<ol><li>Real connection is established only after a call one of methods of the interface except disconnectServer(). So it's lazy wrapper. Keep it in your mind when you decide to put custom implementation od AdbServer.</li><li>Kaspresso calls disconnectServer() after each test if the connection was established during the test.</li></ol>

interface [AdbServer](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [disconnectServer](disconnect-server.md)| [androidJvm]  <br>Brief description  <br><br><br>Disconnect from AdbServer. The method is called by Kaspresso after each test.<br><br>  <br>Content  <br>abstract fun [disconnectServer](disconnect-server.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [performAdb](perform-adb.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Performs adb commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>abstract fun [performAdb](perform-adb.md)(vararg commands: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<Out [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>
| [performCmd](perform-cmd.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Executes shell commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>abstract fun [performCmd](perform-cmd.md)(vararg commands: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<Out [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>
| [performShell](perform-shell.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Performs shell commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>abstract fun [performShell](perform-shell.md)(vararg commands: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<Out [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [AdbServerImpl](../-adb-server-impl/index.md)

