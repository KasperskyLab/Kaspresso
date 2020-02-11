[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotfiles](../index.md) / [DefaultScreenshotDirectoryProvider](./index.md)

# DefaultScreenshotDirectoryProvider

`class DefaultScreenshotDirectoryProvider : `[`ScreenshotDirectoryProvider`](../-screenshot-directory-provider/index.md)

Default implementation of [ScreenshotDirectoryProvider](../-screenshot-directory-provider/index.md)
If [groupByRunNumbers](#) is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test
running several times per the same suite.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DefaultScreenshotDirectoryProvider(groupByRunNumbers: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`<br>Default implementation of [ScreenshotDirectoryProvider](../-screenshot-directory-provider/index.md) If [groupByRunNumbers](#) is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test running several times per the same suite. |

### Functions

| Name | Summary |
|---|---|
| [getDirectoryForTest](get-directory-for-test.md) | `fun getDirectoryForTest(testMethod: `[`TestMethod`](../-test-method/index.md)`, runNumber: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
