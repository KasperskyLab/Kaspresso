[kaspresso](../../index.md) / [com.kaspersky.kaspresso.extensions.spoonext](../index.md) / [UiAutomatorSpoon](./index.md)

# UiAutomatorSpoon

`object UiAutomatorSpoon`

Utility class for capturing spoon-compatible screenshots by uiautomator.

### Functions

| Name | Summary |
|---|---|
| [clearScreenshotsFolder](clear-screenshots-folder.md) | `fun clearScreenshotsFolder(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Clears spoon-screenshots folder. |
| [save](save.md) | `fun save(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)<br>Alternative to [.save](#)`fun save(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, file: `[`File`](https://developer.android.com/reference/java/io/File.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)<br>Save a file from this test run. The file will be saved under the current class &amp; method. The file will be copied to, so make sure all the data you want have been written to the file before calling save. |
| [screenshot](screenshot.md) | `fun screenshot(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)<br>Takes a screenshot with the specified tag.`fun screenshot(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, testClassName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, testMethodName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)<br>Takes a screenshot with the specified tag.  This version allows the caller to manually specify the test class name and method name.  This is necessary when the screenshot is not called in the traditional manner. . |
