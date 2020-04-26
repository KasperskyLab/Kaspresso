[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [MainTestSection](index.md) / [transform](./transform.md)

# transform

`fun transform(actions: Data.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TransformSection`](../-transform-section/index.md)`<Data>`

Can be invoked after [BeforeTestSection](../-before-test-section/index.md) and [init](init.md) but before [MainTestSection](index.md).

It's possible to add multiple transform blocks.

### Parameters

`actions` - actions to run.

**Return**
[TransformSection](../-transform-section/index.md) to continue building a test.

