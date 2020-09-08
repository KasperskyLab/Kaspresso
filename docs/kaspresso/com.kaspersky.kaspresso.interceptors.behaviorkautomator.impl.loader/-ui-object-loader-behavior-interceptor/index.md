//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader](../index.md)/[UiObjectLoaderBehaviorInterceptor](index.md)



# UiObjectLoaderBehaviorInterceptor  
 [androidJvm] 

The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor/index.md) and [UiObjectLoaderProvider](../../com.kaspersky.kaspresso.uiobjectloader/-ui-object-loader-provider/index.md) interfaces. Provides system flaky safety functionality for UiObjectInteraction.perform and UiObjectInteraction.check calls.

class [UiObjectLoaderBehaviorInterceptor](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor/index.md), [UiObjectLoaderProvider](../../com.kaspersky.kaspresso.uiobjectloader/-ui-object-loader-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [UiObjectLoaderBehaviorInterceptor](-ui-object-loader-behavior-interceptor.md)|  [androidJvm] fun [UiObjectLoaderBehaviorInterceptor](-ui-object-loader-behavior-interceptor.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [handleUiObjectAbsence](../../com.kaspersky.kaspresso.uiobjectloader/-ui-object-loader-provider/handle-ui-object-absence.md)| [androidJvm]  <br>Content  <br>open override fun <T> [handleUiObjectAbsence](../../com.kaspersky.kaspresso.uiobjectloader/-ui-object-loader-provider/handle-ui-object-absence.md)(interaction: UiObjectInteraction, action: () -> T): T  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptCheck](intercept-check.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given activity invocation with the UiObject2 repeated loading.<br><br>  <br>Content  <br>open override fun <[T](intercept-check.md)> [interceptCheck](intercept-check.md)(interaction: UiObjectInteraction, assertion: UiOperation<UiObject2>, activity: () -> [T](intercept-check.md)): [T](intercept-check.md)  <br><br><br>
| [interceptPerform](intercept-perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given activity invocation with the UiObject2 repeated loading.<br><br>  <br>Content  <br>open override fun <[T](intercept-perform.md)> [interceptPerform](intercept-perform.md)(interaction: UiObjectInteraction, action: UiOperation<UiObject2>, activity: () -> [T](intercept-perform.md)): [T](intercept-perform.md)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

