[kaspresso](../../index.md) / [com.kaspersky.kaspresso.uiobjectloader](../index.md) / [UiObjectLoaderProviderImpl](./index.md)

# UiObjectLoaderProviderImpl

`class UiObjectLoaderProviderImpl : `[`UiObjectLoaderProvider`](../-ui-object-loader-provider/index.md)

The implementation of the [UiObjectLoaderProvider](../-ui-object-loader-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiObjectLoaderProviderImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of the [UiObjectLoaderProvider](../-ui-object-loader-provider/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [handleUiObjectAbsence](handle-ui-object-absence.md) | `fun <T> handleUiObjectAbsence(interaction: UiObjectInteraction, action: () -> `[`T`](handle-ui-object-absence.md#T)`): `[`T`](handle-ui-object-absence.md#T)<br>Attempt to find [androidx.test.uiautomator.UiObject2](#) in case of [androidx.test.uiautomator.UiObject2](#) absence or stale |
