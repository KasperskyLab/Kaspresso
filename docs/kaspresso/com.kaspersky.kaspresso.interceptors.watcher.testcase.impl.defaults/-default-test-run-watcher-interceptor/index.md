[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.defaults](../index.md) / [DefaultTestRunWatcherInterceptor](./index.md)

# DefaultTestRunWatcherInterceptor

`class DefaultTestRunWatcherInterceptor : `[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DefaultTestRunWatcherInterceptor()` |

### Functions

| Name | Summary |
|---|---|
| [afterEachTest](after-each-test.md) | `fun afterEachTest(override: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, action: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the action which will be executed after the test. The action has access to BaseTestContext. If you set @param override in false then the final beforeAction will be     beforeAction of the parent TestCase plus current @param action.     Otherwise final beforeAction will be only @param action. |
| [beforeEachTest](before-each-test.md) | `fun beforeEachTest(override: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, action: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the action which will be executed before the test. The action has access to BaseTestContext. If you set @param override in false then the final beforeAction will be     beforeAction of the parent TestCase plus current @param action.     Otherwise final beforeAction will be only @param action. |
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md) | `fun onAfterSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onAfterSectionFinishedSuccess](on-after-section-finished-success.md) | `fun onAfterSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionStarted](on-before-section-started.md) | `fun onBeforeSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setBaseTestContext](set-base-test-context.md) | `fun setBaseTestContext(context: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [onAfterSectionStarted](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-after-section-started.md) | `open fun onAfterSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionFinishedFailed](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-before-section-finished-failed.md) | `open fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-before-section-finished-success.md) | `open fun onBeforeSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionFinishedFailed](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-finished-failed.md) | `open fun onMainSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-finished-success.md) | `open fun onMainSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionStarted](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-started.md) | `open fun onMainSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onTestFinished](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-test-finished.md) | `open fun onTestFinished(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, success: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onTestStarted](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-test-started.md) | `open fun onTestStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
