[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots](../index.md) / [ScreenshotsImpl](index.md) / [take](./take.md)

# take

`fun take(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [Screenshots.take](../-screenshots/take.md)

Takes screenshot if it is possible, otherwise logs the error.

Required Permissions: WRITE_EXTERNAL_STORAGE.

### Parameters

`tag` - a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-](#)+.