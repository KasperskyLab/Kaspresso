//[kautomator](../index.md)/[com.kaspersky.components.kautomator.component.common.views](index.md)



# Package com.kaspersky.components.kautomator.component.common.views  


## Types  
  
|  Name|  Summary| 
|---|---|
| [UiBaseView](-ui-base-view/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Base class for all UiAutomator DSL views<br><br><br><br>This base class allows create new custom view with ease. All you have to do is to extend this class, implement all necessarily additional actions/assertions interfaces and override necessary constructors<br><br><br><br>  <br>Content  <br>open class [UiBaseView](-ui-base-view/index.md)<[T](-ui-base-view/index.md)>(**selector**: [UiViewSelector](../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)) : [UiBaseActions](../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md), [UiBaseAssertions](../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md), [UiInterceptable](../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)<[UiObjectInteraction](../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md), [UiOperation](../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>, [UiOperation](../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiObject2>>   <br><br><br>
| [UiView](-ui-view/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Simple view with [UiBaseAction](../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md) and [UiBaseAssertion](../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)<br><br>  <br>Content  <br>class [UiView](-ui-view/index.md) : [UiBaseView](-ui-base-view/index.md)<[UiView](-ui-view/index.md)>   <br><br><br>

