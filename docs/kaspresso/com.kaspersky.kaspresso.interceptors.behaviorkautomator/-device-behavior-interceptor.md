[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator](index.md) / [DeviceBehaviorInterceptor](./-device-behavior-interceptor.md)

# DeviceBehaviorInterceptor

`interface DeviceBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](-kautomator-behavior-interceptor/index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>`

The derived from [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) interface for intercepting [UiDeviceInteraction.perform](#) and
[UiDeviceInteraction.check](#) behavior.

### Inherited Functions

| Name | Summary |
|---|---|
| [interceptCheck](-kautomator-behavior-interceptor/intercept-check.md) | `abstract fun <T> interceptCheck(interaction: `[`Interaction`](-kautomator-behavior-interceptor/index.md#Interaction)`, assertion: `[`Assertion`](-kautomator-behavior-interceptor/index.md#Assertion)`, activity: () -> `[`T`](-kautomator-behavior-interceptor/intercept-check.md#T)`): `[`T`](-kautomator-behavior-interceptor/intercept-check.md#T)<br>Called to do some stuff and actually check an interaction with element. |
| [interceptPerform](-kautomator-behavior-interceptor/intercept-perform.md) | `abstract fun <T> interceptPerform(interaction: `[`Interaction`](-kautomator-behavior-interceptor/index.md#Interaction)`, action: `[`Action`](-kautomator-behavior-interceptor/index.md#Action)`, activity: () -> `[`T`](-kautomator-behavior-interceptor/intercept-perform.md#T)`): `[`T`](-kautomator-behavior-interceptor/intercept-perform.md#T)<br>Called to do some stuff and actually perform an interaction with element. |

### Inheritors

| Name | Summary |
|---|---|
| [FailureLoggingDeviceBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure/-failure-logging-device-behavior-interceptor/index.md) | `class FailureLoggingDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](./-device-behavior-interceptor.md)`, `[`FailureLoggingProvider`](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md)<br>The implementation of [DeviceBehaviorInterceptor](./-device-behavior-interceptor.md) and [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls. |
| [FlakySafeDeviceBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety/-flaky-safe-device-behavior-interceptor/index.md) | `class FlakySafeDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](./-device-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)<br>The implementation of [DeviceBehaviorInterceptor](./-device-behavior-interceptor.md) and [FlakySafetyProvider](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls. |
| [SystemDialogSafetyDeviceBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety/-system-dialog-safety-device-behavior-interceptor/index.md) | `class SystemDialogSafetyDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](./-device-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md)<br>The implementation of [DeviceBehaviorInterceptor](./-device-behavior-interceptor.md) and [SystemDialogSafetyProvider](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls. |
