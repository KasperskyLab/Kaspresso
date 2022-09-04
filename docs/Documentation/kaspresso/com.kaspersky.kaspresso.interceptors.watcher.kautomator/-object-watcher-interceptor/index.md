//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.kautomator](../index.md)/[ObjectWatcherInterceptor](index.md)



# ObjectWatcherInterceptor  
 [androidJvm] 

The derived from [KautomatorWatcherInterceptor](../-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) UiObjectInteraction.perform and UiObjectInteraction.check behavior.

interface [ObjectWatcherInterceptor](index.md) : [KautomatorWatcherInterceptor](../-kautomator-watcher-interceptor/index.md)<UiObjectInteraction, UiOperation<UiObject2>, UiOperation<UiObject2>>    


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| interceptCheck| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before UiInteraction.check is actually called.<br><br>  <br>Content  <br>abstract override fun interceptCheck(interaction: UiObjectInteraction, assertion: UiOperation<UiObject2>)  <br><br><br>
| interceptPerform| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before UiInteraction.perform is actually called.<br><br>  <br>Content  <br>abstract override fun interceptPerform(interaction: UiObjectInteraction, action: UiOperation<UiObject2>)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [LoggingObjectWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging/-logging-object-watcher-interceptor/index.md)

