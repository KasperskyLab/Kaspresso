[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.keyboard](../index.md) / [KeyboardImpl](index.md) / [sendEvent](./send-event.md)

# sendEvent

`fun sendEvent(keyEvent: Int): Unit`

Sends a key event.
Use constants from [KeyEvent](#) to get the code.

Consider to use [ViewActions.pressKey](#).
Also, consider to use [UiDevice.pressKeyCode](#),
or more semantic methods like [UiDevice.pressMenu](#), [UiDevice.pressDPadLeft](#) and so on.

Required Permissions: INTERNET

### Parameters

`keyEvent` - the code from a [KeyEvent](#) constant to send on device.