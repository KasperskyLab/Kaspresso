[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases](../index.md) / [BeforeTestSection](index.md) / [beforeTest](./before-test.md)

# beforeTest

`fun beforeTest(actions: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../-after-test-section/index.md)

Wraps [actions](before-test.md#com.kaspersky.kaspresso.testcases.BeforeTestSection$beforeTest(kotlin.Function0((kotlin.Unit)))/actions) in a lambda, that will invoke these [actions](before-test.md#com.kaspersky.kaspresso.testcases.BeforeTestSection$beforeTest(kotlin.Function0((kotlin.Unit)))/actions) and make screenshot if [actions](before-test.md#com.kaspersky.kaspresso.testcases.BeforeTestSection$beforeTest(kotlin.Function0((kotlin.Unit)))/actions) will fail when it
will be invoked itself, and sets this lambda as the [TestCaseRunner.beforeTestActions](#) to [runner](#).

### Parameters

`actions` - actions to be wrapped and invoked before the test.