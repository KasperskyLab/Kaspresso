[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [BaseTestCaseRule](index.md) / [run](./run.md)

# run

`fun run(testName: String = testClassName, steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<Data>.() -> Unit): Unit`

Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) steps, creates an instance of
[MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) and runs it

### Parameters

`testName` - a name of the test.

`steps` - actions to invoke in main test section.