//[adbserver-connection](../../index.md)/[com.kaspersky.adbserver.connection.api](../index.md)/[BaseConnection](index.md)



# BaseConnection  
 [jvm] 

Interface for common Connection

interface [BaseConnection](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [jvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [jvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isConnected](is-connected.md)| [jvm]  <br>Brief description  <br><br><br>Return status of the connection<br><br>  <br>Content  <br>abstract fun [isConnected](is-connected.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [jvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [tryConnect](try-connect.md)| [jvm]  <br>Brief description  <br><br><br>Try to connect. The connection must be established by this method before any other operations<br><br>  <br>Content  <br>abstract fun [tryConnect](try-connect.md)()  <br><br><br>
| [tryDisconnect](try-disconnect.md)| [jvm]  <br>Brief description  <br><br><br>If the connection is not needed then call this method to close all channels<br><br>  <br>Content  <br>abstract fun [tryDisconnect](try-disconnect.md)()  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [ConnectionClient](../-connection-client/index.md)
| [ConnectionServer](../-connection-server/index.md)

