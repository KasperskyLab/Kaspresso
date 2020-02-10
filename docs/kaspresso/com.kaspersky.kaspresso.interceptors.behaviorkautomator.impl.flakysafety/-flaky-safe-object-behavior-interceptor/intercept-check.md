[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety](../index.md) / [FlakySafeObjectBehaviorInterceptor](index.md) / [interceptCheck](./intercept-check.md)

# interceptCheck

`fun <T> interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion, activity: () -> `[`T`](intercept-check.md#T)`): `[`T`](intercept-check.md#T)

Overrides [KautomatorBehaviorInterceptor.interceptCheck](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-kautomator-behavior-interceptor/intercept-check.md)

Wraps the given [activity](intercept-check.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiObject2)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor.interceptCheck.T)))/activity) invocation with the flaky safety.

### Parameters

`interaction` - the intercepted [UiObjectInteraction](#).

`assertion` - the intercepted [UiObjectAssertion](#).

`activity` - the activity to invoke.