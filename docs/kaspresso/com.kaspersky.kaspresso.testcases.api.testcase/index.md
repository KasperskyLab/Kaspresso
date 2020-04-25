[kaspresso](../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](./index.md)

## Package com.kaspersky.kaspresso.testcases.api.testcase

### Types

| Name | Summary |
|---|---|
| [BaseTestCase](-base-test-case/index.md) | The base class for all parametrized test cases. Extend this class with a single base project-wide inheritor of [TestCase](-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not recommended, use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.`abstract class BaseTestCase<InitData, Data> : `[`TestAssistantsProvider`](../com.kaspersky.kaspresso.testcases.core.testassistants/-test-assistants-provider/index.md) |
| [DocLocScreenshotTestCase](-doc-loc-screenshot-test-case/index.md) | The base class for all docloc screenshot tests.`abstract class DocLocScreenshotTestCase : `[`TestCase`](-test-case/index.md) |
| [TestCase](-test-case/index.md) | The base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.`abstract class TestCase : `[`BaseTestCase`](-base-test-case/index.md)`<Unit, Unit>` |
