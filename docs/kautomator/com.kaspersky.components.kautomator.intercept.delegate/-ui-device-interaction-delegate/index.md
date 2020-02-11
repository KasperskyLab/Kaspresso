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
| [&lt;init&gt;](-init-.md) | `UiDeviceInteractionDelegate(device: UiDevice)`<br>Delegation class for [androidx.test.uiautomator.UiDevice](#). Wraps all available public calls and intercepts into [check](check.md) and [perform](perform.md). |

### Properties

| Name | Summary |
|---|---|
| [interaction](interaction.md) | `val interaction: `[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md) |
| [interceptor](interceptor.md) | `var interceptor: `[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>?` |

### Functions

| Name | Summary |
|---|---|
| [check](check.md) | `fun check(type: `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)`, description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, assert: UiDevice.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun check(uiAssertion: `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [globalInterceptor](global-interceptor.md) | `fun globalInterceptor(): `[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>?` |
| [perform](perform.md) | `fun perform(type: `[`UiOperationType`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-operation-type/index.md)`, description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, action: UiDevice.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun perform(uiAction: `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [screenInterceptors](screen-interceptors.md) | `fun screenInterceptors(): `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>>` |

### Inherited Functions

| Name | Summary |
|---|---|
| [interceptCheck](../-ui-delegate/intercept-check.md) | `open fun interceptCheck(assertion: `[`Assertion`](../-ui-delegate/index.md#Assertion)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Runs the interceptors available for the given delegate during the `check` operation. |
| [interceptPerform](../-ui-delegate/intercept-perform.md) | `open fun interceptPerform(action: `[`Action`](../-ui-delegate/index.md#Action)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Runs the interceptors available for the given delegate during the `execute` operation. |
