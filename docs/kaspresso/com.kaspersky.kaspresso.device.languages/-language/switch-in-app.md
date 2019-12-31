[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.languages](../index.md) / [Language](index.md) / [switchInApp](./switch-in-app.md)

# switchInApp

`abstract fun switchInApp(locale: `[`Locale`](https://developer.android.com/reference/java/util/Locale.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Switches language only in the current Application (not in OS!).
Please, keep in mind the following fact:
If you have switched languages then you need to refresh current screen to get the screen with new language!
Also, don't forget to restore the previous language if you don't clean the state of the Application after each test.

### Exceptions

`Throwable` - if something went wrong