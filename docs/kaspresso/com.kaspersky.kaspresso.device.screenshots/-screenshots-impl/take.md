[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots](../index.md) / [ScreenshotsImpl](index.md) / [take](./take.md)

# take

`fun take(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [Screenshots.take](../-screenshots/take.md)

Takes a screenshot if it is possible, otherwise logs the error.
By default a screenshot name looks like /screenshotRootDir////[tag](take.md#com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl$take(kotlin.String)/tag).png
See [ScreenshotFileProvider](#), [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md) for more details

Required Permissions: WRITE_EXTERNAL_STORAGE.

### Parameters

`tag` - a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-](#)+.