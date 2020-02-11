[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader](../index.md) / [UiObjectLoaderBehaviorInterceptor](./index.md)

# UiObjectLoaderBehaviorInterceptor

`class UiObjectLoaderBehaviorInterceptor : `[`ObjectBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)`, `[`UiObjectLoaderProvider`](../../com.kaspersky.kaspresso.uiobjectloader/-ui-object-loader-provider/index.md)

The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [UiObjectLoaderProvider](../../com.kaspersky.kaspresso.uiobjectloader/-ui-object-loader-provider/index.md) interfaces.
Provides system flaky safety functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiObjectLoaderBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [UiObjectLoaderProvider](../../com.kaspersky.kaspresso.uiobjectloader/-ui-object-loader-provider/index.md) interfaces. Provides system flaky safety functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `fun <T> interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion, activity: () -> `[`T`](intercept-check.md#T)`): `[`T`](intercept-check.md#T)<br>Wraps the given [activity](intercept-check.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader.UiObjectLoaderBehaviorInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiObject2)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader.UiObjectLoaderBehaviorInterceptor.interceptCheck.T)))/activity) invocation with the UiObject2 repeated loading. |
| [interceptPerform](intercept-perform.md) | `fun <T> interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction, activity: () -> `[`T`](intercept-perform.md#T)`): `[`T`](intercept-perform.md#T)<br>Wraps the given [activity](intercept-perform.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader.UiObjectLoaderBehaviorInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiObject2)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader.UiObjectLoaderBehaviorInterceptor.interceptPerform.T)))/activity) invocation with the UiObject2 repeated loading. |
