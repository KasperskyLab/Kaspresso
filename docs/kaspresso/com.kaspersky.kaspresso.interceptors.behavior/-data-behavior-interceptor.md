[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behavior](index.md) / [DataBehaviorInterceptor](./-data-behavior-interceptor.md)

# DataBehaviorInterceptor

`interface DataBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<DataInteraction>`

The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [DataInteraction.check](#) behavior.

### Inherited Functions

| Name | Summary |
|---|---|
| [intercept](-behavior-interceptor/intercept.md) | `abstract fun <T> intercept(interaction: `[`Interaction`](-behavior-interceptor/index.md#Interaction)`, action: () -> `[`T`](-behavior-interceptor/intercept.md#T)`): `[`T`](-behavior-interceptor/intercept.md#T)<br>Called to do some stuff and actually perform an interaction with element. |

### Inheritors

| Name | Summary |
|---|---|
| [FailureLoggingDataBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.failure/-failure-logging-data-behavior-interceptor/index.md) | `class FailureLoggingDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](./-data-behavior-interceptor.md)`, `[`FailureLoggingProvider`](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md)<br>The implementation of [DataBehaviorInterceptor](./-data-behavior-interceptor.md) and [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for [DataInteraction.check](#) calls. |
| [FlakySafeDataBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety/-flaky-safe-data-behavior-interceptor/index.md) | `class FlakySafeDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](./-data-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)<br>The implementation of [DataBehaviorInterceptor](./-data-behavior-interceptor.md) and [FlakySafetyProvider](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [DataInteraction.check](#) calls. |
| [SystemDialogSafetyDataBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-data-behavior-interceptor/index.md) | `class SystemDialogSafetyDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](./-data-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md)<br>The implementation of [DataBehaviorInterceptor](./-data-behavior-interceptor.md) and [SystemDialogSafetyProvider](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [DataInteraction.check](#) calls. |
