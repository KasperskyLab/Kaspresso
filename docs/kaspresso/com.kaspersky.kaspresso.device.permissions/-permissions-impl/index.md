//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.permissions](../index.md)/[PermissionsImpl](index.md)



# PermissionsImpl  
 [androidJvm] 

The implementation of the [Permissions](../-permissions/index.md) interface.

class [PermissionsImpl](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), **uiDevice**: UiDevice) : [Permissions](../-permissions/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [PermissionsImpl](-permissions-impl.md)|  [androidJvm] fun [PermissionsImpl](-permissions-impl.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), uiDevice: UiDevice)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [allowViaDialog](allow-via-dialog.md)| [androidJvm]  <br>Brief description  <br><br><br>Waits for 1 sec, passes the permission-requesting permissions dialog and allows permissions.<br><br>  <br>Content  <br>open override fun [allowViaDialog](allow-via-dialog.md)()  <br><br><br>
| [clickOn](click-on.md)| [androidJvm]  <br>Brief description  <br><br><br>Waits for 1 sec, passes the permission-requesting permissions dialog<br><br>  <br>Content  <br>open override fun [clickOn](click-on.md)(button: [Permissions.Button](../-permissions/-button/index.md))  <br><br><br>
| [denyViaDialog](deny-via-dialog.md)| [androidJvm]  <br>Brief description  <br><br><br>Waits for 1 sec, passes the permission-requesting permissions dialog and denies permissions.<br><br>  <br>Content  <br>open override fun [denyViaDialog](deny-via-dialog.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isDialogVisible](is-dialog-visible.md)| [androidJvm]  <br>Brief description  <br><br><br>Waits for 1 sec, check the permission-requesting permissions dialog is visible.<br><br>  <br>Content  <br>open override fun [isDialogVisible](is-dialog-visible.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

