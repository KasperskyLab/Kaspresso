[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [AfterTestSection](index.md) / [after](./after.md)

# after

`fun after(actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`InitSection`](../-init-section/index.md)`<`[`InitData`](index.md#InitData)`, `[`Data`](index.md#Data)`>`

Wraps [actions](after.md#com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection$after(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext, kotlin.Unit)))/actions) in a lambda, that will invoke these [actions](after.md#com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection$after(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext, kotlin.Unit)))/actions) and make screenshot if [actions](after.md#com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection$after(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext, kotlin.Unit)))/actions) will fail when it
will be invoked itself, and sets this lambda as the [TestBody.afterTestActions](#).

### Parameters

`actions` - actions to be wrapped and invoked after the test.

**Return**
[InitSection](../-init-section/index.md) to continue building a test.

