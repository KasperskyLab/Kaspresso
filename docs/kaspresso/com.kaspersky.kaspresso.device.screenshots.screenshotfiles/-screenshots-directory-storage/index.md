[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotfiles](../index.md) / [ScreenshotsDirectoryStorage](./index.md)

# ScreenshotsDirectoryStorage

`class ScreenshotsDirectoryStorage`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ScreenshotsDirectoryStorage()` |

### Functions

| Name | Summary |
|---|---|
| [getRootScreenshotDirectory](get-root-screenshot-directory.md) | `fun getRootScreenshotDirectory(screenshotDir: `[`File`](https://developer.android.com/reference/java/io/File.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)<br>Returns the directory for screenshots resolved by storage's root. If the directory doesn't exist, it will be created. |
| [obtainDirectory](obtain-directory.md) | `fun obtainDirectory(screenshotTestDir: `[`File`](https://developer.android.com/reference/java/io/File.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)<br>Returns directory for a particular test. If the directory exists, it will be deleted on the first method call. If the directory doesn't exist, it will be created. |
