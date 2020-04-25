[kautomator](../index.md) / [com.kaspersky.components.kautomator.intercept.delegate](./index.md)

## Package com.kaspersky.components.kautomator.intercept.delegate

### Types

| Name | Summary |
|---|---|
| [UiDelegate](-ui-delegate/index.md) | Base delegate interface for Kautomator.`interface UiDelegate<Interaction, Assertion, Action>` |
| [UiDeviceInteractionDelegate](-ui-device-interaction-delegate/index.md) | Delegation class for [androidx.test.uiautomator.UiDevice](#). Wraps all available public calls and intercepts into [check](-ui-device-interaction-delegate/check.md) and [perform](-ui-device-interaction-delegate/perform.md).`class UiDeviceInteractionDelegate : `[`UiDelegate`](-ui-delegate/index.md)`<`[`UiDeviceInteraction`](../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>` |
| [UiObjectInteractionDelegate](-ui-object-interaction-delegate/index.md) | Delegation class for [androidx.test.uiautomator.UiObject2](#). Wraps all available public calls and intercepts into [check](-ui-object-interaction-delegate/check.md) and [perform](-ui-object-interaction-delegate/perform.md).`class UiObjectInteractionDelegate : `[`UiDelegate`](-ui-delegate/index.md)`<`[`UiObjectInteraction`](../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>` |
