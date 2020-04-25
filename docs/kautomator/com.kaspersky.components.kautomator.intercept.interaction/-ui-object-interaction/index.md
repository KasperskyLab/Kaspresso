[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.interaction](../index.md) / [UiObjectInteraction](./index.md)

# UiObjectInteraction

`class UiObjectInteraction : `[`UiInteraction`](../-ui-interaction/index.md)`<`[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>`

Provides an interaction to work with the UiView described by [selector](selector.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Provides an interaction to work with the UiView described by [selector](selector.md)`UiObjectInteraction(device: UiDevice, selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md)`, description: String)` |

### Properties

| Name | Summary |
|---|---|
| [description](description.md) | `val description: String` |
| [device](device.md) | `val device: UiDevice` |
| [selector](selector.md) | `val selector: `[`UiViewSelector`](../../com.kaspersky.components.kautomator.component.common.builders/-ui-view-selector/index.md) |
| [uiObject2](ui-object2.md) | `var uiObject2: UiObject2?` |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `fun check(assertion: `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`): Unit` |
| [perform](perform.md) | `fun perform(action: `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`): Unit` |
| [toString](to-string.md) | `fun toString(): String` |
| [tryToFindUiObject](try-to-find-ui-object.md) | Tries to find UiObject2 with given selector`fun tryToFindUiObject(): Boolean` |
