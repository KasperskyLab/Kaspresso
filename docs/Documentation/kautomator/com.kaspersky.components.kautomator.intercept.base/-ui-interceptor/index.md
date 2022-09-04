//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.intercept.base](../index.md)/[UiInterceptor](index.md)



# UiInterceptor  
 [androidJvm] 



Base class for intercepting the call chain from Kautomator to UiAutomator.



Interceptors can be provided through [KautomatorConfigurator](../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md) runtime, different com.kaspersky.components.kautomator.component.screen.UiScreen as well as [UiViews](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md).



Interceptors are stacked during the runtime for any UiAutomator_DSL-UiAutomator check and perform operations. The stack ordering is following: UiView interceptor -> UiScreen interceptors -> UiAutomatorDsl interceptor.



Any of the interceptors in the chain can break the chain call by setting isOverride to true in onCheck, onPerform or onAll interception functions during the configuration. Doing this will not only prevent underlying interceptors from being invoked, but prevents UiAutomator from executing the operation. In that case, responsibility for actually making UiAutomator call lies on developer.



For each operation the interceptor invocation cycle will be as follows:

// For check operation  
onAll?.invoke()  
onCheck?.invoke()  
  
// For perform operation  
onAll?.invoke()  
onPerform?.invoke()

class [UiInterceptor](index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>(**onCheck**: [UiInterception](../-ui-interception/index.md)<([Interaction](index.md), [Assertion](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>?, **onPerform**: [UiInterception](../-ui-interception/index.md)<([Interaction](index.md), [Action](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>?, **onAll**: [UiInterception](../-ui-interception/index.md)<([Interaction](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>?)   


## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| [com.kaspersky.components.kautomator.component.common.views.UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)| <br><br><br><br>
| com.kaspersky.components.kautomator.component.screen.UiScreen| <br><br><br><br>
| [com.kaspersky.components.kautomator.KautomatorConfigurator](../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md)| <br><br><br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [UiInterceptor](-ui-interceptor.md)|  [androidJvm] fun <[Interaction](index.md), [Assertion](index.md), [Action](index.md)> [UiInterceptor](-ui-interceptor.md)(onCheck: [UiInterception](../-ui-interception/index.md)<([Interaction](index.md), [Assertion](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>?, onPerform: [UiInterception](../-ui-interception/index.md)<([Interaction](index.md), [Action](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>?, onAll: [UiInterception](../-ui-interception/index.md)<([Interaction](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>?)   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| [Builder](-builder/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Builder class that is used to build a single instance of [UiInterceptor](index.md).<br><br>  <br>Content  <br>class [Builder](-builder/index.md)<[Interaction](-builder/index.md), [Assertion](-builder/index.md), [Action](-builder/index.md)>  <br><br><br>
| [Configuration](-configuration/index.md)| [androidJvm]  <br>Content  <br>data class [Configuration](-configuration/index.md)(**uiUiObjectObjectInterceptor**: [UiInterceptor](index.md)<[UiObjectInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>?, **uiDeviceInterceptor**: [UiInterceptor](index.md)<[UiDeviceInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>>?)  <br><br><br>
| [Configurator](-configurator/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Configuration class that is used for building interceptors on the [KautomatorConfigurator](../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md) runtime and com.kaspersky.components.kautomator.component.screen.UiScreen levels.<br><br>  <br>Content  <br>class [Configurator](-configurator/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [onAll](index.md#com.kaspersky.components.kautomator.intercept.base/UiInterceptor/onAll/#/PointingToDeclaration/)|  [androidJvm] val [onAll](index.md#com.kaspersky.components.kautomator.intercept.base/UiInterceptor/onAll/#/PointingToDeclaration/): [UiInterception](../-ui-interception/index.md)<([Interaction](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>?   <br>
| [onCheck](index.md#com.kaspersky.components.kautomator.intercept.base/UiInterceptor/onCheck/#/PointingToDeclaration/)|  [androidJvm] val [onCheck](index.md#com.kaspersky.components.kautomator.intercept.base/UiInterceptor/onCheck/#/PointingToDeclaration/): [UiInterception](../-ui-interception/index.md)<([Interaction](index.md), [Assertion](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>?   <br>
| [onPerform](index.md#com.kaspersky.components.kautomator.intercept.base/UiInterceptor/onPerform/#/PointingToDeclaration/)|  [androidJvm] val [onPerform](index.md#com.kaspersky.components.kautomator.intercept.base/UiInterceptor/onPerform/#/PointingToDeclaration/): [UiInterception](../-ui-interception/index.md)<([Interaction](index.md), [Action](index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>?   <br>

