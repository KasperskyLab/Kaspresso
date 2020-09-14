//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.system](../index.md)/[UiSystem](index.md)



# UiSystem  
 [androidJvm] 

Container class for UiAutomator action and assertions executing in the UiSystem.

object [UiSystem](index.md) : [UiSystemActions](../-ui-system-actions/index.md), [UiSystemAssertions](../-ui-system-assertions/index.md), [UiInterceptable](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)<[UiDeviceInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>>    


## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| [UiSystemActions](../-ui-system-actions/index.md)| <br><br><br><br>
  


## Functions  
  
|  Name|  Summary| 
|---|---|
| checkAction| [androidJvm]  <br>Content  <br>override fun checkAction(methodName: [UiOperationType](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md), action: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| checkAssertAction| [androidJvm]  <br>Content  <br>override fun checkAssertAction(methodName: [UiOperationType](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md), action: () -> [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| checkBooleanAction| [androidJvm]  <br>Content  <br>override fun checkBooleanAction(methodName: [UiOperationType](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md), action: () -> [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br>override fun checkBooleanAction(methodName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), action: () -> [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [click](../-ui-system-actions/click.md)| [androidJvm]  <br>Brief description  <br><br><br>Perform a click at arbitrary coordinates specified by the user (click by System)<br><br>  <br>Content  <br>open override fun [click](../-ui-system-actions/click.md)(x: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), y: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| [drag](../-ui-system-actions/drag.md)| [androidJvm]  <br>Brief description  <br><br><br>Performs a swipe from one coordinate to another coordinate. You can control the smoothness and speed of the swipe by specifying the number of steps. Each step execution is throttled to 5 milliseconds per step, so for a 100 steps, the swipe will take around 0.5 seconds to complete.<br><br>  <br>Content  <br>open override fun [drag](../-ui-system-actions/drag.md)(startX: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), startY: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), endX: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), endY: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), steps: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| intercept| [androidJvm]  <br>Brief description  <br><br><br>Sets the interceptors for the instance. Interceptors will be invoked on the interaction with the UiView.<br><br>  <br>Content  <br>open override fun intercept(builder: [UiInterceptor.Builder](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/-builder/index.md)<[UiDeviceInteraction](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md), [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>, [UiOperation](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation/index.md)<UiDevice>>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [invoke](invoke.md)| [androidJvm]  <br>Content  <br>operator fun [invoke](invoke.md)(function: [UiSystem](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [isScreenOn](../-ui-system-assertions/is-screen-on.md)| [androidJvm]  <br>Content  <br>open override fun [isScreenOn](../-ui-system-assertions/is-screen-on.md)()  <br><br><br>
| [openNotification](../-ui-system-actions/open-notification.md)| [androidJvm]  <br>Brief description  <br><br><br>Opens the notification shade<br><br>  <br>Content  <br>open override fun [openNotification](../-ui-system-actions/open-notification.md)()  <br><br><br>
| [openQuickSettings](../-ui-system-actions/open-quick-settings.md)| [androidJvm]  <br>Brief description  <br><br><br>Opens the Quick Settings shade<br><br>  <br>Content  <br>open override fun [openQuickSettings](../-ui-system-actions/open-quick-settings.md)()  <br><br><br>
| [pressDelete](../-ui-system-actions/press-delete.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the DELETE key<br><br>  <br>Content  <br>open override fun [pressDelete](../-ui-system-actions/press-delete.md)()  <br><br><br>
| [pressEnter](../-ui-system-actions/press-enter.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the ENTER key<br><br>  <br>Content  <br>open override fun [pressEnter](../-ui-system-actions/press-enter.md)()  <br><br><br>
| [pressHome](../-ui-system-actions/press-home.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the HOME button<br><br>  <br>Content  <br>open override fun [pressHome](../-ui-system-actions/press-home.md)()  <br><br><br>
| [pressMenu](../-ui-system-actions/press-menu.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the MENU button<br><br>  <br>Content  <br>open override fun [pressMenu](../-ui-system-actions/press-menu.md)()  <br><br><br>
| [pressRecentApps](../-ui-system-actions/press-recent-apps.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the Recent Apps button<br><br>  <br>Content  <br>open override fun [pressRecentApps](../-ui-system-actions/press-recent-apps.md)()  <br><br><br>
| [pressSearch](../-ui-system-actions/press-search.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the SEARCH button<br><br>  <br>Content  <br>open override fun [pressSearch](../-ui-system-actions/press-search.md)()  <br><br><br>
| [reset](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/reset.md)| [androidJvm]  <br>Brief description  <br><br><br>Removes the interceptors from the instance.<br><br>  <br>Content  <br>open override fun [reset](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/reset.md)()  <br><br><br>
| [sleep](../-ui-system-actions/sleep.md)| [androidJvm]  <br>Brief description  <br><br><br>This method simply presses the power button if the screen is ON else it does nothing if the screen is already OFF.<br><br>  <br>Content  <br>open override fun [sleep](../-ui-system-actions/sleep.md)()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [wakeUp](../-ui-system-actions/wake-up.md)| [androidJvm]  <br>Brief description  <br><br><br>This method simulates pressing the power button if the screen is OFF else it does nothing if the screen is already ON.<br><br>  <br>Content  <br>open override fun [wakeUp](../-ui-system-actions/wake-up.md)()  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [view](index.md#com.kaspersky.components.kautomator.system/UiSystem/view/#/PointingToDeclaration/)|  [androidJvm] <br><br>UiDeviceDelegate on which all actions are performed<br><br>open override val [view](index.md#com.kaspersky.components.kautomator.system/UiSystem/view/#/PointingToDeclaration/): [UiDeviceInteractionDelegate](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md)   <br>

