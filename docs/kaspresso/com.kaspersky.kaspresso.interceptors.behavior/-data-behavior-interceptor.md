[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behavior](index.md) / [DataBehaviorInterceptor](./-data-behavior-interceptor.md)

# DataBehaviorInterceptor

`interface DataBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<DataInteraction>`

The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [DataInteraction.check](#) behavior.

### Inheritors

| Name | Summary |
|---|---|
| [FailureLoggingDataBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.failure/-failure-logging-data-behavior-interceptor/index.md) | The implementation of [DataBehaviorInterceptor](./-data-behavior-interceptor.md) and [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for [DataInteraction.check](#) calls.`class FailureLoggingDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](./-data-behavior-interceptor.md)`, `[`FailureLoggingProvider`](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) |
| [FlakySafeDataBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety/-flaky-safe-data-behavior-interceptor/index.md) | The implementation of [DataBehaviorInterceptor](./-data-behavior-interceptor.md) and [FlakySafetyProvider](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [DataInteraction.check](#) calls.`class FlakySafeDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](./-data-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) |
| [SystemDialogSafetyDataBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-data-behavior-interceptor/index.md) | The implementation of [DataBehaviorInterceptor](./-data-behavior-interceptor.md) and [SystemDialogSafetyProvider](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [DataInteraction.check](#) calls.`class SystemDialogSafetyDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](./-data-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) |
