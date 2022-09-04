//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.intercept.delegate](../index.md)/[UiDelegate](index.md)



# UiDelegate  
 [androidJvm] 



Base delegate interface for Kautomator.



Provides functionality of aggregating interceptors and invoking them on check and perform invocations.



interface [UiDelegate](index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>   


## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)| <br><br><br><br>
  


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [globalInterceptor](global-interceptor.md)| [androidJvm]  <br>Content  <br>abstract fun [globalInterceptor](global-interceptor.md)(): [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>?  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptCheck](intercept-check.md)| [androidJvm]  <br>Brief description  <br><br><br>Runs the interceptors available for the given delegate during the check operation.<br><br>  <br>Content  <br>open fun [interceptCheck](intercept-check.md)(assertion: [Assertion](index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [interceptPerform](intercept-perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Runs the interceptors available for the given delegate during the execute operation.<br><br>  <br>Content  <br>open fun [interceptPerform](intercept-perform.md)(action: [Action](index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [screenInterceptors](screen-interceptors.md)| [androidJvm]  <br>Content  <br>abstract fun [screenInterceptors](screen-interceptors.md)(): [Iterable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)<[UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>>  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [interaction](index.md#com.kaspersky.components.kautomator.intercept.delegate/UiDelegate/interaction/#/PointingToDeclaration/)|  [androidJvm] abstract val [interaction](index.md#com.kaspersky.components.kautomator.intercept.delegate/UiDelegate/interaction/#/PointingToDeclaration/): [Interaction](index.md)   <br>
| [interceptor](index.md#com.kaspersky.components.kautomator.intercept.delegate/UiDelegate/interceptor/#/PointingToDeclaration/)|  [androidJvm] abstract var [interceptor](index.md#com.kaspersky.components.kautomator.intercept.delegate/UiDelegate/interceptor/#/PointingToDeclaration/): [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>?   <br>


## Inheritors  
  
|  Name| 
|---|
| [UiDeviceInteractionDelegate](../-ui-device-interaction-delegate/index.md)
| [UiObjectInteractionDelegate](../-ui-object-interaction-delegate/index.md)

