[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [BaseTestCaseRule](index.md) / [before](./before.md)

# before

`fun before(testName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = testClassName, actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)`<`[`InitData`](index.md#InitData)`, `[`Data`](index.md#Data)`>`

Starts the building a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-before-test-section/index.md) actions and returns an existing instance of
[AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md) to continue building a test.

### Parameters

`testName` - a name of the test.

`actions` - actions to invoke in before test section.

**Return**
an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md).

