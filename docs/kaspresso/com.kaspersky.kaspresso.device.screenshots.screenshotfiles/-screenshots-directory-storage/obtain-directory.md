[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots.screenshotfiles](../index.md) / [ScreenshotsDirectoryStorage](index.md) / [obtainDirectory](./obtain-directory.md)

# obtainDirectory

`fun obtainDirectory(screenshotTestDir: File): File`

Returns directory for a particular test.
If the directory exists, it will be deleted on the first method call.
If the directory doesn't exist, it will be created.

### Parameters

`screenshotTestDir` - desired directory resolved by the root dir.

**Return**
[File](#) which represents an existing directory.

