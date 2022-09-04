//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.keyboard](../index.md)/[Keyboard](index.md)



# Keyboard  
 [androidJvm] 



Use this API only if neither Espresso, nor UiAutomator work for some reasons. E.g. because of animations.



Using this API is highly discouraged. Consider to use the built-in API whenever it's possible as it described in the documentation for methods.



Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar" Methods demanding to use AdbServer in the default implementation of this interface are marked.     But nobody can't deprecate you to write implementation that doesn't require AdbServer.



interface [Keyboard](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [sendEvent](send-event.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Sends a key event. Use constants from [KeyEvent](https://developer.android.com/reference/kotlin/android/view/KeyEvent.html) to get the code.<br><br><br><br>Consider to use ViewActions.pressKey. Also, consider to use UiDevice.pressKeyCode, or more semantic methods like UiDevice.pressMenu, UiDevice.pressDPadLeft and so on.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>abstract fun [sendEvent](send-event.md)(keyEvent: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [typeText](type-text.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Types text char by char in the focused text field. Use it only when Espresso or UiAutomator are not appropriate (e.g. when you are on the lock screen).<br><br><br><br>Consider to use ViewActions.typeText. Also, consider to use UiObject.setText.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>abstract fun [typeText](type-text.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [KeyboardImpl](../-keyboard-impl/index.md)

