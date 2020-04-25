[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging](../index.md) / [LoggingStepWatcherInterceptor](./index.md)

# LoggingStepWatcherInterceptor

`class LoggingStepWatcherInterceptor : `[`StepWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)

The implementation of the [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md) interface.
Logs [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md) on each step event.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md) interface. Logs [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md) on each step event.`LoggingStepWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [interceptAfterFinally](intercept-after-finally.md) | Logs the given [stepInfo](intercept-after-finally.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptAfterFinally(com.kaspersky.kaspresso.testcases.models.info.StepInfo)/stepInfo) on step finally finishes.`fun interceptAfterFinally(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): Unit` |
| [interceptAfterWithError](intercept-after-with-error.md) | Logs the given [stepInfo](intercept-after-with-error.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptAfterWithError(com.kaspersky.kaspresso.testcases.models.info.StepInfo, kotlin.Throwable)/stepInfo) on step finishes with failure.`fun interceptAfterWithError(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`, error: Throwable): Unit` |
| [interceptAfterWithSuccess](intercept-after-with-success.md) | Logs the given [stepInfo](intercept-after-with-success.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptAfterWithSuccess(com.kaspersky.kaspresso.testcases.models.info.StepInfo)/stepInfo) on step finishes with success.`fun interceptAfterWithSuccess(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): Unit` |
| [interceptBefore](intercept-before.md) | Logs the given [stepInfo](intercept-before.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptBefore(com.kaspersky.kaspresso.testcases.models.info.StepInfo)/stepInfo) on step starts.`fun interceptBefore(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): Unit` |
