[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [BaseTestCaseRule](index.md) / [before](./before.md)

# before

`fun before(testName: String = testClassName, actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> Unit): `[`AfterTestSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)`<InitData, Data>`

Starts the building a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-before-test-section/index.md) actions and returns an existing instance of
[AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md) to continue building a test.

### Parameters

`testName` - a name of the test.

`actions` - actions to invoke in before test section.

**Return**
an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md).

