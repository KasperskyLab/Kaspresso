[kaspresso](../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotfiles](./index.md)

## Package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

### Types

| Name | Summary |
|---|---|
| [DefaultScreenshotDirectoryProvider](-default-screenshot-directory-provider/index.md) | `class DefaultScreenshotDirectoryProvider : `[`ScreenshotDirectoryProvider`](-screenshot-directory-provider/index.md)<br>Default implementation of [ScreenshotDirectoryProvider](-screenshot-directory-provider/index.md) If [groupByRunNumbers](#) is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test running several times per the same suite. |
| [DefaultScreenshotNameProvider](-default-screenshot-name-provider/index.md) | `class DefaultScreenshotNameProvider : `[`ScreenshotNameProvider`](-screenshot-name-provider/index.md)<br>Default implementation of [ScreenshotFileProvider](#) If [addTimestamps](#) is true it adds timestamps to names like that "1570158949869_ScreenshotSampleTest_step_1.png" |
| [ScreenshotDirectoryProvider](-screenshot-directory-provider/index.md) | `interface ScreenshotDirectoryProvider`<br>Provides a directory for screenshots of a separate test |
| [ScreenshotNameProvider](-screenshot-name-provider/index.md) | `interface ScreenshotNameProvider`<br>Provides names for screenshots |
| [ScreenshotsDirectoryStorage](-screenshots-directory-storage/index.md) | `class ScreenshotsDirectoryStorage` |
| [TestMethod](-test-method/index.md) | `data class TestMethod` |
