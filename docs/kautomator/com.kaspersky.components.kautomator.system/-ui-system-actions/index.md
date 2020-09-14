//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.system](../index.md)/[UiSystemActions](index.md)



# UiSystemActions  
 [androidJvm] 



Interface with common actions providing by UiAutomator and executing in the System



Provides basic actions that can be performed everywhere



interface [UiSystemActions](index.md)   


## Types  
  
|  Name|  Summary| 
|---|---|
| [UiSystemActionType](-ui-system-action-type/index.md)| [androidJvm]  <br>Content  <br>enum [UiSystemActionType](-ui-system-action-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)<[UiSystemActions.UiSystemActionType](-ui-system-action-type/index.md)> , [UiOperationType](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [click](click.md)| [androidJvm]  <br>Brief description  <br><br><br>Perform a click at arbitrary coordinates specified by the user (click by System)<br><br>  <br>Content  <br>open fun [click](click.md)(x: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), y: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| [drag](drag.md)| [androidJvm]  <br>Brief description  <br><br><br>Performs a swipe from one coordinate to another coordinate. You can control the smoothness and speed of the swipe by specifying the number of steps. Each step execution is throttled to 5 milliseconds per step, so for a 100 steps, the swipe will take around 0.5 seconds to complete.<br><br>  <br>Content  <br>open fun [drag](drag.md)(startX: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), startY: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), endX: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), endY: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), steps: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [openNotification](open-notification.md)| [androidJvm]  <br>Brief description  <br><br><br>Opens the notification shade<br><br>  <br>Content  <br>open fun [openNotification](open-notification.md)()  <br><br><br>
| [openQuickSettings](open-quick-settings.md)| [androidJvm]  <br>Brief description  <br><br><br>Opens the Quick Settings shade<br><br>  <br>Content  <br>open fun [openQuickSettings](open-quick-settings.md)()  <br><br><br>
| [pressDelete](press-delete.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the DELETE key<br><br>  <br>Content  <br>open fun [pressDelete](press-delete.md)()  <br><br><br>
| [pressEnter](press-enter.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the ENTER key<br><br>  <br>Content  <br>open fun [pressEnter](press-enter.md)()  <br><br><br>
| [pressHome](press-home.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the HOME button<br><br>  <br>Content  <br>open fun [pressHome](press-home.md)()  <br><br><br>
| [pressMenu](press-menu.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the MENU button<br><br>  <br>Content  <br>open fun [pressMenu](press-menu.md)()  <br><br><br>
| [pressRecentApps](press-recent-apps.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the Recent Apps button<br><br>  <br>Content  <br>open fun [pressRecentApps](press-recent-apps.md)()  <br><br><br>
| [pressSearch](press-search.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the SEARCH button<br><br>  <br>Content  <br>open fun [pressSearch](press-search.md)()  <br><br><br>
| [sleep](sleep.md)| [androidJvm]  <br>Brief description  <br><br><br>This method simply presses the power button if the screen is ON else it does nothing if the screen is already OFF.<br><br>  <br>Content  <br>open fun [sleep](sleep.md)()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [wakeUp](wake-up.md)| [androidJvm]  <br>Brief description  <br><br><br>This method simulates pressing the power button if the screen is OFF else it does nothing if the screen is already ON.<br><br>  <br>Content  <br>open fun [wakeUp](wake-up.md)()  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [view](index.md#com.kaspersky.components.kautomator.system/UiSystemActions/view/#/PointingToDeclaration/)|  [androidJvm] <br><br>UiDeviceDelegate on which all actions are performed<br><br>abstract val [view](index.md#com.kaspersky.components.kautomator.system/UiSystemActions/view/#/PointingToDeclaration/): [UiDeviceInteractionDelegate](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md)   <br>


## Inheritors  
  
|  Name| 
|---|
| [UiSystem](../-ui-system/index.md)

