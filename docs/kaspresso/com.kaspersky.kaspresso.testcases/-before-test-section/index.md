[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases](../index.md) / [BeforeTestSection](./index.md)

# BeforeTestSection

`class BeforeTestSection`

A representation of a set of actions to be invoked before the test.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BeforeTestSection(runner: `[`TestCaseRunner`](../../com.kaspersky.kaspresso.testcases.runners/-test-case-runner/index.md)`)`<br>A representation of a set of actions to be invoked before the test. |

### Functions

| Name | Summary |
|---|---|
| [beforeTest](before-test.md) | `fun beforeTest(actions: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../-after-test-section/index.md)<br>Wraps [actions](before-test.md#com.kaspersky.kaspresso.testcases.BeforeTestSection$beforeTest(kotlin.Function0((kotlin.Unit)))/actions) in a lambda, that will invoke these [actions](before-test.md#com.kaspersky.kaspresso.testcases.BeforeTestSection$beforeTest(kotlin.Function0((kotlin.Unit)))/actions) and make screenshot if [actions](before-test.md#com.kaspersky.kaspresso.testcases.BeforeTestSection$beforeTest(kotlin.Function0((kotlin.Unit)))/actions) will fail when it will be invoked itself, and sets this lambda as the [TestCaseRunner.beforeTestActions](#) to [runner](#). |
