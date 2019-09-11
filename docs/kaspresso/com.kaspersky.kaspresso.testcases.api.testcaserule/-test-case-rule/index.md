[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [TestCaseRule](./index.md)

# TestCaseRule

`class TestCaseRule : `[`BaseTestCaseRule`](../-base-test-case-rule/index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>`

The base class for all test cases. Extend this class with a single base project-wide inheritor of
[com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md) as a parent for all actual project-wide test cases.
Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the
[Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TestCaseRule(testClassName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, kaspressoBuilder: `[`Kaspresso.Builder`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md)` = Kaspresso.Builder.default())`<br>The base class for all test cases. Extend this class with a single base project-wide inheritor of [com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead. |

### Inherited Functions

| Name | Summary |
|---|---|
| [apply](../-base-test-case-rule/apply.md) | `open fun apply(base: Statement?, description: Description?): Statement` |
| [before](../-base-test-case-rule/before.md) | `fun before(testName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = testClassName, actions: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`AfterTestSection`](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)`<`[`InitData`](../-base-test-case-rule/index.md#InitData)`, `[`Data`](../-base-test-case-rule/index.md#Data)`>`<br>Starts the building a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-before-test-section/index.md) actions and returns an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md) to continue building a test. |
