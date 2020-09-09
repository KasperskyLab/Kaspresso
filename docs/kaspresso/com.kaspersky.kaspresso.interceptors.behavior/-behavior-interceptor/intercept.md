[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior](../index.md) / [BehaviorInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`abstract fun <T> intercept(interaction: Interaction, action: () -> T): T`

Called to do some stuff and actually perform an interaction with element.

### Parameters

`action` - a function-wrapper of an action or an assertion to be invoked.