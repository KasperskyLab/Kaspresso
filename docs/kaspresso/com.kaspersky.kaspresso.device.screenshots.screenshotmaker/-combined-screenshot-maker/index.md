[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotmaker](../index.md) / [CombinedScreenshotMaker](./index.md)

# CombinedScreenshotMaker

`class CombinedScreenshotMaker : `[`ScreenshotMaker`](../-screenshot-maker/index.md)

Calls [preferredScreenshotMaker](#) and fallbacks to [fallbackScreenshotMaker](#) on fail

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CombinedScreenshotMaker(preferredScreenshotMaker: `[`ScreenshotMaker`](../-screenshot-maker/index.md)`, fallbackScreenshotMaker: `[`ScreenshotMaker`](../-screenshot-maker/index.md)`)`<br>Calls [preferredScreenshotMaker](#) and fallbacks to [fallbackScreenshotMaker](#) on fail |

### Functions

| Name | Summary |
|---|---|
| [takeScreenshot](take-screenshot.md) | `fun takeScreenshot(file: `[`File`](https://developer.android.com/reference/java/io/File.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
