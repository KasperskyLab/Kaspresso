[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotmaker](../index.md) / [ScreenshotMaker](./index.md)

# ScreenshotMaker

`interface ScreenshotMaker`

Creates and saves a screenshot

### Functions

| Name | Summary |
|---|---|
| [takeScreenshot](take-screenshot.md) | `abstract fun takeScreenshot(file: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [CombinedScreenshotMaker](../-combined-screenshot-maker/index.md) | Calls [preferredScreenshotMaker](#) and fallbacks to [fallbackScreenshotMaker](#) on fail`class CombinedScreenshotMaker : `[`ScreenshotMaker`](./index.md) |
| [ExternalScreenshotMaker](../-external-screenshot-maker/index.md) | Captures spoon-compatible screenshots by uiautomator.`class ExternalScreenshotMaker : `[`ScreenshotMaker`](./index.md) |
| [InternalScreenshotMaker](../-internal-screenshot-maker/index.md) | Captures the view of a current activity`class InternalScreenshotMaker : `[`ScreenshotMaker`](./index.md) |
