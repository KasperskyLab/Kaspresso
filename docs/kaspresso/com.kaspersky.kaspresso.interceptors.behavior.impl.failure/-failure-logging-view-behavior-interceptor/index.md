[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.failure](../index.md) / [FailureLoggingViewBehaviorInterceptor](./index.md)

# FailureLoggingViewBehaviorInterceptor

`class FailureLoggingViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`, `[`FailureLoggingProvider`](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md)

The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces.
Provides failure logging functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.

By default, this interceptor is not used in Kaspresso.
If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) directly

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FailureLoggingViewBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun <T> intercept(interaction: ViewInteraction, action: () -> `[`T`](intercept.md#T)`): `[`T`](intercept.md#T)<br>Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingViewBehaviorInterceptor$intercept(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingViewBehaviorInterceptor.intercept.T)))/action) invocation with the failure logging. |
