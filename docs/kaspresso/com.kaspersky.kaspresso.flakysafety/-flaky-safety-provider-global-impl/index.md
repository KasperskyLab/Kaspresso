//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.flakysafety](../index.md)/[FlakySafetyProviderGlobalImpl](index.md)



# FlakySafetyProviderGlobalImpl  
 [androidJvm] 



The implementation of the [FlakySafetyProvider](../-flaky-safety-provider/index.md) interface.



By default, this implementation is using in tests (not inside a View).



Why? Have a glance at the example:

someView {  
    flakySafety(timeout = 20.sec) {  
        isVisible()  
    }  
}

In this case, Kaspresso tries to execute someView.isVisible() for 10 seconds (default timeout for FlakySafety interceptors). After 10 seconds someView.isVisible() fails, throws an exception and writes info in logs. This exception is catching by extra ``flakySafety`` that tries to repeat.



But such log messages make logs dirty and confuse a developer or tester.



Besides, extra ``flakySafety`` can contain more actions, that's why there is probability of not having time to execute entire block. Honestly, we don't recommend to put into flakySafety block more than one concrete action.



All of this pushed us to write special implementation of [FlakySafetyProvider](../-flaky-safety-provider/index.md).



The algorithm:

<ol><li>[FlakySafetyProviderGlobalImpl](index.md) removes all interceptors implementing FlakySafety behavior from Kakao/Kautomator before the action.</li><li>[FlakySafetyProviderGlobalImpl](index.md) executes the entire! action inside own FlakySafety.</li><li>[FlakySafetyProviderGlobalImpl](index.md) restores all interceptors implementing FlakySafety behavior from Kakao/Kautomator after the action.</li></ol>

Such behavior allows us to observe more predictable log information (a developer will see only one and correct error message in described above example) and avoids potentially inconsistent execution.



class [FlakySafetyProviderGlobalImpl](index.md)(**kaspresso**: [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md)) : [FlakySafetyProvider](../-flaky-safety-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [FlakySafetyProviderGlobalImpl](-flaky-safety-provider-global-impl.md)|  [androidJvm] fun [FlakySafetyProviderGlobalImpl](-flaky-safety-provider-global-impl.md)(kaspresso: [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [flakySafely](flaky-safely.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action flaky safely.<br><br>  <br>Content  <br>open override fun <[T](flaky-safely.md)> [flakySafely](flaky-safely.md)(action: () -> [T](flaky-safely.md)): [T](flaky-safely.md)  <br>open override fun <[T](flaky-safely.md)> [flakySafely](flaky-safely.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, failureMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, action: () -> [T](flaky-safely.md)): [T](flaky-safely.md)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

