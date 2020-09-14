//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging](../index.md)/[LoggingDeviceWatcherInterceptor](index.md)



# LoggingDeviceWatcherInterceptor  
 [androidJvm] 

The implementation of [DeviceWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor/index.md) that logs info about UiDeviceAssertion or UiDeviceAction and UiDeviceInteraction on which its activities are performing.

class [LoggingDeviceWatcherInterceptor](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [DeviceWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [LoggingDeviceWatcherInterceptor](-logging-device-watcher-interceptor.md)|  [androidJvm] fun [LoggingDeviceWatcherInterceptor](-logging-device-watcher-interceptor.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptCheck](intercept-check.md)| [androidJvm]  <br>Brief description  <br><br><br>Writes info to logger.<br><br>  <br>Content  <br>open override fun [interceptCheck](intercept-check.md)(interaction: UiDeviceInteraction, assertion: UiOperation<UiDevice>)  <br><br><br>
| [interceptPerform](intercept-perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Writes info to logger.<br><br>  <br>Content  <br>open override fun [interceptPerform](intercept-perform.md)(interaction: UiDeviceInteraction, action: UiOperation<UiDevice>)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

