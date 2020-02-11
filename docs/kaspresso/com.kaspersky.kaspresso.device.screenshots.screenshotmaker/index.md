[kaspresso](../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotmaker](./index.md)

## Package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

### Types

| Name | Summary |
|---|---|
| [CombinedScreenshotMaker](-combined-screenshot-maker/index.md) | `class CombinedScreenshotMaker : `[`ScreenshotMaker`](-screenshot-maker/index.md)<br>Calls [preferredScreenshotMaker](#) and fallbacks to [fallbackScreenshotMaker](#) on fail |
| [ExternalScreenshotMaker](-external-screenshot-maker/index.md) | `class ExternalScreenshotMaker : `[`ScreenshotMaker`](-screenshot-maker/index.md)<br>Captures spoon-compatible screenshots by uiautomator. |
| [InternalScreenshotMaker](-internal-screenshot-maker/index.md) | `class InternalScreenshotMaker : `[`ScreenshotMaker`](-screenshot-maker/index.md)<br>Captures the view of a current activity |
| [ScreenshotMaker](-screenshot-maker/index.md) | `interface ScreenshotMaker`<br>Creates and saves a screenshot |
