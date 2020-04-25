[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll](../index.md) / [AutoScrollViewBehaviorInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun <T> intercept(interaction: ViewInteraction, action: () -> T): T`

Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor$intercept(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor.intercept.T)))/action) invocation with the autoscrolling on failure.

### Parameters

`interaction` - the intercepted [ViewInteraction](#).

`action` - the action to invoke.