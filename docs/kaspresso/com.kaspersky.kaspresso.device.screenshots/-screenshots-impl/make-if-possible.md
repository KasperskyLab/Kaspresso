[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.screenshots](../index.md) / [ScreenshotsImpl](index.md) / [makeIfPossible](./make-if-possible.md)

# makeIfPossible

`fun makeIfPossible(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [Screenshots.makeIfPossible](../-screenshots/make-if-possible.md)

Makes screenshot if it is possible, otherwise logs the error.

### Parameters

`tag` - a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-](#)+.