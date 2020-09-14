//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.phone](../index.md)/[PhoneImpl](index.md)



# PhoneImpl  
 [androidJvm] 

The implementation of the [Phone](../-phone/index.md) interface.

class [PhoneImpl](index.md)(**adbServer**: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [Phone](../-phone/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [PhoneImpl](-phone-impl.md)|  [androidJvm] fun [PhoneImpl](-phone-impl.md)(adbServer: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [cancelCall](cancel-call.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Cancels incoming call.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>open override fun [cancelCall](cancel-call.md)(number: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [emulateCall](emulate-call.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Emulates incoming call.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>open override fun [emulateCall](emulate-call.md)(number: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [receiveSms](receive-sms.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Emulates receiving an SMS from number.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>open override fun [receiveSms](receive-sms.md)(number: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

