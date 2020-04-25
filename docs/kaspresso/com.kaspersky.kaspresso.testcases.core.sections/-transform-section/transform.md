[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [TransformSection](index.md) / [transform](./transform.md)

# transform

`abstract fun transform(actions: Data.() -> Unit): `[`TransformSection`](index.md)`<Data>`

Can be invoked after [BeforeTestSection](../-before-test-section/index.md) and [InitSection.init](../-init-section/init.md) but before [MainTestSection](../-main-test-section/index.md).
It's possible to add multiple transform blocks.

### Parameters

`actions` - actions to run.

**Return**
[TransformSection](index.md) to continue building a test.

