[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [InitSection](./index.md)

# InitSection

`interface InitSection<InitData, Data>`

### Functions

| Name | Summary |
|---|---|
| [init](init.md) | Can be invoked after [BeforeTestSection](../-before-test-section/index.md). Running to init test data using dsl.`abstract fun init(actions: InitData.() -> Unit): `[`TransformSection`](../-transform-section/index.md)`<Data>` |
| [run](run.md) | Runs:`abstract fun run(steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<Data>.() -> Unit): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [MainTestSection](../-main-test-section/index.md) | The representation of an actual test.`class MainTestSection<InitData, Data> : `[`InitSection`](./index.md)`<InitData, Data>, `[`TransformSection`](../-transform-section/index.md)`<Data>` |
