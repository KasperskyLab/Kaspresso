[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase](../index.md) / [StepWatcherInterceptor](./index.md)

# StepWatcherInterceptor

`interface StepWatcherInterceptor`

The interface for all interceptors intercepting step events.

### Functions

| Name | Summary |
|---|---|
| [interceptAfterFinally](intercept-after-finally.md) | `open fun interceptAfterFinally(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [interceptAfterWithError](intercept-after-with-error.md) | `open fun interceptAfterWithError(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`, error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [interceptAfterWithSuccess](intercept-after-with-success.md) | `open fun interceptAfterWithSuccess(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [interceptBefore](intercept-before.md) | `open fun interceptBefore(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingStepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging/-logging-step-watcher-interceptor/index.md) | The implementation of the [StepWatcherInterceptor](./index.md) interface. Logs [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md) on each step event.`class LoggingStepWatcherInterceptor : `[`StepWatcherInterceptor`](./index.md) |
| [ScreenshotStepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot/-screenshot-step-watcher-interceptor/index.md) | The implementation of the [StepWatcherInterceptor](./index.md) interface. Takes screenshots if step succeeds or fails.`class ScreenshotStepWatcherInterceptor : `[`StepWatcherInterceptor`](./index.md) |
