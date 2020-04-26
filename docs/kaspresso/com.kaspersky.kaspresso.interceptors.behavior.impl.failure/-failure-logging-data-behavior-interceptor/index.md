[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.failure](../index.md) / [FailureLoggingDataBehaviorInterceptor](./index.md)

# FailureLoggingDataBehaviorInterceptor

`class FailureLoggingDataBehaviorInterceptor : `[`DataBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)`, `[`FailureLoggingProvider`](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md)

The implementation of [DataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces.
Provides failure logging functionality for [DataInteraction.check](#) calls.

By default, this interceptor is not used in Kaspresso.
If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) directly

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [DataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for [DataInteraction.check](#) calls.`FailureLoggingDataBehaviorInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingDataBehaviorInterceptor$intercept(androidx.test.espresso.DataInteraction, kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingDataBehaviorInterceptor.intercept.T)))/action) invocation with the failure logging.`fun <T> intercept(interaction: DataInteraction, action: () -> T): T` |
