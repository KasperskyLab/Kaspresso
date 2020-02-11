[kautomator](../index.md) / [com.kaspersky.components.kautomator.intercept.operation](./index.md)

## Package com.kaspersky.components.kautomator.intercept.operation

### Types

| Name | Summary |
|---|---|
| [UiOperation](-ui-operation/index.md) | `interface UiOperation<View>`<br>Responsible for executing an interaction on the element of UiAutomator |
| [UiOperationBaseImpl](-ui-operation-base-impl/index.md) | `class UiOperationBaseImpl<View> : `[`UiOperation`](-ui-operation/index.md)`<`[`View`](-ui-operation-base-impl/index.md#View)`>` |
| [UiOperationType](-ui-operation-type/index.md) | `interface UiOperationType`<br>Type of the concrete action executing on the given element of UiAutomator |

### Type Aliases

| Name | Summary |
|---|---|
| [UiDeviceAction](-ui-device-action.md) | `typealias UiDeviceAction = `[`UiOperation`](-ui-operation/index.md)`<UiDevice>` |
| [UiDeviceAssertion](-ui-device-assertion.md) | `typealias UiDeviceAssertion = `[`UiOperation`](-ui-operation/index.md)`<UiDevice>` |
| [UiObjectAction](-ui-object-action.md) | `typealias UiObjectAction = `[`UiOperation`](-ui-operation/index.md)`<UiObject2>`<br>Appropriate type aliases of UiOperation according to name paradigm in Kakao library (Assertions and Actions) |
| [UiObjectAssertion](-ui-object-assertion.md) | `typealias UiObjectAssertion = `[`UiOperation`](-ui-operation/index.md)`<UiObject2>` |
