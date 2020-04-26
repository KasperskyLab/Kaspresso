[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.failure](../index.md) / [FailureLoggingWebBehaviorInterceptor](./index.md)

# FailureLoggingWebBehaviorInterceptor

`class FailureLoggingWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`, `[`FailureLoggingProvider`](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md)

The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces.
Provides failure logging functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.

By default, this interceptor is not used in Kaspresso.
If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) directly

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.`FailureLoggingWebBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingWebBehaviorInterceptor$intercept(androidx.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingWebBehaviorInterceptor.intercept.T)))/action) invocation with the failure logging.`fun <T> intercept(interaction: WebInteraction<*>, action: () -> T): T` |
