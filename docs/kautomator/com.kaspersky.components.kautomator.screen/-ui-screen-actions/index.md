[kautomator](../../index.md) / [com.kaspersky.components.kautomator.screen](../index.md) / [UiScreenActions](./index.md)

# UiScreenActions

`interface UiScreenActions`

Interface with common actions for all UiAutomator screens

Provides basic actions that can be performed on each and every screen

### Types

| Name | Summary |
|---|---|
| [UiScreenActionType](-ui-screen-action-type/index.md) | `enum class UiScreenActionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | UiDeviceDelegate on which all actions are performed`abstract val view: `[`UiDeviceInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [pressBack](press-back.md) | Simulates a short press on the BACK button.`open fun pressBack(): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiScreen](../-ui-screen/index.md) | Container class for UiAutomator elements.`abstract class UiScreen<out T : `[`UiScreen`](../-ui-screen/index.md)`<T>> : `[`UiScreenActions`](./index.md) |
