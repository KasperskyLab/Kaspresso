[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.base](../index.md) / [UiInterceptable](./index.md)

# UiInterceptable

`interface UiInterceptable<Interaction, Assertion, Action>`

### Properties

| Name | Summary |
|---|---|
| [view](view.md) | `abstract val view: `[`UiDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-delegate/index.md)`<Interaction, Assertion, Action>` |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Sets the interceptors for the instance. Interceptors will be invoked on the interaction with the UiView.`open fun intercept(builder: Builder<Interaction, Assertion, Action>.() -> Unit): Unit` |
| [reset](reset.md) | Removes the interceptors from the instance.`open fun reset(): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md) | Base class for all UiAutomator DSL views`open class UiBaseView<out T> : `[`UiBaseActions`](../../com.kaspersky.components.kautomator.component.common.actions/-ui-base-actions/index.md)`, `[`UiBaseAssertions`](../../com.kaspersky.components.kautomator.component.common.assertions/-ui-base-assertions/index.md)`, `[`UiInterceptable`](./index.md)`<`[`UiObjectInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>` |
| [UiSystem](../../com.kaspersky.components.kautomator.system/-ui-system/index.md) | Container class for UiAutomator action and assertions executing in the UiSystem.`object UiSystem : `[`UiSystemActions`](../../com.kaspersky.components.kautomator.system/-ui-system-actions/index.md)`, `[`UiSystemAssertions`](../../com.kaspersky.components.kautomator.system/-ui-system-assertions/index.md)`, `[`UiInterceptable`](./index.md)`<`[`UiDeviceInteraction`](../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>` |
