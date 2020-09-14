//[adbserver-common](../../index.md)/[com.kaspersky.adbserver.common.api](../index.md)/[CommandResult](index.md)



# CommandResult  
 [jvm] 

Result of command's executing

data class [CommandResult](index.md)(**status**: [ExecutorResultStatus](../-executor-result-status/index.md), **description**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **serviceInfo**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [Serializable](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [CommandResult](-command-result.md)|  [jvm] fun [CommandResult](-command-result.md)(status: [ExecutorResultStatus](../-executor-result-status/index.md), description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), serviceInfo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [component1](component1.md)| [jvm]  <br>Content  <br>operator fun [component1](component1.md)(): [ExecutorResultStatus](../-executor-result-status/index.md)  <br><br><br>
| [component2](component2.md)| [jvm]  <br>Content  <br>operator fun [component2](component2.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [component3](component3.md)| [jvm]  <br>Content  <br>operator fun [component3](component3.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?  <br><br><br>
| [copy](copy.md)| [jvm]  <br>Content  <br>fun [copy](copy.md)(status: [ExecutorResultStatus](../-executor-result-status/index.md), description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), serviceInfo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [CommandResult](index.md)  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [jvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [jvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [jvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [description](index.md#com.kaspersky.adbserver.common.api/CommandResult/description/#/PointingToDeclaration/)|  [jvm] val [description](index.md#com.kaspersky.adbserver.common.api/CommandResult/description/#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>
| [serviceInfo](index.md#com.kaspersky.adbserver.common.api/CommandResult/serviceInfo/#/PointingToDeclaration/)|  [jvm] val [serviceInfo](index.md#com.kaspersky.adbserver.common.api/CommandResult/serviceInfo/#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?   <br>
| [status](index.md#com.kaspersky.adbserver.common.api/CommandResult/status/#/PointingToDeclaration/)|  [jvm] val [status](index.md#com.kaspersky.adbserver.common.api/CommandResult/status/#/PointingToDeclaration/): [ExecutorResultStatus](../-executor-result-status/index.md)   <br>

