[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging](../index.md) / [LoggingStepWatcherInterceptor](./index.md)

# LoggingStepWatcherInterceptor

`class LoggingStepWatcherInterceptor : `[`StepWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)

The implementation of the [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md) interface.
Logs [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md) on each step event.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingStepWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of the [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md) interface. Logs [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md) on each step event. |

### Functions

| Name | Summary |
|---|---|
| [interceptAfterFinally](intercept-after-finally.md) | `fun interceptAfterFinally(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Logs the given [stepInfo](intercept-after-finally.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptAfterFinally(com.kaspersky.kaspresso.testcases.models.info.StepInfo)/stepInfo) on step finally finishes. |
| [interceptAfterWithError](intercept-after-with-error.md) | `fun interceptAfterWithError(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`, error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Logs the given [stepInfo](intercept-after-with-error.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptAfterWithError(com.kaspersky.kaspresso.testcases.models.info.StepInfo, kotlin.Throwable)/stepInfo) on step finishes with failure. |
| [interceptAfterWithSuccess](intercept-after-with-success.md) | `fun interceptAfterWithSuccess(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Logs the given [stepInfo](intercept-after-with-success.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptAfterWithSuccess(com.kaspersky.kaspresso.testcases.models.info.StepInfo)/stepInfo) on step finishes with success. |
| [interceptBefore](intercept-before.md) | `fun interceptBefore(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Logs the given [stepInfo](intercept-before.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor$interceptBefore(com.kaspersky.kaspresso.testcases.models.info.StepInfo)/stepInfo) on step starts. |
