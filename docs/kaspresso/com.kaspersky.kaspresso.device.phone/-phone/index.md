//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.phone](../index.md)/[Phone](index.md)



# Phone  
 [androidJvm] 



The interface to work with telephony.



Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar" Methods demanding to use AdbServer in the default implementation of this interface are marked.     But nobody can't deprecate you to write implementation that doesn't require AdbServer.



interface [Phone](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [cancelCall](cancel-call.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Cancels incoming call.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>abstract fun [cancelCall](cancel-call.md)(number: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [emulateCall](emulate-call.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Emulates incoming call.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>abstract fun [emulateCall](emulate-call.md)(number: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [receiveSms](receive-sms.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Emulates receiving an SMS from number.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>abstract fun [receiveSms](receive-sms.md)(number: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [PhoneImpl](../-phone-impl/index.md)

