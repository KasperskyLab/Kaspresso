[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases](../index.md) / [TestCase](./index.md)

# TestCase

`abstract class TestCase`

A base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](./index.md) as a
parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
exception caused by re-initialization of the [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md), use [Scenario](../-scenario/index.md) instead.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TestCase(configBuilder: `[`Configurator.Builder`](../../com.kaspersky.kaspresso.configurator/-configurator/-builder/index.md)` = Configurator.Builder.default())`<br>A base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](./index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md), use [Scenario](../-scenario/index.md) instead. |

### Functions

| Name | Summary |
|---|---|
| [beforeTest](before-test.md) | `fun beforeTest(actions: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../-after-test-section/index.md)<br>Starts the building a test, sets the [BeforeTestSection](../-before-test-section/index.md) actions and returns an existing instance of [AfterTestSection](../-after-test-section/index.md) to continue building a test. |
