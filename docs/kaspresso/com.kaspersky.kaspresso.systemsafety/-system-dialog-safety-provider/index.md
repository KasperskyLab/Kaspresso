[kaspresso](../../index.md) / [com.kaspersky.kaspresso.systemsafety](../index.md) / [SystemDialogSafetyProvider](./index.md)

# SystemDialogSafetyProvider

`interface SystemDialogSafetyProvider`

An interface to provide system dialog safety functionality.

### Functions

| Name | Summary |
|---|---|
| [passSystemDialogs](pass-system-dialogs.md) | `abstract fun <T> passSystemDialogs(action: () -> `[`T`](pass-system-dialogs.md#T)`): `[`T`](pass-system-dialogs.md#T)<br>Invokes the given [action](pass-system-dialogs.md#com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider$passSystemDialogs(kotlin.Function0((com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider.passSystemDialogs.T)))/action) and hides the system dialog if the invocation is failed and the system dialog is actually shown via [suppressSystemDialogs](#) call. |

### Inheritors

| Name | Summary |
|---|---|
| [SystemDialogSafetyDataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-data-behavior-interceptor/index.md) | `class SystemDialogSafetyDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md)<br>The implementation of [DataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [DataInteraction.check](#) calls. |
| [SystemDialogSafetyDeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety/-system-dialog-safety-device-behavior-interceptor/index.md) | `class SystemDialogSafetyDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md)<br>The implementation of [DeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls. |
| [SystemDialogSafetyObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety/-system-dialog-safety-object-behavior-interceptor/index.md) | `class SystemDialogSafetyObjectBehaviorInterceptor : `[`ObjectBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md)<br>The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls. |
| [SystemDialogSafetyProviderImpl](../-system-dialog-safety-provider-impl/index.md) | `class SystemDialogSafetyProviderImpl : `[`SystemDialogSafetyProvider`](./index.md)<br>The implementation of the [SystemDialogSafetyProvider](./index.md) interface. |
| [SystemDialogSafetyViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-view-behavior-interceptor/index.md) | `class SystemDialogSafetyViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md)<br>The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls. |
| [SystemDialogSafetyWebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-web-behavior-interceptor/index.md) | `class SystemDialogSafetyWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](./index.md)<br>The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [SystemDialogSafetyProvider](./index.md) interfaces. Provides system dialog safety functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls. |
