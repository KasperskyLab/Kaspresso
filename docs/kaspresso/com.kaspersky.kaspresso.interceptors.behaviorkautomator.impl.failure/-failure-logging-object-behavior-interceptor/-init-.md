[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure](../index.md) / [FailureLoggingObjectBehaviorInterceptor](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FailureLoggingObjectBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`

The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces.
Provides failure logging functionality for [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) calls.

By default, this interceptor is not used in Kaspresso.
If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) directly

