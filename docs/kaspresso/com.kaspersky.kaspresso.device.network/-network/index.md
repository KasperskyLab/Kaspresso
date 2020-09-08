//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.network](../index.md)/[Network](index.md)



# Network  
 [androidJvm] 



The interface to work with network settings.



Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar" Methods demanding to use AdbServer in the default implementation of this interface are marked.     But nobody can't deprecate you to write implementation that doesn't require AdbServer.



interface [Network](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [disable](disable.md)| [androidJvm]  <br>Brief description  <br><br><br>Disables wi-fi and mobile data using adb.<br><br>  <br>Content  <br>abstract fun [disable](disable.md)()  <br><br><br>
| [enable](enable.md)| [androidJvm]  <br>Brief description  <br><br><br>Enables wi-fi and mobile data using adb.<br><br>  <br>Content  <br>abstract fun [enable](enable.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toggleMobileData](toggle-mobile-data.md)| [androidJvm]  <br>Brief description  <br><br><br>Toggles only mobile data. Note: it works only if flight mode is off.<br><br>  <br>Content  <br>abstract fun [toggleMobileData](toggle-mobile-data.md)(enable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [toggleWiFi](toggle-wi-fi.md)| [androidJvm]  <br>Brief description  <br><br><br>Toggles only wi-fi. Note: it works only if flight mode is off.<br><br>  <br>Content  <br>abstract fun [toggleWiFi](toggle-wi-fi.md)(enable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [NetworkImpl](../-network-impl/index.md)

