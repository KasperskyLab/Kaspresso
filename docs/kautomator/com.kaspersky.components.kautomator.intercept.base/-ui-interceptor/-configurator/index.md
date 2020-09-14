//[kautomator](../../../index.md)/[com.kaspersky.components.kautomator.intercept.base](../../index.md)/[UiInterceptor](../index.md)/[Configurator](index.md)



# Configurator  
 [androidJvm] 

Configuration class that is used for building interceptors on the [KautomatorConfigurator](../../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md) runtime and com.kaspersky.components.kautomator.component.screen.UiScreen levels.

class [Configurator](index.md)   


## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| com.kaspersky.components.kautomator.component.screen.UiScreen| <br><br><br><br>
| [com.kaspersky.components.kautomator.KautomatorConfigurator](../../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md)| <br><br><br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [Configurator](-configurator.md)|  [androidJvm] fun [Configurator](-configurator.md)()   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [onUiDeviceInteraction](on-ui-device-interaction.md)| [androidJvm]  <br>Brief description  <br><br><br>Setups the interceptor for check and execute operations happening through [UiDeviceInteraction](../../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)<br><br>  <br>Content  <br>fun [onUiDeviceInteraction](on-ui-device-interaction.md)(builder: [UiInterceptor.Builder](../-builder/index.md)<[UiDeviceInteraction](../../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md), [UiOperation](../../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>, [UiOperation](../../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [onUiInteraction](on-ui-interaction.md)| [androidJvm]  <br>Brief description  <br><br><br>Setups the interceptor for check and execute operations happening through [UiObjectInteraction](../../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)<br><br>  <br>Content  <br>fun [onUiInteraction](on-ui-interaction.md)(builder: [UiInterceptor.Builder](../-builder/index.md)<[UiObjectInteraction](../../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

