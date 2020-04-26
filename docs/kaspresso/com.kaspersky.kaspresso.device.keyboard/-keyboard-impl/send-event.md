[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.keyboard](../index.md) / [KeyboardImpl](index.md) / [sendEvent](./send-event.md)

# sendEvent

`fun sendEvent(keyEvent: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Sends a key event.
Use constants from [KeyEvent](https://developer.android.com/reference/android/view/KeyEvent.html) to get the code.

Consider to use [ViewActions.pressKey](#).
Also, consider to use [UiDevice.pressKeyCode](#),
or more semantic methods like [UiDevice.pressMenu](#), [UiDevice.pressDPadLeft](#) and so on.

Required Permissions: INTERNET

### Parameters

`keyEvent` - the code from a [KeyEvent](https://developer.android.com/reference/android/view/KeyEvent.html) constant to send on device.