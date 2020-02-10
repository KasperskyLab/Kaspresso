[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety](../index.md) / [FlakySafeObjectBehaviorInterceptor](./index.md)

# FlakySafeObjectBehaviorInterceptor

`class FlakySafeObjectBehaviorInterceptor : `[`ObjectBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)

The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces.
Provides system flaky safety functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FlakySafeObjectBehaviorInterceptor(params: `[`FlakySafetyParams`](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `fun <T> interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion, activity: () -> `[`T`](intercept-check.md#T)`): `[`T`](intercept-check.md#T)<br>Wraps the given [activity](intercept-check.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiObject2)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor.interceptCheck.T)))/activity) invocation with the flaky safety. |
| [interceptPerform](intercept-perform.md) | `fun <T> interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction, activity: () -> `[`T`](intercept-perform.md#T)`): `[`T`](intercept-perform.md#T)<br>Wraps the given [activity](intercept-perform.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiObject2)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor.interceptPerform.T)))/activity) invocation with the flaky safety. |
