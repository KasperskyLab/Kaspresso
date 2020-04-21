[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.interaction](../index.md) / [UiDeviceInteraction](./index.md)

# UiDeviceInteraction

`class UiDeviceInteraction : `[`UiInteraction`](../-ui-interaction/index.md)`<`[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>`

Provides an interaction to work with the UiDevice

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiDeviceInteraction(device: UiDevice)`<br>Provides an interaction to work with the UiDevice |

### Properties

| Name | Summary |
|---|---|
| [device](device.md) | `val device: UiDevice` |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `fun check(assertion: `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [perform](perform.md) | `fun perform(action: `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
