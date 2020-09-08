//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot](../index.md)/[ScreenshotStepWatcherInterceptor](index.md)



# ScreenshotStepWatcherInterceptor  
 [androidJvm] 

The implementation of the [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md) interface. Takes screenshots if step succeeds or fails.

class [ScreenshotStepWatcherInterceptor](index.md)(**screenshots**: [Screenshots](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md)) : [StepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ScreenshotStepWatcherInterceptor](-screenshot-step-watcher-interceptor.md)|  [androidJvm] fun [ScreenshotStepWatcherInterceptor](-screenshot-step-watcher-interceptor.md)(screenshots: [Screenshots](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptAfterFinally](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/intercept-after-finally.md)| [androidJvm]  <br>Content  <br>open override fun [interceptAfterFinally](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/intercept-after-finally.md)(stepInfo: [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md))  <br><br><br>
| [interceptAfterWithError](intercept-after-with-error.md)| [androidJvm]  <br>Brief description  <br><br><br>Takes a screenshot of the screen on which the step falied.<br><br>  <br>Content  <br>open override fun [interceptAfterWithError](intercept-after-with-error.md)(stepInfo: [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md), error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [interceptAfterWithSuccess](intercept-after-with-success.md)| [androidJvm]  <br>Brief description  <br><br><br>Takes a screenshot of the screen on which the step succeeded.<br><br>  <br>Content  <br>open override fun [interceptAfterWithSuccess](intercept-after-with-success.md)(stepInfo: [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md))  <br><br><br>
| [interceptBefore](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/intercept-before.md)| [androidJvm]  <br>Content  <br>open override fun [interceptBefore](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/intercept-before.md)(stepInfo: [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

