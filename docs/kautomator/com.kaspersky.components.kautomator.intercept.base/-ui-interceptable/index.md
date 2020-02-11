[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.base](../index.md) / [UiInterceptable](./index.md)

# UiInterceptable

`interface UiInterceptable<Interaction, Assertion, Action>`

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `abstract val view: `[`UiDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-delegate/index.md)`<`[`Interaction`](index.md#Interaction)`, `[`Assertion`](index.md#Assertion)`, `[`Action`](index.md#Action)`>` |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `open fun intercept(builder: `[`UiInterceptor.Builder`](../-ui-interceptor/-builder/index.md)`<`[`Interaction`](index.md#Interaction)`, `[`Assertion`](index.md#Assertion)`, `[`Action`](index.md#Action)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the interceptors for the instance. Interceptors will be invoked on the interaction with the UiView. |
| [reset](reset.md) | `open fun reset(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes the interceptors from the instance. |

### Inheritors

| Name | Summary |
|---|---|
| [UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md) | `open class UiBaseView<out T> : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)`, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`, `[`UiInterceptable`](./index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>`<br>Base class for all UiAutomator DSL views |
| [UiSystem](../../com.kaspersky.components.kautomator.system/-ui-system/index.md) | `object UiSystem : `[`UiSystemActions`](../../com.kaspersky.components.kautomator.system/-ui-system-actions/index.md)`, `[`UiSystemAssertions`](../../com.kaspersky.components.kautomator.system/-ui-system-assertions/index.md)`, `[`UiInterceptable`](./index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>`<br>Container class for UiAutomator action and assertions executing in the UiSystem. |
