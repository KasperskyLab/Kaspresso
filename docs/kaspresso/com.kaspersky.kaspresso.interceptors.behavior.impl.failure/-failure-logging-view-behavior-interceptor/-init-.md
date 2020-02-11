[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.failure](../index.md) / [FailureLoggingViewBehaviorInterceptor](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FailureLoggingViewBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`

The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces.
Provides failure logging functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.

By default, this interceptor is not used in Kaspresso.
If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) directly

