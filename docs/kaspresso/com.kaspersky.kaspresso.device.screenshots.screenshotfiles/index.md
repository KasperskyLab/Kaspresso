[kaspresso](../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotfiles](./index.md)

## Package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

### Types

| Name | Summary |
|---|---|
| [DefaultScreenshotDirectoryProvider](-default-screenshot-directory-provider/index.md) | Default implementation of [ScreenshotDirectoryProvider](-screenshot-directory-provider/index.md) If [groupByRunNumbers](#) is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test running several times per the same suite.`class DefaultScreenshotDirectoryProvider : `[`ScreenshotDirectoryProvider`](-screenshot-directory-provider/index.md) |
| [DefaultScreenshotNameProvider](-default-screenshot-name-provider/index.md) | Default implementation of [ScreenshotFileProvider](#) If [addTimestamps](#) is true it adds timestamps to names like that "1570158949869_ScreenshotSampleTest_step_1.png"`class DefaultScreenshotNameProvider : `[`ScreenshotNameProvider`](-screenshot-name-provider/index.md) |
| [ScreenshotDirectoryProvider](-screenshot-directory-provider/index.md) | Provides a directory for screenshots of a separate test`interface ScreenshotDirectoryProvider` |
| [ScreenshotNameProvider](-screenshot-name-provider/index.md) | Provides names for screenshots`interface ScreenshotNameProvider` |
| [ScreenshotsDirectoryStorage](-screenshots-directory-storage/index.md) | `class ScreenshotsDirectoryStorage` |
| [TestMethod](-test-method/index.md) | `data class TestMethod` |
