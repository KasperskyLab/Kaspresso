[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.keyboard](../index.md) / [Keyboard](./index.md)

# Keyboard

`interface Keyboard`

Use this API only if neither Espresso, nor UiAutomator
work for some reasons. E.g. because of animations.

Using this API is highly discouraged. Consider to use the built-in API
whenever it's possible as it described in the documentation for methods.

Required: Started AdbServer
    1. Download a file "kaspresso/artifacts/desktop.jar"
    2. Start AdbServer =&gt; input in cmd "java jar path_to_file/desktop.jar"
Methods demanding to use AdbServer in the default implementation of this interface are marked.
    But nobody can't deprecate you to write implementation that doesn't require AdbServer.

### Functions

| Name | Summary |
|---|---|
| [sendEvent](send-event.md) | `abstract fun sendEvent(keyEvent: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends a key event. Use constants from [KeyEvent](https://developer.android.com/reference/android/view/KeyEvent.html) to get the code. |
| [typeText](type-text.md) | `abstract fun typeText(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Types text char by char in the focused text field. Use it only when Espresso or UiAutomator are not appropriate (e.g. when you are on the lock screen). |

### Inheritors

| Name | Summary |
|---|---|
| [KeyboardImpl](../-keyboard-impl/index.md) | `class KeyboardImpl : `[`Keyboard`](./index.md)<br>The implementation of the [Keyboard](./index.md) interface. |
