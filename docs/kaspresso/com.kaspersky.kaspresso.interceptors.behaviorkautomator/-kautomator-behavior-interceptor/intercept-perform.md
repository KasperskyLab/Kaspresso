[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator](../index.md) / [KautomatorBehaviorInterceptor](index.md) / [interceptPerform](./intercept-perform.md)

# interceptPerform

`abstract fun <T> interceptPerform(interaction: `[`Interaction`](index.md#Interaction)`, action: `[`Action`](index.md#Action)`, activity: () -> `[`T`](intercept-perform.md#T)`): `[`T`](intercept-perform.md#T)

Called to do some stuff and actually perform an interaction with element.

### Parameters

`activity` - a function-wrapper of an action or an assertion to be invoked.