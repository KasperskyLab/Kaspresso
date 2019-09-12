[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll](../index.md) / [AutoScrollWebBehaviorInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun <T> intercept(interaction: WebInteraction<*>, action: () -> `[`T`](intercept.md#T)`): `[`T`](intercept.md#T)

Overrides [BehaviorInterceptor.intercept](../../com.kaspersky.kaspresso.interceptors.behavior/-behavior-interceptor/intercept.md)

Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollWebBehaviorInterceptor$intercept(android.support.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollWebBehaviorInterceptor.intercept.T)))/action) invocation with the autoscrolling on failure.

### Parameters

`interaction` - the intercepted [Web.WebInteraction](#).

`action` - the action to invoke.