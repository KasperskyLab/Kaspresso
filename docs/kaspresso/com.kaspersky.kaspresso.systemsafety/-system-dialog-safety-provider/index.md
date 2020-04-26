[kaspresso](../../index.md) / [com.kaspersky.kaspresso.systemsafety](../index.md) / [SystemDialogSafetyProvider](./index.md)

# SystemDialogSafetyProvider

`interface SystemDialogSafetyProvider`

An interface to provide system dialog safety functionality.

### Functions

| Name | Summary |
|---|---|
| [passSystemDialogs](pass-system-dialogs.md) | Invokes the given [action](pass-system-dialogs.md#com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider$passSystemDialogs(kotlin.Function0((com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider.passSystemDialogs.T)))/action) and hides the system dialog if the invocation is failed and the system dialog is actually shown via [suppressSystemDialogs](#) call.`abstract fun <T> passSystemDialogs(action: () -> T): T` |

### Inheritors

| Name | Summary |
|---|---|
| [SystemDialogSafetyDataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-data-behavior-interceptor/index.md) | The implementation of [DataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [DataInteraction.check](#) calls.`class SystemDialogSafetyDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md) |
| [SystemDialogSafetyDeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety/-system-dialog-safety-device-behavior-interceptor/index.md) | The implementation of [DeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls.`class SystemDialogSafetyDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md) |
| [SystemDialogSafetyObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety/-system-dialog-safety-object-behavior-interceptor/index.md) | The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls.`class SystemDialogSafetyObjectBehaviorInterceptor : `[`ObjectBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md) |
| [SystemDialogSafetyProviderImpl](../-system-dialog-safety-provider-impl/index.md) | The implementation of the [SystemDialogSafetyProvider](./index.md) interface.`class SystemDialogSafetyProviderImpl : `[`SystemDialogSafetyProvider`](./index.md) |
| [SystemDialogSafetyViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-view-behavior-interceptor/index.md) | The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.`class SystemDialogSafetyViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md) |
| [SystemDialogSafetyWebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-web-behavior-interceptor/index.md) | The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.`class SystemDialogSafetyWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md) |
