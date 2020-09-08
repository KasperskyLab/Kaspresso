//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.files](../index.md)/[Files](index.md)



# Files  
 [androidJvm] 



The interface to work with file permissions.



Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar" Methods demanding to use AdbServer in the default implementation of this interface are marked.     But nobody can't deprecate you to write implementation that doesn't require AdbServer.



interface [Files](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [pull](pull.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Performs adb pull.<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>abstract fun [pull](pull.md)(devicePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), serverPath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [push](push.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Performs adb push.<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>abstract fun [push](push.md)(serverPath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), devicePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [remove](remove.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Removes a file by given path.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>abstract fun [remove](remove.md)(path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [FilesImpl](../-files-impl/index.md)

