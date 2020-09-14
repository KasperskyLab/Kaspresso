//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.flakysafety](../index.md)/[ContinuouslyProvider](index.md)



# ContinuouslyProvider  
 [androidJvm] 

The interface to provide the flaky safety functionality.

interface [ContinuouslyProvider](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [continuously](continuously.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Invokes the given action during set timeout.<br><br><br><br>It can be helpful for checking of negative scenarios.<br><br><br><br>In opposite to [FlakySafetyProvider.flakySafely](../-flaky-safety-provider/flaky-safely.md) it does not skip last attempt after first success and throws inside exception outside as soon as it was thrown<br><br><br><br>  <br>Content  <br>abstract fun <[T](continuously.md)> [continuously](continuously.md)(action: () -> [T](continuously.md)): [T](continuously.md)  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br><br><br>Invokes the given action during set timeout.<br><br><br><br>It can be helpful for checking of negative scenarios.<br><br><br><br>In opposite to [FlakySafetyProvider.flakySafely](../-flaky-safety-provider/flaky-safely.md) it does not skips last attempt after first success and throws inside exception outside as soon as it was thrown<br><br><br><br>  <br>Content  <br>abstract fun <[T](continuously.md)> [continuously](continuously.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, failureMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, action: () -> [T](continuously.md)): [T](continuously.md)  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [ContinuouslyProviderImpl](../-continuously-provider-impl/index.md)
| [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md)

