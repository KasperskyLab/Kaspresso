//[adbserver-connection](../../index.md)/[com.kaspersky.adbserver.connection.api](../index.md)/[ConnectionClient](index.md)



# ConnectionClient  
 [jvm] 

BaseConnection + opportunity to execute Adb commands

interface [ConnectionClient](index.md) : [BaseConnection](../-base-connection/index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [jvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [executeCommand](execute-command.md)| [jvm]  <br>Brief description  <br><br><br>It is synchronous method to not reorder a line of commands because if commands were completed in incorrect order it may to lead inconsistent state of the app and the device<br><br>  <br>Content  <br>abstract fun [executeCommand](execute-command.md)(command: Command): CommandResult  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [jvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isConnected](../-base-connection/is-connected.md)| [jvm]  <br>Brief description  <br><br><br>Return status of the connection<br><br>  <br>Content  <br>abstract override fun [isConnected](../-base-connection/is-connected.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [jvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [tryConnect](../-base-connection/try-connect.md)| [jvm]  <br>Brief description  <br><br><br>Try to connect. The connection must be established by this method before any other operations<br><br>  <br>Content  <br>abstract override fun [tryConnect](../-base-connection/try-connect.md)()  <br><br><br>
| [tryDisconnect](../-base-connection/try-disconnect.md)| [jvm]  <br>Brief description  <br><br><br>If the connection is not needed then call this method to close all channels<br><br>  <br>Content  <br>abstract override fun [tryDisconnect](../-base-connection/try-disconnect.md)()  <br><br><br>

