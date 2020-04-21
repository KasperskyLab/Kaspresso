[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator](../index.md) / [KautomatorBehaviorInterceptor](index.md) / [interceptCheck](./intercept-check.md)

# interceptCheck

`abstract fun <T> interceptCheck(interaction: `[`Interaction`](index.md#Interaction)`, assertion: `[`Assertion`](index.md#Assertion)`, activity: () -> `[`T`](intercept-check.md#T)`): `[`T`](intercept-check.md#T)

Called to do some stuff and actually check an interaction with element.

### Parameters

`activity` - a function-wrapper of an action or an assertion to be invoked.