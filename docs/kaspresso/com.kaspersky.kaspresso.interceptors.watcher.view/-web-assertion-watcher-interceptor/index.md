//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view](../index.md)/[WebAssertionWatcherInterceptor](index.md)



# WebAssertionWatcherInterceptor  
 [androidJvm] 

The interface for all atom interceptors, used in [WebAssertionProxy](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md).

interface [WebAssertionWatcherInterceptor](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before androidx.test.espresso.web.assertion.WebAssertion.checkResult is actually called.<br><br>  <br>Content  <br>abstract fun [intercept](intercept.md)(webAssertionProxy: [WebAssertionProxy](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)<*>, view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, result: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [LoggingWebAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-web-assertion-watcher-interceptor/index.md)

