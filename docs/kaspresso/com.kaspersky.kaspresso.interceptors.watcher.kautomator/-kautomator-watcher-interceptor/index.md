//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.kautomator](../index.md)/[KautomatorWatcherInterceptor](index.md)



# KautomatorWatcherInterceptor  
 [androidJvm] 

The interface for all interceptors that are watching the default interaction in Kautomator.

interface [KautomatorWatcherInterceptor](index.md)<[Interaction](index.md), [Assertion](index.md), [Action](index.md)>   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptCheck](intercept-check.md)| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before UiInteraction.check is actually called.<br><br>  <br>Content  <br>abstract fun [interceptCheck](intercept-check.md)(interaction: [Interaction](index.md), assertion: [Assertion](index.md))  <br><br><br>
| [interceptPerform](intercept-perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before UiInteraction.perform is actually called.<br><br>  <br>Content  <br>abstract fun [interceptPerform](intercept-perform.md)(interaction: [Interaction](index.md), action: [Action](index.md))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [DeviceWatcherInterceptor](../-device-watcher-interceptor/index.md)
| [ObjectWatcherInterceptor](../-object-watcher-interceptor/index.md)

