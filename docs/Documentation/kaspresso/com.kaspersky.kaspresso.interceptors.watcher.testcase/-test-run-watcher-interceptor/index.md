//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.testcase](../index.md)/[TestRunWatcherInterceptor](index.md)



# TestRunWatcherInterceptor  
 [androidJvm] 

The interface for all interceptors intercepting test run events.

interface [TestRunWatcherInterceptor](index.md) : [TestContextHolder](../-test-context-holder/index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md)| [androidJvm]  <br>Content  <br>open fun [onAfterSectionFinishedFailed](on-after-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [onAfterSectionFinishedSuccess](on-after-section-finished-success.md)| [androidJvm]  <br>Content  <br>open fun [onAfterSectionFinishedSuccess](on-after-section-finished-success.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onAfterSectionStarted](on-after-section-started.md)| [androidJvm]  <br>Content  <br>open fun [onAfterSectionStarted](on-after-section-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md)| [androidJvm]  <br>Content  <br>open fun [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [onBeforeSectionFinishedSuccess](on-before-section-finished-success.md)| [androidJvm]  <br>Content  <br>open fun [onBeforeSectionFinishedSuccess](on-before-section-finished-success.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onBeforeSectionStarted](on-before-section-started.md)| [androidJvm]  <br>Content  <br>open fun [onBeforeSectionStarted](on-before-section-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onMainSectionFinishedFailed](on-main-section-finished-failed.md)| [androidJvm]  <br>Content  <br>open fun [onMainSectionFinishedFailed](on-main-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [onMainSectionFinishedSuccess](on-main-section-finished-success.md)| [androidJvm]  <br>Content  <br>open fun [onMainSectionFinishedSuccess](on-main-section-finished-success.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onMainSectionStarted](on-main-section-started.md)| [androidJvm]  <br>Content  <br>open fun [onMainSectionStarted](on-main-section-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onTestFinished](on-test-finished.md)| [androidJvm]  <br>Content  <br>open fun [onTestFinished](on-test-finished.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), success: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [onTestStarted](on-test-started.md)| [androidJvm]  <br>Content  <br>open fun [onTestStarted](on-test-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [setBaseTestContext](../-test-context-holder/set-base-test-context.md)| [androidJvm]  <br>Content  <br>open override fun [setBaseTestContext](../-test-context-holder/set-base-test-context.md)(context: [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [TestRunCompositeWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite/-test-run-composite-watcher-interceptor/index.md)
| [DefaultTestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.defaults/-default-test-run-watcher-interceptor/index.md)
| [TestRunLoggerWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging/-test-run-logger-watcher-interceptor/index.md)
| [BuildStepReportWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.report/-build-step-report-watcher-interceptor/index.md)
| [TestRunnerScreenshotWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot/-test-runner-screenshot-watcher-interceptor/index.md)

