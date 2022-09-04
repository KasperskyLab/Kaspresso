//[adbserver-connection](../../index.md)/[com.kaspersky.adbserver.connection.api](../index.md)/[ConnectionFactory](index.md)



# ConnectionFactory  
 [jvm] 

The singleton to provide convenient methods to create Server and Client

object [ConnectionFactory](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [createClient](create-client.md)| [jvm]  <br>Content  <br>fun [createClient](create-client.md)(socketCreation: () -> [Socket](https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html), logger: Logger, connectionClientLifecycle: [ConnectionClientLifecycle](../-connection-client-lifecycle/index.md)): [ConnectionClient](../-connection-client/index.md)  <br><br><br>
| [createServer](create-server.md)| [jvm]  <br>Content  <br>fun [createServer](create-server.md)(socketCreation: () -> [Socket](https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html), commandExecutor: CommandExecutor, logger: Logger, connectionServerLifecycle: [ConnectionServerLifecycle](../-connection-server-lifecycle/index.md)): [ConnectionServer](../-connection-server/index.md)  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [jvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [jvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [jvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

