[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety](../index.md) / [FlakySafeDeviceBehaviorInterceptor](index.md) / [interceptPerform](./intercept-perform.md)

# interceptPerform

`fun <T> interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction, activity: () -> `[`T`](intercept-perform.md#T)`): `[`T`](intercept-perform.md#T)

Overrides [KautomatorBehaviorInterceptor.interceptPerform](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-kautomator-behavior-interceptor/intercept-perform.md)

Wraps the given [activity](intercept-perform.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor.interceptPerform.T)))/activity) invocation with the flaky safety.

### Parameters

`interaction` - the intercepted [UiDeviceInteraction](#).

`action` - the intercepted [UiDeviceAction](#).

`activity` - the activity to invoke.