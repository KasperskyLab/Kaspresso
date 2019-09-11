[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging](../index.md) / [LoggingStepWatcherInterceptor](index.md) / [interceptAfterFinally](./intercept-after-finally.md)

# interceptAfterFinally

`fun interceptAfterFinally(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [StepWatcherInterceptor.interceptAfterFinally](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/intercept-after-finally.md)

Logs the given [stepInfo](intercept-after-finally.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptAfterFinally(com.kaspersky.kaspresso.testcases.models.info.StepInfo)/stepInfo) on step finally finishes.

### Parameters

`stepInfo` - the step info to log.