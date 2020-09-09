[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [BeforeTestSection](./index.md)

# BeforeTestSection

`class BeforeTestSection<InitData, Data>`

The representation of a set of actions to be invoked before the test.

### Functions

| Name | Summary |
|---|---|
| [beforeTest](before-test.md) | Wraps [actions](before-test.md#com.kaspersky.kaspresso.testcases.core.sections.BeforeTestSection$beforeTest(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext, kotlin.Unit)))/actions) in a lambda, that will invoke these [actions](before-test.md#com.kaspersky.kaspresso.testcases.core.sections.BeforeTestSection$beforeTest(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext, kotlin.Unit)))/actions) and make screenshot if [actions](before-test.md#com.kaspersky.kaspresso.testcases.core.sections.BeforeTestSection$beforeTest(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext, kotlin.Unit)))/actions) will fail when it will be invoked itself, and sets this lambda as the [TestBody.beforeTestActions](#).`fun beforeTest(actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../-after-test-section/index.md)`<InitData, Data>` |
