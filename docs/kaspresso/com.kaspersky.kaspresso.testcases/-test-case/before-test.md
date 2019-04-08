[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases](../index.md) / [TestCase](index.md) / [beforeTest](./before-test.md)

# beforeTest

`protected fun beforeTest(actions: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../-after-test-section/index.md)

Starts the building a test, sets the [BeforeTestSection](../-before-test-section/index.md) actions and returns an existing instance of
[AfterTestSection](../-after-test-section/index.md) to continue building a test.

### Parameters

`actions` - actions to invoke in before test section.

**Return**
an existing instance of [AfterTestSection](../-after-test-section/index.md).

