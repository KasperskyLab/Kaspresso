//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md)/[LoggingViewAssertionWatcherInterceptor](index.md)



# LoggingViewAssertionWatcherInterceptor  
 [androidJvm] 

The implementation of [ViewAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md) that logs info about ViewAssertion.

class [LoggingViewAssertionWatcherInterceptor](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [ViewAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [LoggingViewAssertionWatcherInterceptor](-logging-view-assertion-watcher-interceptor.md)|  [androidJvm] fun [LoggingViewAssertionWatcherInterceptor](-logging-view-assertion-watcher-interceptor.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Writes info to logger.<br><br>  <br>Content  <br>open override fun [intercept](intercept.md)(viewAssertion: ViewAssertion, view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, exception: NoMatchingViewException?)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

