[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases](../index.md) / [AfterTestSection](index.md) / [afterTest](./after-test.md)

# afterTest

`fun afterTest(actions: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TestCaseRunner`](../../com.kaspersky.kaspresso.testcases.runners/-test-case-runner/index.md)

Wraps [actions](after-test.md#com.kaspersky.kaspresso.testcases.AfterTestSection$afterTest(kotlin.Function0((kotlin.Unit)))/actions) in a lambda, that will invoke these [actions](after-test.md#com.kaspersky.kaspresso.testcases.AfterTestSection$afterTest(kotlin.Function0((kotlin.Unit)))/actions) and make screenshot if [actions](after-test.md#com.kaspersky.kaspresso.testcases.AfterTestSection$afterTest(kotlin.Function0((kotlin.Unit)))/actions) will fail when it
will be invoked itself, and sets this lambda as the [TestCaseRunner.afterTestActions](#) to [runner](#).

### Parameters

`actions` - actions to be wrapped and invoked after the test.

**Return**
[runner](#) to continue building a test.

