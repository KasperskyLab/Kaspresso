[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.flakysafety](../index.md) / [FlakySafeExecutingInterceptor](index.md) / [interceptAndExecute](./intercept-and-execute.md)

# interceptAndExecute

`fun interceptAndExecute(function: () -> ViewInteraction): ViewInteraction`

Overrides [ExecutingInterceptor.interceptAndExecute](../../com.kaspersky.kaspresso.interceptors/-executing-interceptor/intercept-and-execute.md)

Performs multiple attempts to execute an action or an assertion.

### Parameters

`function` - a function-wrapper of an action or an assertion to be invoked.

**Return**
[ViewInteraction](#) as it is a result of [function](intercept-and-execute.md#com.kaspersky.kaspresso.interceptors.impl.flakysafety.FlakySafeExecutingInterceptor$interceptAndExecute(kotlin.Function0((android.support.test.espresso.ViewInteraction)))/function) invocation.

