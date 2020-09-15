//[kautomator](../index.md)/[com.kaspersky.components.kautomator.intercept.delegate](index.md)



# Package com.kaspersky.components.kautomator.intercept.delegate  


## Types  
  
|  Name|  Summary| 
|---|---|
| [UiDelegate](-ui-delegate/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Base delegate interface for Kautomator.<br><br><br><br>Provides functionality of aggregating interceptors and invoking them on check and perform invocations.<br><br><br><br>  <br>Content  <br>interface [UiDelegate](-ui-delegate/index.md)<[Interaction](-ui-delegate/index.md), [Assertion](-ui-delegate/index.md), [Action](-ui-delegate/index.md)>  <br><br><br>
| [UiDeviceInteractionDelegate](-ui-device-interaction-delegate/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Delegation class for androidx.test.uiautomator.UiDevice. Wraps all available public calls and intercepts into [check](-ui-device-interaction-delegate/check.md) and [perform](-ui-device-interaction-delegate/perform.md).<br><br>  <br>Content  <br>class [UiDeviceInteractionDelegate](-ui-device-interaction-delegate/index.md)(**device**: UiDevice) : [UiDelegate](-ui-delegate/index.md)<[UiDeviceInteraction](../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md), [UiOperation](../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>, [UiOperation](../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>>   <br><br><br>
| [UiObjectInteractionDelegate](-ui-object-interaction-delegate/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Delegation class for androidx.test.uiautomator.UiObject2. Wraps all available public calls and intercepts into [check](-ui-object-interaction-delegate/check.md) and [perform](-ui-object-interaction-delegate/perform.md).<br><br>  <br>Content  <br>class [UiObjectInteractionDelegate](-ui-object-interaction-delegate/index.md)(**device**: UiDevice, **selector**: [UiViewSelector](../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md), **description**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [UiDelegate](-ui-delegate/index.md)<[UiObjectInteraction](../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>   <br><br><br>

