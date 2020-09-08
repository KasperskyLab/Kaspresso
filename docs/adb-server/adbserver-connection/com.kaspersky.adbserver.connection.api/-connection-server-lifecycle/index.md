//[adbserver-connection](../../index.md)/[com.kaspersky.adbserver.connection.api](../index.md)/[ConnectionServerLifecycle](index.md)



# ConnectionServerLifecycle  
 [jvm] interface [ConnectionServerLifecycle](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [jvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [jvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [onDisconnectedBySocketProblems](on-disconnected-by-socket-problems.md)| [jvm]  <br>Content  <br>abstract fun [onDisconnectedBySocketProblems](on-disconnected-by-socket-problems.md)()  <br><br><br>
| [onExecutedTask](on-executed-task.md)| [jvm]  <br>Content  <br>abstract fun [onExecutedTask](on-executed-task.md)(command: Command, commandResult: CommandResult)  <br><br><br>
| [onReceivedTask](on-received-task.md)| [jvm]  <br>Content  <br>abstract fun [onReceivedTask](on-received-task.md)(command: Command)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [jvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

