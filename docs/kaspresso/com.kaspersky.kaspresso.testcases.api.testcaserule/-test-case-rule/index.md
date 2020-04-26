[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [TestCaseRule](./index.md)

# TestCaseRule

`class TestCaseRule : `[`BaseTestCaseRule`](../-base-test-case-rule/index.md)`<Unit, Unit>`

The base class for all test cases. Extend this class with a single base project-wide inheritor of
[com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md) as a parent for all actual project-wide test cases.
Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the
[Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The base class for all test cases. Extend this class with a single base project-wide inheritor of [com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.`TestCaseRule(testClassName: String, kaspressoBuilder: Builder = Kaspresso.Builder.simple())` |
