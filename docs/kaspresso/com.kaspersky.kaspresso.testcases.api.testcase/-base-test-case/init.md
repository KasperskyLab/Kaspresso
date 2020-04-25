[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](../index.md) / [BaseTestCase](index.md) / [init](./init.md)

# init

`protected fun init(testName: String = testCaseName, actions: InitData.() -> Unit): `[`TransformSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-transform-section/index.md)`<Data>`

Starts the building of a test from data initialization section. Sets
[com.kaspersky.kaspresso.testcases.core.sections.InitSection](../../com.kaspersky.kaspresso.testcases.core.sections/-init-section/index.md) actions and returns an existing instance of
[MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) to continue building a tests.

### Parameters

`testName` - a name of the test.

`actions` - actions to be wrapped and invoked before the test for data initialization

**Return**
an existing instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md).

