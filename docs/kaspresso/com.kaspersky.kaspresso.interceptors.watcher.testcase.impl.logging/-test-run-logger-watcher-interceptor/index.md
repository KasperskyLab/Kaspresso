//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging](../index.md)/[TestRunLoggerWatcherInterceptor](index.md)



# TestRunLoggerWatcherInterceptor  
 [androidJvm] 

The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface. Logs [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md) on each section event.

class [TestRunLoggerWatcherInterceptor](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [TestRunLoggerWatcherInterceptor](-test-run-logger-watcher-interceptor.md)|  [androidJvm] fun [TestRunLoggerWatcherInterceptor](-test-run-logger-watcher-interceptor.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the given testInfo on "after" section finishes with failure.<br><br>  <br>Content  <br>open override fun [onAfterSectionFinishedFailed](on-after-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [onAfterSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-after-section-finished-success.md)| [androidJvm]  <br>Content  <br>open override fun [onAfterSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-after-section-finished-success.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onAfterSectionStarted](on-after-section-started.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the given testInfo on "after" section starts.<br><br>  <br>Content  <br>open override fun [onAfterSectionStarted](on-after-section-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the given testInfo on "before" section finishes with failure.<br><br>  <br>Content  <br>open override fun [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [onBeforeSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-before-section-finished-success.md)| [androidJvm]  <br>Content  <br>open override fun [onBeforeSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-before-section-finished-success.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onBeforeSectionStarted](on-before-section-started.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the given testInfo on "before" section starts.<br><br>  <br>Content  <br>open override fun [onBeforeSectionStarted](on-before-section-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onMainSectionFinishedFailed](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-finished-failed.md)| [androidJvm]  <br>Content  <br>open override fun [onMainSectionFinishedFailed](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [onMainSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-finished-success.md)| [androidJvm]  <br>Content  <br>open override fun [onMainSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-finished-success.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onMainSectionStarted](on-main-section-started.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the given testInfo on "main" section starts.<br><br>  <br>Content  <br>open override fun [onMainSectionStarted](on-main-section-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onTestFinished](on-test-finished.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the given testInfo on whole test finishes.<br><br>  <br>Content  <br>open override fun [onTestFinished](on-test-finished.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), success: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [onTestStarted](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-test-started.md)| [androidJvm]  <br>Content  <br>open override fun [onTestStarted](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-test-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [setBaseTestContext](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-context-holder/set-base-test-context.md)| [androidJvm]  <br>Content  <br>open override fun [setBaseTestContext](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-context-holder/set-base-test-context.md)(context: [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

