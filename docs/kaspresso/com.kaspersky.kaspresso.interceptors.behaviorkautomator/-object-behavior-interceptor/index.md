//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator](../index.md)/[ObjectBehaviorInterceptor](index.md)



# ObjectBehaviorInterceptor  
 [androidJvm] 

The derived from [KautomatorBehaviorInterceptor](../-kautomator-behavior-interceptor/index.md) interface for intercepting UiObjectInteraction.perform and UiObjectInteraction.check behavior.

interface [ObjectBehaviorInterceptor](index.md) : [KautomatorBehaviorInterceptor](../-kautomator-behavior-interceptor/index.md)<UiObjectInteraction, UiOperation<UiObject2>, UiOperation<UiObject2>>    


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| interceptCheck| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff and actually check an interaction with element.<br><br>  <br>Content  <br>abstract override fun <T> interceptCheck(interaction: UiObjectInteraction, assertion: UiOperation<UiObject2>, activity: () -> T): T  <br><br><br>
| interceptPerform| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff and actually perform an interaction with element.<br><br>  <br>Content  <br>abstract override fun <T> interceptPerform(interaction: UiObjectInteraction, action: UiOperation<UiObject2>, activity: () -> T): T  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [AutoScrollObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll/-auto-scroll-object-behavior-interceptor/index.md)
| [FailureLoggingObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure/-failure-logging-object-behavior-interceptor/index.md)
| [FlakySafeObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety/-flaky-safe-object-behavior-interceptor/index.md)
| [UiObjectLoaderBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader/-ui-object-loader-behavior-interceptor/index.md)
| [SystemDialogSafetyObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety/-system-dialog-safety-object-behavior-interceptor/index.md)

