//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.systemsafety](../index.md)/[SystemDialogSafetyProviderImpl](index.md)



# SystemDialogSafetyProviderImpl  
 [androidJvm] 

The implementation of the [SystemDialogSafetyProvider](../-system-dialog-safety-provider/index.md) interface.

class [SystemDialogSafetyProviderImpl](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), **uiDevice**: UiDevice, **adbServer**: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [SystemDialogSafetyProvider](../-system-dialog-safety-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [SystemDialogSafetyProviderImpl](-system-dialog-safety-provider-impl.md)|  [androidJvm] fun [SystemDialogSafetyProviderImpl](-system-dialog-safety-provider-impl.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), uiDevice: UiDevice, adbServer: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md))   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| [Companion](-companion/index.md)| [androidJvm]  <br>Content  <br>object [Companion](-companion/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [passSystemDialogs](pass-system-dialogs.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action and hides the system dialog if the invocation is failed and the system dialog is actually shown via suppressSystemDialogs call.<br><br>  <br>Content  <br>open override fun <[T](pass-system-dialogs.md)> [passSystemDialogs](pass-system-dialogs.md)(action: () -> [T](pass-system-dialogs.md)): [T](pass-system-dialogs.md)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

