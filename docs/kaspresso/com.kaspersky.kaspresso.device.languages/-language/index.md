[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.languages](../index.md) / [Language](./index.md)

# Language

`interface Language`

The interface to work with languages

### Functions

| Name | Summary |
|---|---|
| [switchInApp](switch-in-app.md) | `abstract fun switchInApp(locale: `[`Locale`](https://developer.android.com/reference/java/util/Locale.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Switches language only in the current Application (not in OS!). Please, keep in mind the following fact: If you have switched languages then you need to refresh current screen to get the screen with new language! Also, don't forget to restore the previous language if you don't clean the state of the Application after each test. |

### Inheritors

| Name | Summary |
|---|---|
| [LanguageImpl](../-language-impl/index.md) | `class LanguageImpl : `[`Language`](./index.md)<br>The implementation of [Language](./index.md) |
