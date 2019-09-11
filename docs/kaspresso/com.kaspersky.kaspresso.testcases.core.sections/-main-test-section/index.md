[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [MainTestSection](./index.md)

# MainTestSection

`class MainTestSection<InitData, Data> : `[`InitSection`](../-init-section/index.md)`<`[`InitData`](index.md#InitData)`, `[`Data`](index.md#Data)`>, `[`TransformSection`](../-transform-section/index.md)`<`[`Data`](index.md#Data)`>`

The representation of an actual test.

### Functions

| Name | Summary |
|---|---|
| [init](init.md) | `fun init(actions: `[`InitData`](index.md#InitData)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TransformSection`](../-transform-section/index.md)`<`[`Data`](index.md#Data)`>`<br>Can be invoked after [BeforeTestSection](../-before-test-section/index.md). Running to init test data using dsl. |
| [run](run.md) | `fun run(steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs: |
| [transform](transform.md) | `fun transform(actions: `[`Data`](index.md#Data)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TransformSection`](../-transform-section/index.md)`<`[`Data`](index.md#Data)`>`<br>Can be invoked after [BeforeTestSection](../-before-test-section/index.md) and [init](init.md) but before [MainTestSection](./index.md). |
