[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotmaker](../index.md) / [ScreenshotMaker](./index.md)

# ScreenshotMaker

`interface ScreenshotMaker`

Creates and saves a screenshot

### Functions

| Name | Summary |
|---|---|
| [takeScreenshot](take-screenshot.md) | `abstract fun takeScreenshot(file: `[`File`](https://developer.android.com/reference/java/io/File.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [CombinedScreenshotMaker](../-combined-screenshot-maker/index.md) | `class CombinedScreenshotMaker : `[`ScreenshotMaker`](./index.md)<br>Calls [preferredScreenshotMaker](#) and fallbacks to [fallbackScreenshotMaker](#) on fail |
| [ExternalScreenshotMaker](../-external-screenshot-maker/index.md) | `class ExternalScreenshotMaker : `[`ScreenshotMaker`](./index.md)<br>Captures spoon-compatible screenshots by uiautomator. |
| [InternalScreenshotMaker](../-internal-screenshot-maker/index.md) | `class InternalScreenshotMaker : `[`ScreenshotMaker`](./index.md)<br>Captures the view of a current activity |
