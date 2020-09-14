//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.flakysafety](../index.md)/[FlakySafetyProvider](index.md)



# FlakySafetyProvider  
 [androidJvm] 

The interface to provide the flaky safety functionality.

interface [FlakySafetyProvider](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [flakySafely](flaky-safely.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action flaky safely.<br><br>  <br>Content  <br>abstract fun <[T](flaky-safely.md)> [flakySafely](flaky-safely.md)(action: () -> [T](flaky-safely.md)): [T](flaky-safely.md)  <br>abstract fun <[T](flaky-safely.md)> [flakySafely](flaky-safely.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, failureMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, action: () -> [T](flaky-safely.md)): [T](flaky-safely.md)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [FlakySafetyProviderGlobalImpl](../-flaky-safety-provider-global-impl/index.md)
| [FlakySafetyProviderSimpleImpl](../-flaky-safety-provider-simple-impl/index.md)
| [FlakySafeDataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety/-flaky-safe-data-behavior-interceptor/index.md)
| [FlakySafeViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety/-flaky-safe-view-behavior-interceptor/index.md)
| [FlakySafeWebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety/-flaky-safe-web-behavior-interceptor/index.md)
| [FlakySafeDeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety/-flaky-safe-device-behavior-interceptor/index.md)
| [FlakySafeObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety/-flaky-safe-object-behavior-interceptor/index.md)
| [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md)

