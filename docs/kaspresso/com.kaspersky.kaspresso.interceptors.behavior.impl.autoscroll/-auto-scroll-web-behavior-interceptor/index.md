[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll](../index.md) / [AutoScrollWebBehaviorInterceptor](./index.md)

# AutoScrollWebBehaviorInterceptor

`class AutoScrollWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`, `[`AutoScrollProvider`](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md)`<WebInteraction<*>>`

The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces.
Provides autoscroll on failure functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces. Provides autoscroll on failure functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.`AutoScrollWebBehaviorInterceptor(params: `[`AutoScrollParams`](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollWebBehaviorInterceptor$intercept(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollWebBehaviorInterceptor.intercept.T)))/action) invocation with the autoscrolling on failure.`fun <T> intercept(interaction: WebInteraction<*>, action: () -> T): T` |
