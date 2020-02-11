[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotfiles](../index.md) / [ScreenshotDirectoryProvider](./index.md)

# ScreenshotDirectoryProvider

`interface ScreenshotDirectoryProvider`

Provides a directory for screenshots of a separate test

### Functions

| Name | Summary |
|---|---|
| [getDirectoryForTest](get-directory-for-test.md) | `abstract fun getDirectoryForTest(testMethod: `[`TestMethod`](../-test-method/index.md)`, runNumber: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DefaultScreenshotDirectoryProvider](../-default-screenshot-directory-provider/index.md) | `class DefaultScreenshotDirectoryProvider : `[`ScreenshotDirectoryProvider`](./index.md)<br>Default implementation of [ScreenshotDirectoryProvider](./index.md) If [groupByRunNumbers](#) is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test running several times per the same suite. |
