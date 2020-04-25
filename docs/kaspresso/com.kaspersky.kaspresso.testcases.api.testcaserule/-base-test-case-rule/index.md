[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [BaseTestCaseRule](./index.md)

# BaseTestCaseRule

`open class BaseTestCaseRule<InitData, Data> : TestRule`

The base class for all parametrized test cases rules.

### Parameters

`InitData` - data initialized in before section.

`Data` - data transformed from [InitData](index.md#InitData) by special function.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The base class for all parametrized test cases rules.`BaseTestCaseRule(kaspressoBuilder: Builder = Kaspresso.Builder.advanced(), testClassName: String, dataProducer: ((InitData.() -> Unit)?) -> Data, mainSectionEnrichers: List<`[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<Data>> = emptyList())` |

### Functions

| Name | Summary |
|---|---|
| [apply](apply.md) | `open fun apply(base: Statement?, description: Description?): Statement` |
| [before](before.md) | Starts the building a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-before-test-section/index.md) actions and returns an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md) to continue building a test.`fun before(testName: String = testClassName, actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> Unit): `[`AfterTestSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)`<InitData, Data>` |
| [init](init.md) | Starts the building of a test from data initialization section. Sets [com.kaspersky.kaspresso.testcases.core.sections.InitSection](../../com.kaspersky.kaspresso.testcases.core.sections/-init-section/index.md) actions and returns an existing instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) to continue building a tests.`fun init(testName: String = testClassName, actions: InitData.() -> Unit): `[`TransformSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-transform-section/index.md)`<Data>` |
| [run](run.md) | Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) steps, creates an instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) and runs it`fun run(testName: String = testClassName, steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<Data>.() -> Unit): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [TestCaseRule](../-test-case-rule/index.md) | The base class for all test cases. Extend this class with a single base project-wide inheritor of [com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.`class TestCaseRule : `[`BaseTestCaseRule`](./index.md)`<Unit, Unit>` |
