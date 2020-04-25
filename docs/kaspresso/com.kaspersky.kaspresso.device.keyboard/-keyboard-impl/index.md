[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.keyboard](../index.md) / [KeyboardImpl](./index.md)

# KeyboardImpl

`class KeyboardImpl : `[`Keyboard`](../-keyboard/index.md)

The implementation of the [Keyboard](../-keyboard/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [Keyboard](../-keyboard/index.md) interface.`KeyboardImpl(adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [sendEvent](send-event.md) | Sends a key event. Use constants from [KeyEvent](#) to get the code.`fun sendEvent(keyEvent: Int): Unit` |
| [typeText](type-text.md) | Types text char by char in the focused text field. Use it only when Espresso or UiAutomator are not appropriate (e.g. when you are on the lock screen).`fun typeText(text: String): Unit` |
