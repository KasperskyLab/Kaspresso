//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety](../index.md)/[SystemDialogSafetyObjectBehaviorInterceptor](index.md)



# SystemDialogSafetyObjectBehaviorInterceptor  
 [androidJvm] 

The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor/index.md) and [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for UiObjectInteraction.perform and UiObjectInteraction.check calls.

class [SystemDialogSafetyObjectBehaviorInterceptor](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), **uiDevice**: UiDevice, **adbServer**: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor/index.md), [SystemDialogSafetyProvider](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [SystemDialogSafetyObjectBehaviorInterceptor](-system-dialog-safety-object-behavior-interceptor.md)|  [androidJvm] fun [SystemDialogSafetyObjectBehaviorInterceptor](-system-dialog-safety-object-behavior-interceptor.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), uiDevice: UiDevice, adbServer: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptCheck](intercept-check.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given activity invocation with the system dialog safety.<br><br>  <br>Content  <br>open override fun <[T](intercept-check.md)> [interceptCheck](intercept-check.md)(interaction: UiObjectInteraction, assertion: UiOperation<UiObject2>, activity: () -> [T](intercept-check.md)): [T](intercept-check.md)  <br><br><br>
| [interceptPerform](intercept-perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given activity invocation with the system dialog safety.<br><br>  <br>Content  <br>open override fun <[T](intercept-perform.md)> [interceptPerform](intercept-perform.md)(interaction: UiObjectInteraction, action: UiOperation<UiObject2>, activity: () -> [T](intercept-perform.md)): [T](intercept-perform.md)  <br><br><br>
| [passSystemDialogs](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/pass-system-dialogs.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action and hides the system dialog if the invocation is failed and the system dialog is actually shown via suppressSystemDialogs call.<br><br>  <br>Content  <br>open override fun <T> [passSystemDialogs](../../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/pass-system-dialogs.md)(action: () -> T): T  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

