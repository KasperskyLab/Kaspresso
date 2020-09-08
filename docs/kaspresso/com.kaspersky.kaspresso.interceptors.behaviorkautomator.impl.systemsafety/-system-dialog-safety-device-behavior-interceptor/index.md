//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety](../index.md)/[SystemDialogSafetyDeviceBehaviorInterceptor](index.md)



# SystemDialogSafetyDeviceBehaviorInterceptor  
 [androidJvm] 

The implementation of [DeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor/index.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for UiDeviceInteraction.perform and UiDeviceInteraction.check calls.

class [SystemDialogSafetyDeviceBehaviorInterceptor](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), **uiDevice**: UiDevice, **adbServer**: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [DeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor/index.md), [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [SystemDialogSafetyDeviceBehaviorInterceptor](-system-dialog-safety-device-behavior-interceptor.md)|  [androidJvm] fun [SystemDialogSafetyDeviceBehaviorInterceptor](-system-dialog-safety-device-behavior-interceptor.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), uiDevice: UiDevice, adbServer: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptCheck](intercept-check.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given activity invocation with the system dialog safety.<br><br>  <br>Content  <br>open override fun <[T](intercept-check.md)> [interceptCheck](intercept-check.md)(interaction: UiDeviceInteraction, assertion: UiOperation<UiDevice>, activity: () -> [T](intercept-check.md)): [T](intercept-check.md)  <br><br><br>
| [interceptPerform](intercept-perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given activity invocation with the system dialog safety.<br><br>  <br>Content  <br>open override fun <[T](intercept-perform.md)> [interceptPerform](intercept-perform.md)(interaction: UiDeviceInteraction, action: UiOperation<UiDevice>, activity: () -> [T](intercept-perform.md)): [T](intercept-perform.md)  <br><br><br>
| [passSystemDialogs](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/pass-system-dialogs.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action and hides the system dialog if the invocation is failed and the system dialog is actually shown via suppressSystemDialogs call.<br><br>  <br>Content  <br>open override fun <T> [passSystemDialogs](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/pass-system-dialogs.md)(action: () -> T): T  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

