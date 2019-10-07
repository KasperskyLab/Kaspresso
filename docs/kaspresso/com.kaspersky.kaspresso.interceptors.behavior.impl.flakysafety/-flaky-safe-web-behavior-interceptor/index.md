[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety](../index.md) / [FlakySafeWebBehaviorInterceptor](./index.md)

# FlakySafeWebBehaviorInterceptor

`class FlakySafeWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)

The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces.
Provides system flaky safety functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FlakySafeWebBehaviorInterceptor(params: `[`FlakySafetyParams`](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun <T> intercept(interaction: WebInteraction<*>, action: () -> `[`T`](intercept.md#T)`): `[`T`](intercept.md#T)<br>Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor$intercept(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor.intercept.T)))/action) invocation with the flaky safety. |
