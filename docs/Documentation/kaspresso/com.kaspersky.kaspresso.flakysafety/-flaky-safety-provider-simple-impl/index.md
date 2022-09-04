//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.flakysafety](../index.md)/[FlakySafetyProviderSimpleImpl](index.md)



# FlakySafetyProviderSimpleImpl  
 [androidJvm] 

The implementation of the [FlakySafetyProvider](../-flaky-safety-provider/index.md) interface. By default, this implementation is using to struggle with flaky UI libs inside a View

class [FlakySafetyProviderSimpleImpl](index.md)(**params**: [FlakySafetyParams](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md), **logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [FlakySafetyProvider](../-flaky-safety-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [FlakySafetyProviderSimpleImpl](-flaky-safety-provider-simple-impl.md)|  [androidJvm] fun [FlakySafetyProviderSimpleImpl](-flaky-safety-provider-simple-impl.md)(params: [FlakySafetyParams](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md), logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [flakySafely](flaky-safely.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action flaky safely.<br><br>  <br>Content  <br>open override fun <[T](flaky-safely.md)> [flakySafely](flaky-safely.md)(action: () -> [T](flaky-safely.md)): [T](flaky-safely.md)  <br>open override fun <[T](flaky-safely.md)> [flakySafely](flaky-safely.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, failureMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, action: () -> [T](flaky-safely.md)): [T](flaky-safely.md)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

