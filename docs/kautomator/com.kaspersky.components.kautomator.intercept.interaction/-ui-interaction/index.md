[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.interaction](../index.md) / [UiInteraction](./index.md)

# UiInteraction

`interface UiInteraction<Assertion, Action>`

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `abstract fun check(assertion: Assertion): Unit` |
| [perform](perform.md) | `abstract fun perform(action: Action): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiDeviceInteraction](../-ui-device-interaction/index.md) | Provides an interaction to work with the UiDevice`class UiDeviceInteraction : `[`UiInteraction`](./index.md)`<`[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>` |
| [UiObjectInteraction](../-ui-object-interaction/index.md) | Provides an interaction to work with the UiView described by [selector](../-ui-object-interaction/selector.md)`class UiObjectInteraction : `[`UiInteraction`](./index.md)`<`[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>` |
