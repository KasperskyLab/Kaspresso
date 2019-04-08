[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases](../index.md) / [SubCase](./index.md)

# SubCase

`abstract class SubCase`

A base class for all subcases. A representation of some repeating scenario inside the [TestCase](../-test-case/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SubCase()`<br>A base class for all subcases. A representation of some repeating scenario inside the [TestCase](../-test-case/index.md). |

### Properties

| Name | Summary |
|---|---|
| [steps](steps.md) | `abstract val steps: `[`Scenario`](../-scenario/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Steps to run. Need to be implemented in derived [SubCase](./index.md). |

### Functions

| Name | Summary |
|---|---|
| [run](run.md) | `fun run(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Runs [steps](steps.md) on [SubCaseRunner](../../com.kaspersky.kaspresso.testcases.runners/-sub-case-runner/index.md). Called from outside to execute prepared derived [SubCase](./index.md). |
