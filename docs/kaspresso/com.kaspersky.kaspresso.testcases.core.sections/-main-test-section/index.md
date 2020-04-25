[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [MainTestSection](./index.md)

# MainTestSection

`class MainTestSection<InitData, Data> : `[`InitSection`](../-init-section/index.md)`<InitData, Data>, `[`TransformSection`](../-transform-section/index.md)`<Data>`

The representation of an actual test.

### Functions

| Name | Summary |
|---|---|
| [init](init.md) | Can be invoked after [BeforeTestSection](../-before-test-section/index.md). Running to init test data using dsl.`fun init(actions: InitData.() -> Unit): `[`TransformSection`](../-transform-section/index.md)`<Data>` |
| [run](run.md) | Runs:`fun run(steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<Data>.() -> Unit): Unit` |
| [transform](transform.md) | Can be invoked after [BeforeTestSection](../-before-test-section/index.md) and [init](init.md) but before [MainTestSection](./index.md).`fun transform(actions: Data.() -> Unit): `[`TransformSection`](../-transform-section/index.md)`<Data>` |
