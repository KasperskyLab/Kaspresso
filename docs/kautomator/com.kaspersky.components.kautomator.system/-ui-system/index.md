[kautomator](../../index.md) / [com.kaspersky.components.kautomator.system](../index.md) / [UiSystem](./index.md)

# UiSystem

`object UiSystem : `[`UiSystemActions`](../-ui-system-actions/index.md)`, `[`UiSystemAssertions`](../-ui-system-assertions/index.md)`, `[`UiInterceptable`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>`

Container class for UiAutomator action and assertions executing in the UiSystem.

**See Also**

[UiSystemActions](../-ui-system-actions/index.md)

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `val view: `[`UiDeviceInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md)<br>UiDeviceDelegate on which all actions are performed |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun invoke(function: `[`UiSystem`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [click](../-ui-system-actions/click.md) | `open fun click(x: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, y: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Perform a click at arbitrary coordinates specified by the user (click by System) |
| [drag](../-ui-system-actions/drag.md) | `open fun drag(startX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, startY: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, endX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, endY: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, steps: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs a swipe from one coordinate to another coordinate. You can control the smoothness and speed of the swipe by specifying the number of steps. Each step execution is throttled to 5 milliseconds per step, so for a 100 steps, the swipe will take around 0.5 seconds to complete. |
| [intercept](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/intercept.md) | `open fun intercept(builder: `[`UiInterceptor.Builder`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/-builder/index.md)`<`[`Interaction`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md#Interaction)`, `[`Assertion`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md#Assertion)`, `[`Action`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md#Action)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the interceptors for the instance. Interceptors will be invoked on the interaction with the UiView. |
| [isScreenOn](../-ui-system-assertions/is-screen-on.md) | `open fun isScreenOn(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openNotification](../-ui-system-actions/open-notification.md) | `open fun openNotification(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens the notification shade |
| [openQuickSettings](../-ui-system-actions/open-quick-settings.md) | `open fun openQuickSettings(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens the Quick Settings shade |
| [pressDelete](../-ui-system-actions/press-delete.md) | `open fun pressDelete(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the DELETE key |
| [pressEnter](../-ui-system-actions/press-enter.md) | `open fun pressEnter(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the ENTER key |
| [pressHome](../-ui-system-actions/press-home.md) | `open fun pressHome(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the HOME button |
| [pressMenu](../-ui-system-actions/press-menu.md) | `open fun pressMenu(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the MENU button |
| [pressRecentApps](../-ui-system-actions/press-recent-apps.md) | `open fun pressRecentApps(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the Recent Apps button |
| [pressSearch](../-ui-system-actions/press-search.md) | `open fun pressSearch(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the SEARCH button |
| [reset](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/reset.md) | `open fun reset(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes the interceptors from the instance. |
| [sleep](../-ui-system-actions/sleep.md) | `open fun sleep(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method simply presses the power button if the screen is ON else it does nothing if the screen is already OFF. |
| [wakeUp](../-ui-system-actions/wake-up.md) | `open fun wakeUp(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method simulates pressing the power button if the screen is OFF else it does nothing if the screen is already ON. |
