[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.keyboard](../index.md) / [KeyboardImpl](./index.md)

# KeyboardImpl

`class KeyboardImpl : `[`Keyboard`](../-keyboard/index.md)

The implementation of the [Keyboard](../-keyboard/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `KeyboardImpl(adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)`<br>The implementation of the [Keyboard](../-keyboard/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [sendEvent](send-event.md) | `fun sendEvent(keyEvent: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends a key event. Use constants from [KeyEvent](https://developer.android.com/reference/android/view/KeyEvent.html) to get the code. |
| [typeText](type-text.md) | `fun typeText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Types text char by char in the focused text field. Use it only when Espresso or UiAutomator are not appropriate (e.g. when you are on the lock screen). |
