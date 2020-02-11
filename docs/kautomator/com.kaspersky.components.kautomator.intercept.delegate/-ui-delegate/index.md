[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.delegate](../index.md) / [UiDelegate](./index.md)

# UiDelegate

`interface UiDelegate<Interaction, Assertion, Action>`

Base delegate interface for Kautomator.

Provides functionality of aggregating interceptors and invoking them on `check`
and `perform` invocations.

**See Also**

[UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)

### Properties

| Name | Summary |
|---|---|
| [interaction](interaction.md) | `abstract val interaction: `[`Interaction`](index.md#Interaction) |
| [interceptor](interceptor.md) | `abstract var interceptor: `[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`Interaction`](index.md#Interaction)`, `[`Assertion`](index.md#Assertion)`, `[`Action`](index.md#Action)`>?` |

### Functions

| Name | Summary |
|---|---|
| [globalInterceptor](global-interceptor.md) | `abstract fun globalInterceptor(): `[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`Interaction`](index.md#Interaction)`, `[`Assertion`](index.md#Assertion)`, `[`Action`](index.md#Action)`>?` |
| [interceptCheck](intercept-check.md) | `open fun interceptCheck(assertion: `[`Assertion`](index.md#Assertion)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Runs the interceptors available for the given delegate during the `check` operation. |
| [interceptPerform](intercept-perform.md) | `open fun interceptPerform(action: `[`Action`](index.md#Action)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Runs the interceptors available for the given delegate during the `execute` operation. |
| [screenInterceptors](screen-interceptors.md) | `abstract fun screenInterceptors(): `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`UiInterceptor`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)`<`[`Interaction`](index.md#Interaction)`, `[`Assertion`](index.md#Assertion)`, `[`Action`](index.md#Action)`>>` |

### Inheritors

| Name | Summary |
|---|---|
| [UiDeviceInteractionDelegate](../-ui-device-interaction-delegate/index.md) | `class UiDeviceInteractionDelegate : `[`UiDelegate`](./index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>`<br>Delegation class for [androidx.test.uiautomator.UiDevice](#). Wraps all available public calls and intercepts into [check](../-ui-device-interaction-delegate/check.md) and [perform](../-ui-device-interaction-delegate/perform.md). |
| [UiObjectInteractionDelegate](../-ui-object-interaction-delegate/index.md) | `class UiObjectInteractionDelegate : `[`UiDelegate`](./index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>`<br>Delegation class for [androidx.test.uiautomator.UiObject2](#). Wraps all available public calls and intercepts into [check](../-ui-object-interaction-delegate/check.md) and [perform](../-ui-object-interaction-delegate/perform.md). |
