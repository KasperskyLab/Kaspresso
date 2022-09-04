//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite](../index.md)/[TestRunCompositeWatcherInterceptor](index.md)



# TestRunCompositeWatcherInterceptor  
 [androidJvm] 

The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface. Composes all of [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)s list into one composite [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) that is actually called by com.kaspersky.kaspresso.testcases.core.TestRunner on each test event.

class [TestRunCompositeWatcherInterceptor](index.md)(**watcherInterceptors**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)>, **logger**: [Logger](../../com.kaspersky.kaspresso.logger/-logger/index.md), **exceptions**: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>) : [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [TestRunCompositeWatcherInterceptor](-test-run-composite-watcher-interceptor.md)|  [androidJvm] fun [TestRunCompositeWatcherInterceptor](-test-run-composite-watcher-interceptor.md)(watcherInterceptors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)>, logger: [Logger](../../com.kaspersky.kaspresso.logger/-logger/index.md), exceptions: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>)   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| [Companion](-companion/index.md)| [androidJvm]  <br>Content  <br>object [Companion](-companion/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on "after" section finishes with failure, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onAfterSectionFinishedFailed](on-after-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [onAfterSectionFinishedSuccess](on-after-section-finished-success.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on "after" section finishes with success, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onAfterSectionFinishedSuccess](on-after-section-finished-success.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onAfterSectionStarted](on-after-section-started.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on "after" section starts, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onAfterSectionStarted](on-after-section-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on "before" section finishes with failure, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [onBeforeSectionFinishedSuccess](on-before-section-finished-success.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on "before" section finishes with success, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onBeforeSectionFinishedSuccess](on-before-section-finished-success.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onBeforeSectionStarted](on-before-section-started.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on "before" section starts, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onBeforeSectionStarted](on-before-section-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onMainSectionFinishedFailed](on-main-section-finished-failed.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on "main" section finishes with failure, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onMainSectionFinishedFailed](on-main-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [onMainSectionFinishedSuccess](on-main-section-finished-success.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on "main" section finishes with success, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onMainSectionFinishedSuccess](on-main-section-finished-success.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onMainSectionStarted](on-main-section-started.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on "main" section starts, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onMainSectionStarted](on-main-section-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [onTestFinished](on-test-finished.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on the whole test finishes, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onTestFinished](on-test-finished.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), success: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [onTestStarted](on-test-started.md)| [androidJvm]  <br>Brief description  <br><br><br>Called on the whole test starts, delegates the interception to watcherInterceptors.<br><br>  <br>Content  <br>open override fun [onTestStarted](on-test-started.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [setBaseTestContext](set-base-test-context.md)| [androidJvm]  <br>Content  <br>open override fun [setBaseTestContext](set-base-test-context.md)(context: [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

