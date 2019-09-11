[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging](../index.md) / [LoggingStepWatcherInterceptor](index.md) / [interceptAfterWithError](./intercept-after-with-error.md)

# interceptAfterWithError

`fun interceptAfterWithError(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`, error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [StepWatcherInterceptor.interceptAfterWithError](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/intercept-after-with-error.md)

Logs the given [stepInfo](intercept-after-with-error.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptAfterWithError(com.kaspersky.kaspresso.testcases.models.info.StepInfo, kotlin.Throwable)/stepInfo) on step finishes with failure.

### Parameters

`stepInfo` - the step info to log.

`error` - the error occurred to log.