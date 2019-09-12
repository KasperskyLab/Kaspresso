[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety](../index.md) / [SystemDialogSafetyDataBehaviorInterceptor](./index.md)

# SystemDialogSafetyDataBehaviorInterceptor

`class SystemDialogSafetyDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md)

The implementation of [DataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces.
Provides system dialog safety functionality for [DataInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SystemDialogSafetyDataBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, uiDevice: UiDevice)`<br>The implementation of [DataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [DataInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun <T> intercept(interaction: DataInteraction, action: () -> `[`T`](intercept.md#T)`): `[`T`](intercept.md#T)<br>Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyDataBehaviorInterceptor$intercept(android.support.test.espresso.DataInteraction, kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyDataBehaviorInterceptor.intercept.T)))/action) invocation with the system dialog safety. |
