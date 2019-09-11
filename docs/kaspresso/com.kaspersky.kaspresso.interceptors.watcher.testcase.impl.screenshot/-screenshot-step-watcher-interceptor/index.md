[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot](../index.md) / [ScreenshotStepWatcherInterceptor](./index.md)

# ScreenshotStepWatcherInterceptor

`class ScreenshotStepWatcherInterceptor : `[`StepWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)

The implementation of the [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md) interface.
Takes screenshots if step succeeds or fails.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ScreenshotStepWatcherInterceptor(screenshots: `[`Screenshots`](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md)`)`<br>The implementation of the [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md) interface. Takes screenshots if step succeeds or fails. |

### Functions

| Name | Summary |
|---|---|
| [interceptAfterWithError](intercept-after-with-error.md) | `fun interceptAfterWithError(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`, error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Takes a screenshot of the screen on which the step falied. |
| [interceptAfterWithSuccess](intercept-after-with-success.md) | `fun interceptAfterWithSuccess(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Takes a screenshot of the screen on which the step succeeded. |

### Inherited Functions

| Name | Summary |
|---|---|
| [interceptAfterFinally](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/intercept-after-finally.md) | `open fun interceptAfterFinally(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [interceptBefore](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/intercept-before.md) | `open fun interceptBefore(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
