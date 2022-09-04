//[kaspresso](../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite](index.md)



# Package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite  


## Types  
  
|  Name|  Summary| 
|---|---|
| [TestRunCompositeWatcherInterceptor](-test-run-composite-watcher-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The implementation of the [TestRunWatcherInterceptor](../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface. Composes all of [TestRunWatcherInterceptor](../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)s list into one composite [TestRunWatcherInterceptor](../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) that is actually called by com.kaspersky.kaspresso.testcases.core.TestRunner on each test event.<br><br>  <br>Content  <br>class [TestRunCompositeWatcherInterceptor](-test-run-composite-watcher-interceptor/index.md)(**watcherInterceptors**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TestRunWatcherInterceptor](../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)>, **logger**: [Logger](../com.kaspersky.kaspresso.logger/-logger/index.md), **exceptions**: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>) : [TestRunWatcherInterceptor](../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)  <br><br><br>

