[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator](index.md) / [DeviceBehaviorInterceptor](./-device-behavior-interceptor.md)

# DeviceBehaviorInterceptor

`interface DeviceBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](-kautomator-behavior-interceptor/index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>`

The derived from [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) interface for intercepting [UiDeviceInteraction.perform](#) and
[UiDeviceInteraction.check](#) behavior.

### Inheritors

| Name | Summary |
|---|---|
| [FailureLoggingDeviceBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure/-failure-logging-device-behavior-interceptor/index.md) | The implementation of [DeviceBehaviorInterceptor](./-device-behavior-interceptor.md) and [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls.`class FailureLoggingDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](./-device-behavior-interceptor.md)`, `[`FailureLoggingProvider`](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) |
| [FlakySafeDeviceBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety/-flaky-safe-device-behavior-interceptor/index.md) | The implementation of [DeviceBehaviorInterceptor](./-device-behavior-interceptor.md) and [FlakySafetyProvider](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls.`class FlakySafeDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](./-device-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) |
| [SystemDialogSafetyDeviceBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety/-system-dialog-safety-device-behavior-interceptor/index.md) | The implementation of [DeviceBehaviorInterceptor](./-device-behavior-interceptor.md) and [SystemDialogSafetyProvider](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls.`class SystemDialogSafetyDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](./-device-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) |
