//[kautomator](../index.md)/[com.kaspersky.components.kautomator.intercept.operation](index.md)



# Package com.kaspersky.components.kautomator.intercept.operation  


## Types  
  
|  Name|  Summary| 
|---|---|
| UiDeviceAction| [androidJvm]  <br>Content  <br>typealias UiDeviceAction = [UiOperation](-ui-operation/index.md)<UiDevice>  <br><br><br>
| UiDeviceAssertion| [androidJvm]  <br>Content  <br>typealias UiDeviceAssertion = [UiOperation](-ui-operation/index.md)<UiDevice>  <br><br><br>
| UiObjectAction| [androidJvm]  <br>Brief description  <br><br><br>Appropriate type aliases of UiOperation according to name paradigm in Kakao library (Assertions and Actions)<br><br>  <br>Content  <br>typealias UiObjectAction = [UiOperation](-ui-operation/index.md)<UiObject2>  <br><br><br>
| UiObjectAssertion| [androidJvm]  <br>Content  <br>typealias UiObjectAssertion = [UiOperation](-ui-operation/index.md)<UiObject2>  <br><br><br>
| [UiOperation](-ui-operation/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Responsible for executing an interaction on the element of UiAutomator<br><br>  <br>Content  <br>interface [UiOperation](-ui-operation/index.md)<[View](-ui-operation/index.md)>  <br><br><br>
| [UiOperationBaseImpl](-ui-operation-base-impl/index.md)| [androidJvm]  <br>Content  <br>class [UiOperationBaseImpl](-ui-operation-base-impl/index.md)<[View](-ui-operation-base-impl/index.md)>(**type**: [UiOperationType](-ui-operation-type/index.md), **description**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, **action**: [View](-ui-operation-base-impl/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [UiOperation](-ui-operation/index.md)<[View](-ui-operation-base-impl/index.md)>   <br><br><br>
| [UiOperationType](-ui-operation-type/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Type of the concrete action executing on the given element of UiAutomator<br><br>  <br>Content  <br>interface [UiOperationType](-ui-operation-type/index.md)  <br><br><br>

