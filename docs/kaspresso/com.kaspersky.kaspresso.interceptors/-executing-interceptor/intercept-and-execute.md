[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors](../index.md) / [ExecutingInterceptor](index.md) / [interceptAndExecute](./intercept-and-execute.md)

# interceptAndExecute

`abstract fun interceptAndExecute(function: () -> ViewInteraction): ViewInteraction`

Called to do some stuff and actually execute an action or an assertion.

### Parameters

`function` - a function-wrapper of an action or an assertion to be invoked.

**Return**
[ViewInteraction](#) as it is a result of [function](intercept-and-execute.md#com.kaspersky.kaspresso.interceptors.ExecutingInterceptor$interceptAndExecute(kotlin.Function0((android.support.test.espresso.ViewInteraction)))/function) invocation.

