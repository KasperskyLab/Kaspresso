[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety](../index.md) / [SystemDialogSafetyViewBehaviorInterceptor](./index.md)

# SystemDialogSafetyViewBehaviorInterceptor

`class SystemDialogSafetyViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md)

The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces.
Provides system dialog safety functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.`SystemDialogSafetyViewBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, uiDevice: UiDevice, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyViewBehaviorInterceptor$intercept(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyViewBehaviorInterceptor.intercept.T)))/action) invocation with the system dialog safety.`fun <T> intercept(interaction: ViewInteraction, action: () -> T): T` |
