//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view](../index.md)/[AtomWatcherInterceptor](index.md)



# AtomWatcherInterceptor  
 [androidJvm] 

The interface for all atom interceptors, used in [com.kaspersky.kaspresso.proxy.AtomProxy](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md).

interface [AtomWatcherInterceptor](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before androidx.test.espresso.web.model.Atom.transform is actually called.<br><br>  <br>Content  <br>abstract fun [intercept](intercept.md)(atomProxy: [AtomProxy](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md)<*>, evaluation: Evaluation?)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [LoggingAtomWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-atom-watcher-interceptor/index.md)

