[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.failure](../index.md) / [FailureLoggingViewBehaviorInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun <T> intercept(interaction: ViewInteraction, action: () -> T): T`

Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingViewBehaviorInterceptor$intercept(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingViewBehaviorInterceptor.intercept.T)))/action) invocation with the failure logging.

### Parameters

`interaction` - the intercepted [ViewInteraction](#).

`action` - the action to invoke.