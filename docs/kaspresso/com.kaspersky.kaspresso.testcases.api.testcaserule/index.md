[kaspresso](../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](./index.md)

## Package com.kaspersky.kaspresso.testcases.api.testcaserule

### Types

| Name | Summary |
|---|---|
| [BaseTestCaseRule](-base-test-case-rule/index.md) | The base class for all parametrized test cases rules.`open class BaseTestCaseRule<InitData, Data> : TestRule` |
| [TestCaseRule](-test-case-rule/index.md) | The base class for all test cases. Extend this class with a single base project-wide inheritor of [com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.`class TestCaseRule : `[`BaseTestCaseRule`](-base-test-case-rule/index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>` |
