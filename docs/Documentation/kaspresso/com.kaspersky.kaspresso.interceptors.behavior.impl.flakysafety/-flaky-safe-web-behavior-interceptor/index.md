//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety](../index.md)/[FlakySafeWebBehaviorInterceptor](index.md)



# FlakySafeWebBehaviorInterceptor  
 [androidJvm] 

The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor/index.md) and [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for Web.WebInteraction.perform and Web.WebInteraction.check calls.

class [FlakySafeWebBehaviorInterceptor](index.md)(**params**: [FlakySafetyParams](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md), **logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor/index.md), [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [FlakySafeWebBehaviorInterceptor](-flaky-safe-web-behavior-interceptor.md)|  [androidJvm] fun [FlakySafeWebBehaviorInterceptor](-flaky-safe-web-behavior-interceptor.md)(params: [FlakySafetyParams](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md), logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [flakySafely](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/flaky-safely.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action flaky safely.<br><br>  <br>Content  <br>open override fun <T> [flakySafely](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/flaky-safely.md)(action: () -> T): T  <br>open override fun <T> [flakySafely](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/flaky-safely.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, failureMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, action: () -> T): T  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given action invocation with the flaky safety.<br><br>  <br>Content  <br>open override fun <[T](intercept.md)> [intercept](intercept.md)(interaction: Web.WebInteraction<*>, action: () -> [T](intercept.md)): [T](intercept.md)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

