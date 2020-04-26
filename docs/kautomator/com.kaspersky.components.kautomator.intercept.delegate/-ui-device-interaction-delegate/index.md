[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.delegate](../index.md) / [UiDeviceInteractionDelegate](./index.md)

# UiDeviceInteractionDelegate

`class UiDeviceInteractionDelegate : `[`UiDelegate`](../-ui-delegate/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>`

Delegation class for [androidx.test.uiautomator.UiDevice](#).
Wraps all available public calls and intercepts into [check](check.md) and [perform](perform.md).

**See Also**

[UiDelegate](../-ui-delegate/index.md)

[UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Delegation class for [androidx.test.uiautomator.UiDevice](#). Wraps all available public calls and intercepts into [check](check.md) and [perform](perform.md).`UiDeviceInteractionDelegate(device: UiDevice)` |

### Properties

| Name | Summary |
|---|---|
| [interaction](interaction.md) | `val interaction: `[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md) |
| [interceptor](interceptor.md) | `var interceptor: `[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>?` |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `fun check(type: `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)`, description: String? = null, assert: UiDevice.() -> Unit): Unit`<br>`fun check(uiAssertion: `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`): Unit` |
| [globalInterceptor](global-interceptor.md) | `fun globalInterceptor(): `[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>?` |
| [perform](perform.md) | `fun perform(type: `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)`, description: String? = null, action: UiDevice.() -> Unit): Unit`<br>`fun perform(uiAction: `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`): Unit` |
| [screenInterceptors](screen-interceptors.md) | `fun screenInterceptors(): Iterable<`[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>>` |