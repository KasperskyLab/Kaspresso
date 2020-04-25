[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.delegate](../index.md) / [UiObjectInteractionDelegate](./index.md)

# UiObjectInteractionDelegate

`class UiObjectInteractionDelegate : `[`UiDelegate`](../-ui-delegate/index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>`

Delegation class for [androidx.test.uiautomator.UiObject2](#).
Wraps all available public calls and intercepts into [check](check.md) and [perform](perform.md).

**See Also**

[UiDelegate](../-ui-delegate/index.md)

[UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Delegation class for [androidx.test.uiautomator.UiObject2](#). Wraps all available public calls and intercepts into [check](check.md) and [perform](perform.md).`UiObjectInteractionDelegate(device: UiDevice, selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`, description: String)` |

### Properties

| Name | Summary |
|---|---|
| [interaction](interaction.md) | `val interaction: `[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md) |
| [interceptor](interceptor.md) | `var interceptor: `[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>?` |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `fun check(type: `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)`, description: String? = null, assert: UiObject2.() -> Unit): Unit`<br>`fun check(uiAssertion: `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`): Unit` |
| [globalInterceptor](global-interceptor.md) | `fun globalInterceptor(): `[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>?` |
| [loadView](load-view.md) | `fun loadView(): Boolean` |
| [perform](perform.md) | `fun perform(type: `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)`, description: String? = null, action: UiObject2.() -> Unit): Unit`<br>`fun perform(uiAction: `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`): Unit` |
| [screenInterceptors](screen-interceptors.md) | `fun screenInterceptors(): Iterable<`[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>>` |
