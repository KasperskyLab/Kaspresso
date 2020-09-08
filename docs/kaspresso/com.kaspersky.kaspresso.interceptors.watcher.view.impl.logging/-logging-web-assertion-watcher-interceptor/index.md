//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md)/[LoggingWebAssertionWatcherInterceptor](index.md)



# LoggingWebAssertionWatcherInterceptor  
 [androidJvm] 

The implementation of [WebAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md) that logs info about androidx.test.espresso.web.assertion.WebAssertion.

class [LoggingWebAssertionWatcherInterceptor](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [WebAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [LoggingWebAssertionWatcherInterceptor](-logging-web-assertion-watcher-interceptor.md)|  [androidJvm] fun [LoggingWebAssertionWatcherInterceptor](-logging-web-assertion-watcher-interceptor.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Writes info to compositeLogger.<br><br>  <br>Content  <br>open override fun [intercept](intercept.md)(webAssertionProxy: [WebAssertionProxy](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)<*>, view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, result: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

