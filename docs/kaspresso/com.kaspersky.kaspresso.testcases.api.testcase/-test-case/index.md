[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](../index.md) / [TestCase](./index.md)

# TestCase

`abstract class TestCase : `[`BaseTestCase`](../-base-test-case/index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>`

The base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](./index.md) as a
parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use
[com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TestCase(kaspressoBuilder: `[`Kaspresso.Builder`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md)` = Kaspresso.Builder.advanced())`<br>The base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](./index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead. |

### Inherited Properties

| Name | Summary |
|---|---|
| [adbServer](../-base-test-case/adb-server.md) | `open val adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md) |
| [device](../-base-test-case/device.md) | `open val device: `[`Device`](../../com.kaspersky.kaspresso.device/-device/index.md) |
| [params](../-base-test-case/params.md) | `open val params: `[`Params`](../../com.kaspersky.kaspresso.params/-params/index.md) |
| [testLogger](../-base-test-case/test-logger.md) | `open val testLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [before](../-base-test-case/before.md) | `fun before(testName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = testCaseName, actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)`<`[`InitData`](../-base-test-case/index.md#InitData)`, `[`Data`](../-base-test-case/index.md#Data)`>`<br>Starts the building of a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-before-test-section/index.md) actions and returns an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md) to continue the building a test. |
| [init](../-base-test-case/init.md) | `fun init(testName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = testCaseName, actions: `[`InitData`](../-base-test-case/index.md#InitData)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TransformSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-transform-section/index.md)`<`[`Data`](../-base-test-case/index.md#Data)`>`<br>Starts the building of a test from data initialization section. Sets [com.kaspersky.kaspresso.testcases.core.sections.InitSection](../../com.kaspersky.kaspresso.testcases.core.sections/-init-section/index.md) actions and returns an existing instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) to continue building a tests. |
| [run](../-base-test-case/run.md) | `fun run(testName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = testCaseName, steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](../-base-test-case/index.md#Data)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) steps, creates an instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) and runs it |

### Inheritors

| Name | Summary |
|---|---|
| [DocLocScreenshotTestCase](../-doc-loc-screenshot-test-case/index.md) | `abstract class DocLocScreenshotTestCase : `[`TestCase`](./index.md)<br>The base class for all docloc screenshot tests. |
