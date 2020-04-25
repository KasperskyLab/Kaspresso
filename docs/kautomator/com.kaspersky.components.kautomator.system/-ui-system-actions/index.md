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
| [view](view.md) | UiDeviceDelegate on which all actions are performed`abstract val view: `[`UiDeviceInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [click](click.md) | Perform a click at arbitrary coordinates specified by the user (click by System)`open fun click(x: Int, y: Int): Unit` |
| [drag](drag.md) | Performs a swipe from one coordinate to another coordinate. You can control the smoothness and speed of the swipe by specifying the number of steps. Each step execution is throttled to 5 milliseconds per step, so for a 100 steps, the swipe will take around 0.5 seconds to complete.`open fun drag(startX: Int, startY: Int, endX: Int, endY: Int, steps: Int): Unit` |
| [openNotification](open-notification.md) | Opens the notification shade`open fun openNotification(): Unit` |
| [openQuickSettings](open-quick-settings.md) | Opens the Quick Settings shade`open fun openQuickSettings(): Unit` |
| [pressDelete](press-delete.md) | Simulates a short press on the DELETE key`open fun pressDelete(): Unit` |
| [pressEnter](press-enter.md) | Simulates a short press on the ENTER key`open fun pressEnter(): Unit` |
| [pressHome](press-home.md) | Simulates a short press on the HOME button`open fun pressHome(): Unit` |
| [pressMenu](press-menu.md) | Simulates a short press on the MENU button`open fun pressMenu(): Unit` |
| [pressRecentApps](press-recent-apps.md) | Simulates a short press on the Recent Apps button`open fun pressRecentApps(): Unit` |
| [pressSearch](press-search.md) | Simulates a short press on the SEARCH button`open fun pressSearch(): Unit` |
| [sleep](sleep.md) | This method simply presses the power button if the screen is ON else it does nothing if the screen is already OFF.`open fun sleep(): Unit` |
| [wakeUp](wake-up.md) | This method simulates pressing the power button if the screen is OFF else it does nothing if the screen is already ON.`open fun wakeUp(): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiSystem](../-ui-system/index.md) | Container class for UiAutomator action and assertions executing in the UiSystem.`object UiSystem : `[`UiSystemActions`](./index.md)`, `[`UiSystemAssertions`](../-ui-system-assertions/index.md)`, `[`UiInterceptable`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>` |
