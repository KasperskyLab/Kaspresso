[kaspresso](../../index.md) / [com.kaspersky.kaspresso.uiobjectloader](../index.md) / [UiObjectLoaderProvider](./index.md)

# UiObjectLoaderProvider

`interface UiObjectLoaderProvider`

The interface to provide the functionality to load [androidx.test.uiautomator.UiObject2](#) in case of its absence or stale.

### Functions

| Name | Summary |
|---|---|
| [handleUiObjectAbsence](handle-ui-object-absence.md) | `abstract fun <T> handleUiObjectAbsence(interaction: UiObjectInteraction, action: () -> `[`T`](handle-ui-object-absence.md#T)`): `[`T`](handle-ui-object-absence.md#T) |

### Inheritors

| Name | Summary |
|---|---|
| [UiObjectLoaderBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader/-ui-object-loader-behavior-interceptor/index.md) | `class UiObjectLoaderBehaviorInterceptor : `[`ObjectBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)`, `[`UiObjectLoaderProvider`](./index.md)<br>The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [UiObjectLoaderProvider](./index.md) interfaces. Provides system flaky safety functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls. |
| [UiObjectLoaderProviderImpl](../-ui-object-loader-provider-impl/index.md) | `class UiObjectLoaderProviderImpl : `[`UiObjectLoaderProvider`](./index.md)<br>The implementation of the [UiObjectLoaderProvider](./index.md) interface. |
