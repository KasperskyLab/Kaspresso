[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](../index.md) / [BaseTestCase](./index.md)

# BaseTestCase

`abstract class BaseTestCase<InitData, Data> : `[`TestAssistantsProvider`](../../com.kaspersky.kaspresso.testcases.core.testassistants/-test-assistants-provider/index.md)

The base class for all parametrized test cases. Extend this class with a single base project-wide inheritor of
[TestCase](../-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not recommended, use
[com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.

### Parameters

`InitData` - data initialized in before section.

`Data` - data transformed from [InitData](index.md#InitData) by special function.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseTestCase(kaspressoBuilder: `[`Kaspresso.Builder`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md)` = Kaspresso.Builder.advanced(), dataProducer: ((`[`InitData`](index.md#InitData)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)?) -> `[`Data`](index.md#Data)`, mainSectionEnrichers: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<`[`Data`](index.md#Data)`>> = emptyList())`<br>The base class for all parametrized test cases. Extend this class with a single base project-wide inheritor of [TestCase](../-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not recommended, use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead. |

### Properties

| Name | Summary |
|---|---|
| [adbServer](adb-server.md) | `open val adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md) |
| [device](device.md) | `open val device: `[`Device`](../../com.kaspersky.kaspresso.device/-device/index.md) |
| [params](params.md) | `open val params: `[`Params`](../../com.kaspersky.kaspresso.params/-params/index.md) |
| [testLogger](test-logger.md) | `open val testLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md) |

### Functions

| Name | Summary |
|---|---|
| [before](before.md) | `fun before(testName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = testCaseName, actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)`<`[`InitData`](index.md#InitData)`, `[`Data`](index.md#Data)`>`<br>Starts the building of a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-before-test-section/index.md) actions and returns an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md) to continue the building a test. |
| [init](init.md) | `fun init(testName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = testCaseName, actions: `[`InitData`](index.md#InitData)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`TransformSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-transform-section/index.md)`<`[`Data`](index.md#Data)`>`<br>Starts the building of a test from data initialization section. Sets [com.kaspersky.kaspresso.testcases.core.sections.InitSection](../../com.kaspersky.kaspresso.testcases.core.sections/-init-section/index.md) actions and returns an existing instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) to continue building a tests. |
| [run](run.md) | `fun run(testName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = testCaseName, steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) steps, creates an instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) and runs it |

### Inheritors

| Name | Summary |
|---|---|
| [TestCase](../-test-case/index.md) | `abstract class TestCase : `[`BaseTestCase`](./index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>`<br>The base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](../-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead. |
