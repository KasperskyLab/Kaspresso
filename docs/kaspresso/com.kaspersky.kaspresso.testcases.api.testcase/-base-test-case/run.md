[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](../index.md) / [BaseTestCase](index.md) / [run](./run.md)

# run

`protected fun run(testName: String = testCaseName, steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<Data>.() -> Unit): Unit`

Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) steps, creates an instance of
[MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) and runs it

### Parameters

`testName` - a name of the test.

`steps` - actions to invoke in main test section.