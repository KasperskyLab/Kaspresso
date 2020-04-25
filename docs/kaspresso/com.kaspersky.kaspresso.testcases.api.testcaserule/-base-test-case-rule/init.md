[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [BaseTestCaseRule](index.md) / [init](./init.md)

# init

`fun init(testName: String = testClassName, actions: InitData.() -> Unit): `[`TransformSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-transform-section/index.md)`<Data>`

Starts the building of a test from data initialization section. Sets
[com.kaspersky.kaspresso.testcases.core.sections.InitSection](../../com.kaspersky.kaspresso.testcases.core.sections/-init-section/index.md) actions and returns an existing instance of
[MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) to continue building a tests.

### Parameters

`testName` - a name of the test.

`actions` - actions to be wrapped and invoked before the test for data initialization

**Return**
an existing instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md).

