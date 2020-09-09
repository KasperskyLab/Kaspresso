[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [TransformSection](./index.md)

# TransformSection

`interface TransformSection<Data>`

### Functions

| Name | Summary |
|---|---|
| [run](run.md) | Runs:`abstract fun run(steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<Data>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [transform](transform.md) | Can be invoked after [BeforeTestSection](../-before-test-section/index.md) and [InitSection.init](../-init-section/init.md) but before [MainTestSection](../-main-test-section/index.md). It's possible to add multiple transform blocks.`abstract fun transform(actions: Data.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TransformSection`](./index.md)`<Data>` |

### Inheritors

| Name | Summary |
|---|---|
| [MainTestSection](../-main-test-section/index.md) | The representation of an actual test.`class MainTestSection<InitData, Data> : `[`InitSection`](../-init-section/index.md)`<InitData, Data>, `[`TransformSection`](./index.md)`<Data>` |
