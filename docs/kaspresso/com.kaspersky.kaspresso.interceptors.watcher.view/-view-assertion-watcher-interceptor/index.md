//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view](../index.md)/[ViewAssertionWatcherInterceptor](index.md)



# ViewAssertionWatcherInterceptor  
 [androidJvm] 

The interface for all view action interceptors, used in [com.kaspersky.kaspresso.proxy.ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md).

interface [ViewAssertionWatcherInterceptor](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before androidx.test.espresso.ViewAssertion.check is actually called.<br><br>  <br>Content  <br>abstract fun [intercept](intercept.md)(viewAssertion: ViewAssertion, view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, exception: NoMatchingViewException?)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [LoggingViewAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-view-assertion-watcher-interceptor/index.md)

