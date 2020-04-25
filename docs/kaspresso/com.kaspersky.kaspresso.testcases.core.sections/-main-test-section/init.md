[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [MainTestSection](index.md) / [init](./init.md)

# init

`fun init(actions: InitData.() -> Unit): `[`TransformSection`](../-transform-section/index.md)`<Data>`

Can be invoked after [BeforeTestSection](../-before-test-section/index.md). Running to init test data using dsl.

### Parameters

`actions` - actions to be wrapped and invoked before the test.

**Return**
[TransformSection](../-transform-section/index.md) to continue building a test.

