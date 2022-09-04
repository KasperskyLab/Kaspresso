//[adbserver-desktop-device-connection](../../index.md)/[com.kaspersky.adbserver.desdevconnection](../index.md)/[DesktopDeviceSocketConnection](index.md)



# DesktopDeviceSocketConnection  
 [jvm] 



Please use only this interface to provide sockets for your connection between Desktop and Device



DesktopDeviceSocketConnection provides:

<ol><li>A pair of sockets according to correct setting of ports and correct forwarding of ports for Desktop and Device</li><li>A lambda to load the socket because it may be time consuming process</li></ol>

interface [DesktopDeviceSocketConnection](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [jvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getDesktopSocketLoad](get-desktop-socket-load.md)| [jvm]  <br>Content  <br>abstract fun [getDesktopSocketLoad](get-desktop-socket-load.md)(executor: CommandExecutor, logger: Logger): () -> [Socket](https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html)  <br><br><br>
| [getDeviceSocketLoad](get-device-socket-load.md)| [jvm]  <br>Content  <br>abstract fun [getDeviceSocketLoad](get-device-socket-load.md)(logger: Logger): () -> [Socket](https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [jvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [jvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

