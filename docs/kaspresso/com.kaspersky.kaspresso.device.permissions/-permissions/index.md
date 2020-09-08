//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.permissions](../index.md)/[Permissions](index.md)



# Permissions  
 [androidJvm] 

The interface to work with permissions fairly by real permission dialogs.

interface [Permissions](index.md)   


## Types  
  
|  Name|  Summary| 
|---|---|
| [Button](-button/index.md)| [androidJvm]  <br>Content  <br>enum [Button](-button/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)<[Permissions.Button](-button/index.md)>   <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [allowViaDialog](allow-via-dialog.md)| [androidJvm]  <br>Brief description  <br><br><br>Passes the permission-requesting permissions dialog and allows permissions.<br><br>  <br>Content  <br>abstract fun [allowViaDialog](allow-via-dialog.md)()  <br><br><br>
| [clickOn](click-on.md)| [androidJvm]  <br>Brief description  <br><br><br>Passes the permission-requesting permissions dialog<br><br>  <br>Content  <br>abstract fun [clickOn](click-on.md)(button: [Permissions.Button](-button/index.md))  <br><br><br>
| [denyViaDialog](deny-via-dialog.md)| [androidJvm]  <br>Brief description  <br><br><br>Passes the permission-requesting permissions dialog and denies permissions.<br><br>  <br>Content  <br>abstract fun [denyViaDialog](deny-via-dialog.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isDialogVisible](is-dialog-visible.md)| [androidJvm]  <br>Brief description  <br><br><br>Check the permission-requesting permissions dialog is visible.<br><br>  <br>Content  <br>abstract fun [isDialogVisible](is-dialog-visible.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [PermissionsImpl](../-permissions-impl/index.md)

