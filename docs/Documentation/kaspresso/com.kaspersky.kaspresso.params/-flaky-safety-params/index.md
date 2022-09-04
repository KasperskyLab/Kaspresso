//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.params](../index.md)/[FlakySafetyParams](index.md)



# FlakySafetyParams  
 [androidJvm] 

The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md) parameters.

class [FlakySafetyParams](index.md)   


## Types  
  
|  Name|  Summary| 
|---|---|
| [Companion](-companion/index.md)| [androidJvm]  <br>Content  <br>object [Companion](-companion/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [allowedExceptions](index.md#com.kaspersky.kaspresso.params/FlakySafetyParams/allowedExceptions/#/PointingToDeclaration/)|  [androidJvm] <br><br>The set of exceptions, if caught, the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md) will continue to attempt.<br><br>val [allowedExceptions](index.md#com.kaspersky.kaspresso.params/FlakySafetyParams/allowedExceptions/#/PointingToDeclaration/): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>   <br>
| [intervalMs](index.md#com.kaspersky.kaspresso.params/FlakySafetyParams/intervalMs/#/PointingToDeclaration/)|  [androidJvm] <br><br>The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md).<br><br>var [intervalMs](index.md#com.kaspersky.kaspresso.params/FlakySafetyParams/intervalMs/#/PointingToDeclaration/): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)   <br>
| [timeoutMs](index.md#com.kaspersky.kaspresso.params/FlakySafetyParams/timeoutMs/#/PointingToDeclaration/)|  [androidJvm] <br><br>The timeout during which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-simple-impl/index.md).<br><br>var [timeoutMs](index.md#com.kaspersky.kaspresso.params/FlakySafetyParams/timeoutMs/#/PointingToDeclaration/): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)   <br>

