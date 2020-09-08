//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.testcase](../index.md)/[StepWatcherInterceptor](index.md)



# StepWatcherInterceptor  
 [androidJvm] 

The interface for all interceptors intercepting step events.

interface [StepWatcherInterceptor](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptAfterFinally](intercept-after-finally.md)| [androidJvm]  <br>Content  <br>open fun [interceptAfterFinally](intercept-after-finally.md)(stepInfo: [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md))  <br><br><br>
| [interceptAfterWithError](intercept-after-with-error.md)| [androidJvm]  <br>Content  <br>open fun [interceptAfterWithError](intercept-after-with-error.md)(stepInfo: [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md), error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [interceptAfterWithSuccess](intercept-after-with-success.md)| [androidJvm]  <br>Content  <br>open fun [interceptAfterWithSuccess](intercept-after-with-success.md)(stepInfo: [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md))  <br><br><br>
| [interceptBefore](intercept-before.md)| [androidJvm]  <br>Content  <br>open fun [interceptBefore](intercept-before.md)(stepInfo: [StepInfo](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [LoggingStepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging/-logging-step-watcher-interceptor/index.md)
| [ScreenshotStepWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot/-screenshot-step-watcher-interceptor/index.md)

