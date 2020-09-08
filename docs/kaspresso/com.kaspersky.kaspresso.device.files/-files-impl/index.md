//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.files](../index.md)/[FilesImpl](index.md)



# FilesImpl  
 [androidJvm] 

The implementation of the [Files](../-files/index.md) interface.

class [FilesImpl](index.md)(**adbServer**: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [Files](../-files/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [FilesImpl](-files-impl.md)|  [androidJvm] fun [FilesImpl](-files-impl.md)(adbServer: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [pull](pull.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Performs adb pull.<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>open override fun [pull](pull.md)(devicePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), serverPath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [push](push.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Performs adb push.<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>open override fun [push](push.md)(serverPath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), devicePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [remove](remove.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Removes a file by given path.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>open override fun [remove](remove.md)(path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

