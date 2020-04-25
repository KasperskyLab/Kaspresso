[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots](../index.md) / [ScreenshotsImpl](./index.md)

# ScreenshotsImpl

`class ScreenshotsImpl : `[`Screenshots`](../-screenshots/index.md)`, `[`ScreenshotTestStartListener`](../-screenshot-test-start-listener/index.md)

The implementation of the [Screenshots](../-screenshots/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [Screenshots](../-screenshots/index.md) interface.`ScreenshotsImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, screenshotMaker: `[`ScreenshotMaker`](../../com.kaspersky.kaspresso.device.screenshots.screenshotmaker/-screenshot-maker/index.md)`, screenshotDirectoryProvider: `[`ScreenshotDirectoryProvider`](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md)`, screenshotNameProvider: `[`ScreenshotNameProvider`](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-name-provider/index.md)`, screenshotRootDir: File = File("screenshots"))` |

### Functions

| Name | Summary |
|---|---|
| [onTestStarted](on-test-started.md) | `fun onTestStarted(): Unit` |
| [take](take.md) | Takes a screenshot if it is possible, otherwise logs the error. By default a screenshot name looks like /screenshotRootDir////[tag](take.md#com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl$take(kotlin.String)/tag).png See [ScreenshotFileProvider](#), [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md) for more details`fun take(tag: String): Unit` |
