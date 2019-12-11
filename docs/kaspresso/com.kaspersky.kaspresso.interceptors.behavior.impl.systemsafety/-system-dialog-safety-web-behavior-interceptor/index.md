[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety](../index.md) / [SystemDialogSafetyWebBehaviorInterceptor](./index.md)

# SystemDialogSafetyWebBehaviorInterceptor

`class SystemDialogSafetyWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md)

The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces.
Provides system dialog safety functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SystemDialogSafetyWebBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, uiDevice: UiDevice, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)`<br>The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun <T> intercept(interaction: WebInteraction<*>, action: () -> `[`T`](intercept.md#T)`): `[`T`](intercept.md#T)<br>Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyWebBehaviorInterceptor$intercept(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyWebBehaviorInterceptor.intercept.T)))/action) invocation with the system dialog safety. |
