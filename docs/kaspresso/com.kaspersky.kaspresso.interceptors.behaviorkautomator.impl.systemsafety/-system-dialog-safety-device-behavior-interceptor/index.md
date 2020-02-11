[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety](../index.md) / [SystemDialogSafetyDeviceBehaviorInterceptor](./index.md)

# SystemDialogSafetyDeviceBehaviorInterceptor

`class SystemDialogSafetyDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md)

The implementation of [DeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces.
Provides system dialog safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SystemDialogSafetyDeviceBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, uiDevice: UiDevice, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)`<br>The implementation of [DeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `fun <T> interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion, activity: () -> `[`T`](intercept-check.md#T)`): `[`T`](intercept-check.md#T)<br>Wraps the given [activity](intercept-check.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyDeviceBehaviorInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyDeviceBehaviorInterceptor.interceptCheck.T)))/activity) invocation with the system dialog safety. |
| [interceptPerform](intercept-perform.md) | `fun <T> interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction, activity: () -> `[`T`](intercept-perform.md#T)`): `[`T`](intercept-perform.md#T)<br>Wraps the given [activity](intercept-perform.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyDeviceBehaviorInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyDeviceBehaviorInterceptor.interceptPerform.T)))/activity) invocation with the system dialog safety. |
