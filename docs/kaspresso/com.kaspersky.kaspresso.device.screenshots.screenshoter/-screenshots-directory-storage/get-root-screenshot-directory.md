[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshoter](../index.md) / [ScreenshotsDirectoryStorage](index.md) / [getRootScreenshotDirectory](./get-root-screenshot-directory.md)

# getRootScreenshotDirectory

`fun getRootScreenshotDirectory(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, screenshotDir: `[`File`](https://developer.android.com/reference/java/io/File.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)

Returns the directory for screenshots resolved by storage's root.
If the directory doesn't exist, it will be created.

### Parameters

`context` - a context to get directory on pre-lollipop.

`screenshotDir` - desired root directory.

**Return**
[File](https://developer.android.com/reference/java/io/File.html) which represents an existing directory.

