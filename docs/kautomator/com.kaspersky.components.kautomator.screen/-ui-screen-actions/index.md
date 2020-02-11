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
| [view](view.md) | `abstract val view: `[`UiDeviceInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md)<br>UiDeviceDelegate on which all actions are performed |

### Functions

| Name | Summary |
|---|---|
| [pressBack](press-back.md) | `open fun pressBack(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the BACK button. |

### Inheritors

| Name | Summary |
|---|---|
| [UiScreen](../-ui-screen/index.md) | `abstract class UiScreen<out T : `[`UiScreen`](../-ui-screen/index.md)`<`[`T`](../-ui-screen/index.md#T)`>> : `[`UiScreenActions`](./index.md)<br>Container class for UiAutomator elements. |
