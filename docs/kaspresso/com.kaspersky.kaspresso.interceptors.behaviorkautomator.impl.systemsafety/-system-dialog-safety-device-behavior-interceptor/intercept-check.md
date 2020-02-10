[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety](../index.md) / [SystemDialogSafetyDeviceBehaviorInterceptor](index.md) / [interceptCheck](./intercept-check.md)

# interceptCheck

`fun <T> interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion, activity: () -> `[`T`](intercept-check.md#T)`): `[`T`](intercept-check.md#T)

Overrides [KautomatorBehaviorInterceptor.interceptCheck](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-kautomator-behavior-interceptor/intercept-check.md)

Wraps the given [activity](intercept-check.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyDeviceBehaviorInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyDeviceBehaviorInterceptor.interceptCheck.T)))/activity) invocation with the system dialog safety.

### Parameters

`interaction` - the intercepted [UiDeviceInteraction](#).

`assertion` - the intercepted [UiDeviceAssertion](#).

`activity` - the activity to invoke.