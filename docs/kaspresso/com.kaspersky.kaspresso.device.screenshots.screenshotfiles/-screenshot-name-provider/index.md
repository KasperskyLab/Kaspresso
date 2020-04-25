[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotfiles](../index.md) / [ScreenshotNameProvider](./index.md)

# ScreenshotNameProvider

`interface ScreenshotNameProvider`

Provides names for screenshots

### Functions

| Name | Summary |
|---|---|
| [getScreenshotName](get-screenshot-name.md) | `abstract fun getScreenshotName(tag: String): String` |

### Inheritors

| Name | Summary |
|---|---|
| [DefaultScreenshotNameProvider](../-default-screenshot-name-provider/index.md) | Default implementation of [ScreenshotFileProvider](#) If [addTimestamps](#) is true it adds timestamps to names like that "1570158949869_ScreenshotSampleTest_step_1.png"`class DefaultScreenshotNameProvider : `[`ScreenshotNameProvider`](./index.md) |
