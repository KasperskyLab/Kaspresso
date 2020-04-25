[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot](../index.md) / [ScreenshotStepWatcherInterceptor](./index.md)

# ScreenshotStepWatcherInterceptor

`class ScreenshotStepWatcherInterceptor : `[`StepWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)

The implementation of the [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md) interface.
Takes screenshots if step succeeds or fails.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md) interface. Takes screenshots if step succeeds or fails.`ScreenshotStepWatcherInterceptor(screenshots: `[`Screenshots`](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [interceptAfterWithError](intercept-after-with-error.md) | Takes a screenshot of the screen on which the step falied.`fun interceptAfterWithError(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`, error: Throwable): Unit` |
| [interceptAfterWithSuccess](intercept-after-with-success.md) | Takes a screenshot of the screen on which the step succeeded.`fun interceptAfterWithSuccess(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`): Unit` |
