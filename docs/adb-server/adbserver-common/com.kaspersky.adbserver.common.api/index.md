//[adbserver-common](../index.md)/[com.kaspersky.adbserver.common.api](index.md)



# Package com.kaspersky.adbserver.common.api  


## Types  
  
|  Name|  Summary| 
|---|---|
| [Command](-command/index.md)| [jvm]  <br>Brief description  <br><br><br>Command to execute by AdbServer<br><br>  <br>Content  <br>abstract class [Command](-command/index.md)(**body**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Serializable](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)  <br><br><br>
| [CommandExecutor](-command-executor/index.md)| [jvm]  <br>Brief description  <br><br><br>Executor of terminal commands.<br><br>  <br>Content  <br>interface [CommandExecutor](-command-executor/index.md)  <br><br><br>
| [CommandResult](-command-result/index.md)| [jvm]  <br>Brief description  <br><br><br>Result of command's executing<br><br>  <br>Content  <br>data class [CommandResult](-command-result/index.md)(**status**: [ExecutorResultStatus](-executor-result-status/index.md), **description**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **serviceInfo**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [Serializable](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)  <br><br><br>
| [ExecutorResultStatus](-executor-result-status/index.md)| [jvm]  <br>Content  <br>enum [ExecutorResultStatus](-executor-result-status/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)<[ExecutorResultStatus](-executor-result-status/index.md)>   <br><br><br>

