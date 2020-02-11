[kautomator](../../index.md) / [com.kaspersky.components.kautomator.system](../index.md) / [UiSystemActions](./index.md)

# UiSystemActions

`interface UiSystemActions`

Interface with common actions providing by UiAutomator and executing in the System

Provides basic actions that can be performed everywhere

### Types

| Name | Summary |
|---|---|
| [UiSystemActionType](-ui-system-action-type/index.md) | `enum class UiSystemActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `abstract val view: `[`UiDeviceInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md)<br>UiDeviceDelegate on which all actions are performed |

### Functions

| Name | Summary |
|---|---|
| [click](click.md) | `open fun click(x: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, y: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Perform a click at arbitrary coordinates specified by the user (click by System) |
| [drag](drag.md) | `open fun drag(startX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, startY: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, endX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, endY: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, steps: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs a swipe from one coordinate to another coordinate. You can control the smoothness and speed of the swipe by specifying the number of steps. Each step execution is throttled to 5 milliseconds per step, so for a 100 steps, the swipe will take around 0.5 seconds to complete. |
| [openNotification](open-notification.md) | `open fun openNotification(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens the notification shade |
| [openQuickSettings](open-quick-settings.md) | `open fun openQuickSettings(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens the Quick Settings shade |
| [pressDelete](press-delete.md) | `open fun pressDelete(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the DELETE key |
| [pressEnter](press-enter.md) | `open fun pressEnter(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the ENTER key |
| [pressHome](press-home.md) | `open fun pressHome(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the HOME button |
| [pressMenu](press-menu.md) | `open fun pressMenu(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the MENU button |
| [pressRecentApps](press-recent-apps.md) | `open fun pressRecentApps(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the Recent Apps button |
| [pressSearch](press-search.md) | `open fun pressSearch(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the SEARCH button |
| [sleep](sleep.md) | `open fun sleep(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method simply presses the power button if the screen is ON else it does nothing if the screen is already OFF. |
| [wakeUp](wake-up.md) | `open fun wakeUp(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method simulates pressing the power button if the screen is OFF else it does nothing if the screen is already ON. |

### Inheritors

| Name | Summary |
|---|---|
| [UiSystem](../-ui-system/index.md) | `object UiSystem : `[`UiSystemActions`](./index.md)`, `[`UiSystemAssertions`](../-ui-system-assertions/index.md)`, `[`UiInterceptable`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>`<br>Container class for UiAutomator action and assertions executing in the UiSystem. |
