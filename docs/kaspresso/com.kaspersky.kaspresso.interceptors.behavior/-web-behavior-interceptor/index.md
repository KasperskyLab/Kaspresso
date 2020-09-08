//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behavior](../index.md)/[WebBehaviorInterceptor](index.md)



# WebBehaviorInterceptor  
 [androidJvm] 

The derived from [BehaviorInterceptor](../-behavior-interceptor/index.md) interface for intercepting Web.WebInteraction.perform and Web.WebInteraction.check behavior.

interface [WebBehaviorInterceptor](index.md) : [BehaviorInterceptor](../-behavior-interceptor/index.md)<Web.WebInteraction<*>>    


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| intercept| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff and actually perform an interaction with element.<br><br>  <br>Content  <br>abstract override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [AutoScrollWebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll/-auto-scroll-web-behavior-interceptor/index.md)
| [FailureLoggingWebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.failure/-failure-logging-web-behavior-interceptor/index.md)
| [FlakySafeWebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety/-flaky-safe-web-behavior-interceptor/index.md)
| [SystemDialogSafetyWebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-web-behavior-interceptor/index.md)

