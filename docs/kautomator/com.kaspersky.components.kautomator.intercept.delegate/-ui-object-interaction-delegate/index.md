//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.intercept.delegate](../index.md)/[UiObjectInteractionDelegate](index.md)



# UiObjectInteractionDelegate  
 [androidJvm] 

Delegation class for androidx.test.uiautomator.UiObject2. Wraps all available public calls and intercepts into [check](check.md) and [perform](perform.md).

class [UiObjectInteractionDelegate](index.md)(**device**: UiDevice, **selector**: [UiViewSelector](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md), **description**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [UiDelegate](../-ui-delegate/index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>    


## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| [UiDelegate](../-ui-delegate/index.md)| <br><br><br><br>
| [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)| <br><br><br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [UiObjectInteractionDelegate](-ui-object-interaction-delegate.md)|  [androidJvm] fun [UiObjectInteractionDelegate](-ui-object-interaction-delegate.md)(device: UiDevice, selector: [UiViewSelector](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md), description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [check](check.md)| [androidJvm]  <br>Content  <br>fun [check](check.md)(uiAssertion: [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>)  <br>fun [check](check.md)(type: [UiOperationType](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md), description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, assert: UiObject2.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [globalInterceptor](global-interceptor.md)| [androidJvm]  <br>Content  <br>open override fun [globalInterceptor](global-interceptor.md)(): [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>?  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| interceptCheck| [androidJvm]  <br>Brief description  <br><br><br>Runs the interceptors available for the given delegate during the check operation.<br><br>  <br>Content  <br>open override fun interceptCheck(assertion: [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| interceptOnAll| [androidJvm]  <br>Content  <br>override fun interceptOnAll(uiInterceptor: [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| interceptOnCheck| [androidJvm]  <br>Content  <br>override fun interceptOnCheck(uiInterceptor: [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>, assertion: [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| interceptOnPerform| [androidJvm]  <br>Content  <br>override fun interceptOnPerform(uiInterceptor: [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>, action: [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| interceptPerform| [androidJvm]  <br>Brief description  <br><br><br>Runs the interceptors available for the given delegate during the execute operation.<br><br>  <br>Content  <br>open override fun interceptPerform(action: [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [loadView](load-view.md)| [androidJvm]  <br>Content  <br>fun [loadView](load-view.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [perform](perform.md)| [androidJvm]  <br>Content  <br>fun [perform](perform.md)(uiAction: [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>)  <br>fun [perform](perform.md)(type: [UiOperationType](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md), description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, action: UiObject2.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [screenInterceptors](screen-interceptors.md)| [androidJvm]  <br>Content  <br>open override fun [screenInterceptors](screen-interceptors.md)(): [Iterable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)<[UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>>  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [interaction](index.md#com.kaspersky.components.kautomator.intercept.delegate/UiObjectInteractionDelegate/interaction/#/PointingToDeclaration/)|  [androidJvm] open override val [interaction](index.md#com.kaspersky.components.kautomator.intercept.delegate/UiObjectInteractionDelegate/interaction/#/PointingToDeclaration/): [UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)   <br>
| [interceptor](index.md#com.kaspersky.components.kautomator.intercept.delegate/UiObjectInteractionDelegate/interceptor/#/PointingToDeclaration/)|  [androidJvm] open override var [interceptor](index.md#com.kaspersky.components.kautomator.intercept.delegate/UiObjectInteractionDelegate/interceptor/#/PointingToDeclaration/): [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>?   <br>

