[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety](../index.md) / [SystemDialogSafetyWebBehaviorInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`fun <T> intercept(interaction: WebInteraction<*>, action: () -> `[`T`](intercept.md#T)`): `[`T`](intercept.md#T)

Overrides [BehaviorInterceptor.intercept](../../com.kaspersky.kaspresso.interceptors.behavior/-behavior-interceptor/intercept.md)

Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyWebBehaviorInterceptor$intercept(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyWebBehaviorInterceptor.intercept.T)))/action) invocation with the system dialog safety.

### Parameters

`interaction` - the intercepted [Web.WebInteraction](#).

`action` - the action to invoke.