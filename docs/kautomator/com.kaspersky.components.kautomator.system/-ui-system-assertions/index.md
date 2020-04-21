[kautomator](../../index.md) / [com.kaspersky.components.kautomator.system](../index.md) / [UiSystemAssertions](./index.md)

# UiSystemAssertions

`interface UiSystemAssertions`

Interface with common assertions providing by UiAutomator and executing in the System

Provides basic assertions that can be checked everywhere

### Types

| Name | Summary |
|---|---|
| [UiSystemAssertionType](-ui-system-assertion-type/index.md) | `enum class UiSystemAssertionType : `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md) |

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `abstract val view: `[`UiDeviceInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md)<br>UiDeviceDelegate on which all actions are checked |

### Functions

| Name | Summary |
|---|---|
| [isScreenOn](is-screen-on.md) | `open fun isScreenOn(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [UiSystem](../-ui-system/index.md) | `object UiSystem : `[`UiSystemActions`](../-ui-system-actions/index.md)`, `[`UiSystemAssertions`](./index.md)`, `[`UiInterceptable`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>`<br>Container class for UiAutomator action and assertions executing in the UiSystem. |
