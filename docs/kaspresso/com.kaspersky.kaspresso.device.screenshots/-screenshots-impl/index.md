[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots](../index.md) / [ScreenshotsImpl](./index.md)

# ScreenshotsImpl

`class ScreenshotsImpl : `[`Screenshots`](../-screenshots/index.md)`, `[`ScreenshotTestStartListener`](../-screenshot-test-start-listener/index.md)

The implementation of the [Screenshots](../-screenshots/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ScreenshotsImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, screenshotMaker: `[`ScreenshotMaker`](../../com.kaspersky.kaspresso.device.screenshots.screenshotmaker/-screenshot-maker/index.md)`, screenshotDirectoryProvider: `[`ScreenshotDirectoryProvider`](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md)`, screenshotNameProvider: `[`ScreenshotNameProvider`](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-name-provider/index.md)`, screenshotRootDir: `[`File`](https://developer.android.com/reference/java/io/File.html)` = File("screenshots"))`<br>The implementation of the [Screenshots](../-screenshots/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [onTestStarted](on-test-started.md) | `fun onTestStarted(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [take](take.md) | `fun take(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Takes a screenshot if it is possible, otherwise logs the error. By default a screenshot name looks like /screenshotRootDir////[tag](take.md#com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl$take(kotlin.String)/tag).png See [ScreenshotFileProvider](#), [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md) for more details |
