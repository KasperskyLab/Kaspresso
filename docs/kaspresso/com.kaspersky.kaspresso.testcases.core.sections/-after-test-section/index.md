[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [AfterTestSection](./index.md)

# AfterTestSection

`class AfterTestSection<InitData, Data>`

The representation of a set of actions to invoke after the test.

### Functions

| Name | Summary |
|---|---|
| [after](after.md) | Wraps [actions](after.md#com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection$after(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext, kotlin.Unit)))/actions) in a lambda, that will invoke these [actions](after.md#com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection$after(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext, kotlin.Unit)))/actions) and make screenshot if [actions](after.md#com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection$after(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext, kotlin.Unit)))/actions) will fail when it will be invoked itself, and sets this lambda as the [TestBody.afterTestActions](#).`fun after(actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`InitSection`](../-init-section/index.md)`<InitData, Data>` |
