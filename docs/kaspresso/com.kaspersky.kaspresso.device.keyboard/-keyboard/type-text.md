[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.keyboard](../index.md) / [Keyboard](index.md) / [typeText](./type-text.md)

# typeText

`abstract fun typeText(text: String): Unit`

Types text char by char in the focused text field.
Use it only when Espresso or UiAutomator are not appropriate (e.g. when you are on the lock screen).

Consider to use [ViewActions.typeText](#).
Also, consider to use [UiObject.setText](#).

Required Permissions: INTERNET

