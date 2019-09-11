[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [InitSection](./index.md)

# InitSection

`interface InitSection<InitData, Data>`

### Functions

| Name | Summary |
|---|---|
| [init](init.md) | `abstract fun init(actions: `[`InitData`](index.md#InitData)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TransformSection`](../-transform-section/index.md)`<`[`Data`](index.md#Data)`>`<br>Can be invoked after [BeforeTestSection](../-before-test-section/index.md). Running to init test data using dsl. |
| [run](run.md) | `abstract fun run(steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs: |

### Inheritors

| Name | Summary |
|---|---|
| [MainTestSection](../-main-test-section/index.md) | `class MainTestSection<InitData, Data> : `[`InitSection`](./index.md)`<`[`InitData`](../-main-test-section/index.md#InitData)`, `[`Data`](../-main-test-section/index.md#Data)`>, `[`TransformSection`](../-transform-section/index.md)`<`[`Data`](../-main-test-section/index.md#Data)`>`<br>The representation of an actual test. |
