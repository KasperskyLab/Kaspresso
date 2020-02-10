[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll](../index.md) / [AutoScrollObjectBehaviorInterceptor](./index.md)

# AutoScrollObjectBehaviorInterceptor

`class AutoScrollObjectBehaviorInterceptor : `[`ObjectBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)`, `[`AutoScrollProvider`](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md)`<UiObjectInteraction>`

The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces.
Provides autoscroll on failure functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AutoScrollObjectBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, autoScrollParams: `[`AutoScrollParams`](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md)`)`<br>The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces. Provides autoscroll on failure functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `fun <T> interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion, activity: () -> `[`T`](intercept-check.md#T)`): `[`T`](intercept-check.md#T)<br>Wraps the given [assertion](intercept-check.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll.AutoScrollObjectBehaviorInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiObject2)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll.AutoScrollObjectBehaviorInterceptor.interceptCheck.T)))/assertion) invocation with the autoscrolling on failure. |
| [interceptPerform](intercept-perform.md) | `fun <T> interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction, activity: () -> `[`T`](intercept-perform.md#T)`): `[`T`](intercept-perform.md#T)<br>Wraps the given [action](intercept-perform.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll.AutoScrollObjectBehaviorInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiObject2)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll.AutoScrollObjectBehaviorInterceptor.interceptPerform.T)))/action) invocation with the autoscrolling on failure. |
