[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.languages](../index.md) / [LanguageImpl](./index.md)

# LanguageImpl

`class LanguageImpl : `[`Language`](../-language/index.md)

The implementation of [Language](../-language/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LanguageImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)`<br>The implementation of [Language](../-language/index.md) |

### Functions

| Name | Summary |
|---|---|
| [switchInApp](switch-in-app.md) | `fun switchInApp(locale: `[`Locale`](https://developer.android.com/reference/java/util/Locale.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Switches language only in the current Application (not in OS!). Please, keep in mind the following fact: If you have switched languages then you need to refresh current screen to get the screen with new language! Also, don't forget to restore the previous language if you don't clean the state of the Application after each test. |
