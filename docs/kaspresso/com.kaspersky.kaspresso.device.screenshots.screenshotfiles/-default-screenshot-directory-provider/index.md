[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotfiles](../index.md) / [DefaultScreenshotDirectoryProvider](./index.md)

# DefaultScreenshotDirectoryProvider

`class DefaultScreenshotDirectoryProvider : `[`ScreenshotDirectoryProvider`](../-screenshot-directory-provider/index.md)

Default implementation of [ScreenshotDirectoryProvider](../-screenshot-directory-provider/index.md)
If [groupByRunNumbers](#) is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test
running several times per the same suite.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Default implementation of [ScreenshotDirectoryProvider](../-screenshot-directory-provider/index.md) If [groupByRunNumbers](#) is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test running several times per the same suite.`DefaultScreenshotDirectoryProvider(groupByRunNumbers: Boolean)` |

### Functions

| Name | Summary |
|---|---|
| [getDirectoryForTest](get-directory-for-test.md) | `fun getDirectoryForTest(testMethod: `[`TestMethod`](../-test-method/index.md)`, runNumber: Int): String` |
