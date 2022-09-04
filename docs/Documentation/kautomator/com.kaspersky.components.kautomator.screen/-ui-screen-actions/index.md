//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.screen](../index.md)/[UiScreenActions](index.md)



# UiScreenActions  
 [androidJvm] 



Interface with common actions for all UiAutomator screens



Provides basic actions that can be performed on each and every screen



interface [UiScreenActions](index.md)   


## Types  
  
|  Name|  Summary| 
|---|---|
| [UiScreenActionType](-ui-screen-action-type/index.md)| [androidJvm]  <br>Content  <br>enum [UiScreenActionType](-ui-screen-action-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)<[UiScreenActions.UiScreenActionType](-ui-screen-action-type/index.md)> , [UiOperationType](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [pressBack](press-back.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the BACK button.<br><br>  <br>Content  <br>open fun [pressBack](press-back.md)()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [waitForWindowUpdate](wait-for-window-update.md)| [androidJvm]  <br>Brief description  <br><br><br>Waits for window update<br><br>  <br>Content  <br>open fun [waitForWindowUpdate](wait-for-window-update.md)(timeout: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Waits for window update for specified package name<br><br>  <br>Content  <br>open fun [waitForWindowUpdate](wait-for-window-update.md)(packageName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeout: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [view](index.md#com.kaspersky.components.kautomator.screen/UiScreenActions/view/#/PointingToDeclaration/)|  [androidJvm] <br><br>UiDeviceDelegate on which all actions are performed<br><br>abstract val [view](index.md#com.kaspersky.components.kautomator.screen/UiScreenActions/view/#/PointingToDeclaration/): [UiDeviceInteractionDelegate](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md)   <br>


## Inheritors  
  
|  Name| 
|---|
| [UiScreen](../-ui-screen/index.md)

