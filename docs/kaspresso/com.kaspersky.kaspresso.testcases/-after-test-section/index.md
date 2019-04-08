[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases](../index.md) / [AfterTestSection](./index.md)

# AfterTestSection

`class AfterTestSection`

A representation of a set of actions to invoke after the test.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AfterTestSection(runner: `[`TestCaseRunner`](../../com.kaspersky.kaspresso.testcases.runners/-test-case-runner/index.md)`)`<br>A representation of a set of actions to invoke after the test. |

### Functions

| Name | Summary |
|---|---|
| [afterTest](after-test.md) | `fun afterTest(actions: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TestCaseRunner`](../../com.kaspersky.kaspresso.testcases.runners/-test-case-runner/index.md)<br>Wraps [actions](after-test.md#com.kaspersky.kaspresso.testcases.AfterTestSection$afterTest(kotlin.Function0((kotlin.Unit)))/actions) in a lambda, that will invoke these [actions](after-test.md#com.kaspersky.kaspresso.testcases.AfterTestSection$afterTest(kotlin.Function0((kotlin.Unit)))/actions) and make screenshot if [actions](after-test.md#com.kaspersky.kaspresso.testcases.AfterTestSection$afterTest(kotlin.Function0((kotlin.Unit)))/actions) will fail when it will be invoked itself, and sets this lambda as the [TestCaseRunner.afterTestActions](#) to [runner](#). |
