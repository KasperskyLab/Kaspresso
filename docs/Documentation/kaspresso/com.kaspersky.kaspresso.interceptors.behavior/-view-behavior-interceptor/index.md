//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behavior](../index.md)/[ViewBehaviorInterceptor](index.md)



# ViewBehaviorInterceptor  
 [androidJvm] 

The derived from [BehaviorInterceptor](../-behavior-interceptor/index.md) interface for intercepting ViewInteraction.perform and ViewInteraction.check behavior.

interface [ViewBehaviorInterceptor](index.md) : [BehaviorInterceptor](../-behavior-interceptor/index.md)<ViewInteraction>    


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| intercept| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff and actually perform an interaction with element.<br><br>  <br>Content  <br>abstract override fun <T> intercept(interaction: ViewInteraction, action: () -> T): T  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [AutoScrollViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll/-auto-scroll-view-behavior-interceptor/index.md)
| [FailureLoggingViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.failure/-failure-logging-view-behavior-interceptor/index.md)
| [FlakySafeViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety/-flaky-safe-view-behavior-interceptor/index.md)
| [SystemDialogSafetyViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-view-behavior-interceptor/index.md)

