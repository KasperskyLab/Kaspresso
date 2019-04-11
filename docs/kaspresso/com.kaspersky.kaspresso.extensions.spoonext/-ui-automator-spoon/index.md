[kaspresso](../../index.md) / [com.kaspersky.kaspresso.extensions.spoonext](../index.md) / [UiAutomatorSpoon](./index.md)

# UiAutomatorSpoon

`class UiAutomatorSpoon`

Class for capturing spoon-compatible screenshots by uiautomator.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiAutomatorSpoon(screenshotDir: `[`File`](https://developer.android.com/reference/java/io/File.html)`)`<br>Class for capturing spoon-compatible screenshots by uiautomator. |

### Functions

| Name | Summary |
|---|---|
| [clearScreenshotsFolder](clear-screenshots-folder.md) | `fun clearScreenshotsFolder(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Clears spoon-screenshots folder. |
| [screenshot](screenshot.md) | `fun screenshot(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)<br>Takes a screenshot with the specified tag.`fun screenshot(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, testClassName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, testMethodName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)<br>Takes a screenshot with the specified tag.  This version allows the caller to manually specify the test class name and method name.  This is necessary when the screenshot is not called in the traditional manner. .`fun screenshot(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, screenshotDirectory: `[`File`](https://developer.android.com/reference/java/io/File.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)<br>Takes a screenshot with the specified name. This version allows the caller to manually specify the output directory.  This is necessary when the screenshot is not called in the traditional manner. . |
