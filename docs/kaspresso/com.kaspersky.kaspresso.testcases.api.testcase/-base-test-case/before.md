[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](../index.md) / [BaseTestCase](index.md) / [before](./before.md)

# before

`protected fun before(testName: String = testCaseName, actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> Unit): `[`AfterTestSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)`<InitData, Data>`

Starts the building of a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-before-test-section/index.md) actions and returns an existing instance of
[AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md) to continue the building a test.

### Parameters

`testName` - a name of the test.

`actions` - actions to invoke in before test section.

**Return**
an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md).

