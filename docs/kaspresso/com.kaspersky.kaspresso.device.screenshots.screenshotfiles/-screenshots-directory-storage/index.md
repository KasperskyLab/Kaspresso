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
| [getRootScreenshotDirectory](get-root-screenshot-directory.md) | Returns the directory for screenshots resolved by storage's root. If the directory doesn't exist, it will be created.`fun getRootScreenshotDirectory(screenshotDir: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html) |
| [obtainDirectory](obtain-directory.md) | Returns directory for a particular test. If the directory exists, it will be deleted on the first method call. If the directory doesn't exist, it will be created.`fun obtainDirectory(screenshotTestDir: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html) |
