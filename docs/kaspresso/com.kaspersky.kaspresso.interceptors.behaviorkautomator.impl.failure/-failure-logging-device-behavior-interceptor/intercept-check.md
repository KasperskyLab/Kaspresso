[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure](../index.md) / [FailureLoggingDeviceBehaviorInterceptor](index.md) / [interceptCheck](./intercept-check.md)

# interceptCheck

`fun <T> interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion, activity: () -> T): T`

Wraps the given [activity](intercept-check.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure.FailureLoggingDeviceBehaviorInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure.FailureLoggingDeviceBehaviorInterceptor.interceptCheck.T)))/activity) invocation with the failure logging.

### Parameters

`interaction` - the intercepted [UiDeviceInteraction](#).

`assertion` - the intercepted [UiDeviceAssertion](#).

`activity` - the activity to invoke.