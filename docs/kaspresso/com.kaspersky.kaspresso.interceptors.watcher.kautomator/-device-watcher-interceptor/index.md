//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.kautomator](../index.md)/[DeviceWatcherInterceptor](index.md)



# DeviceWatcherInterceptor  
 [androidJvm] 

The derived from [KautomatorWatcherInterceptor](../-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) UiDeviceInteraction.perform and UiDeviceInteraction.check behavior.

interface [DeviceWatcherInterceptor](index.md) : [KautomatorWatcherInterceptor](../-kautomator-watcher-interceptor/index.md)<UiDeviceInteraction, UiOperation<UiDevice>, UiOperation<UiDevice>>    


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| interceptCheck| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before UiInteraction.check is actually called.<br><br>  <br>Content  <br>abstract override fun interceptCheck(interaction: UiDeviceInteraction, assertion: UiOperation<UiDevice>)  <br><br><br>
| interceptPerform| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff before UiInteraction.perform is actually called.<br><br>  <br>Content  <br>abstract override fun interceptPerform(interaction: UiDeviceInteraction, action: UiOperation<UiDevice>)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [LoggingDeviceWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging/-logging-device-watcher-interceptor/index.md)

