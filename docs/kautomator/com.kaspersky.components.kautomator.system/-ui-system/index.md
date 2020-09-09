[kautomator](../../index.md) / [com.kaspersky.components.kautomator.system](../index.md) / [UiSystem](./index.md)

# UiSystem

`object UiSystem : `[`UiSystemActions`](../-ui-system-actions/index.md)`, `[`UiSystemAssertions`](../-ui-system-assertions/index.md)`, `[`UiInterceptable`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptable/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>`

Container class for UiAutomator action and assertions executing in the UiSystem.

**See Also**

[UiSystemActions](../-ui-system-actions/index.md)

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | UiDeviceDelegate on which all actions are performed`val view: `[`UiDeviceInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun invoke(function: `[`UiSystem`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
