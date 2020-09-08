//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view](../index.md)/[ViewActionWatcherInterceptor](index.md)



# ViewActionWatcherInterceptor  
 [androidJvm] 

The interface for all view action interceptors, used in [com.kaspersky.kaspresso.proxy.ViewActionProxy](../../com.kaspersky.kaspresso.proxy/-view-action-proxy/index.md).

interface [ViewActionWatcherInterceptor](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before ViewAction.perform is actually called.<br><br>  <br>Content  <br>abstract fun [intercept](intercept.md)(viewAction: ViewAction, view: [View](https://developer.android.com/reference/kotlin/android/view/View.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [LoggingViewActionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-view-action-watcher-interceptor/index.md)

