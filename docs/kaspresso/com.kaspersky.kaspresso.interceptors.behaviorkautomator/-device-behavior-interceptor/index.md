//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator](../index.md)/[DeviceBehaviorInterceptor](index.md)



# DeviceBehaviorInterceptor  
 [androidJvm] 

The derived from [KautomatorBehaviorInterceptor](../-kautomator-behavior-interceptor/index.md) interface for intercepting UiDeviceInteraction.perform and UiDeviceInteraction.check behavior.

interface [DeviceBehaviorInterceptor](index.md) : [KautomatorBehaviorInterceptor](../-kautomator-behavior-interceptor/index.md)<UiDeviceInteraction, UiOperation<UiDevice>, UiOperation<UiDevice>>    


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| interceptCheck| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff and actually check an interaction with element.<br><br>  <br>Content  <br>abstract override fun <T> interceptCheck(interaction: UiDeviceInteraction, assertion: UiOperation<UiDevice>, activity: () -> T): T  <br><br><br>
| interceptPerform| [androidJvm]  <br>Brief description  <br><br><br>Called to do some stuff and actually perform an interaction with element.<br><br>  <br>Content  <br>abstract override fun <T> interceptPerform(interaction: UiDeviceInteraction, action: UiOperation<UiDevice>, activity: () -> T): T  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [FailureLoggingDeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure/-failure-logging-device-behavior-interceptor/index.md)
| [FlakySafeDeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety/-flaky-safe-device-behavior-interceptor/index.md)
| [SystemDialogSafetyDeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety/-system-dialog-safety-device-behavior-interceptor/index.md)

