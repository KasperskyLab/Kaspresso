[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator](../index.md) / [KautomatorBehaviorInterceptor](index.md) / [interceptPerform](./intercept-perform.md)

# interceptPerform

`abstract fun <T> interceptPerform(interaction: Interaction, action: Action, activity: () -> T): T`

Called to do some stuff and actually perform an interaction with element.

### Parameters

`activity` - a function-wrapper of an action or an assertion to be invoked.