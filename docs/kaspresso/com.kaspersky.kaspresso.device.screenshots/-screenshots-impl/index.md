[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots](../index.md) / [ScreenshotsImpl](./index.md)

# ScreenshotsImpl

`class ScreenshotsImpl : `[`Screenshots`](../-screenshots/index.md)

The implementation of the [Screenshots](../-screenshots/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ScreenshotsImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, activities: `[`Activities`](../../com.kaspersky.kaspresso.device.activities/-activities/index.md)`, screenshotDir: `[`File`](https://developer.android.com/reference/java/io/File.html)` = File("screenshots"))`<br>The implementation of the [Screenshots](../-screenshots/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [take](take.md) | `fun take(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Takes screenshot if it is possible, otherwise logs the error. |
