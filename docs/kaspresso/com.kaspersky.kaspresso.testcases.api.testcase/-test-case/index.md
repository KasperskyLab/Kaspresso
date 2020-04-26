[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](../index.md) / [TestCase](./index.md)

# TestCase

`abstract class TestCase : `[`BaseTestCase`](../-base-test-case/index.md)`<Unit, Unit>`

The base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](./index.md) as a
parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use
[com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](./index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.`TestCase(kaspressoBuilder: Builder = Kaspresso.Builder.simple())` |

### Inheritors

| Name | Summary |
|---|---|
| [DocLocScreenshotTestCase](../-doc-loc-screenshot-test-case/index.md) | The base class for all docloc screenshot tests.`abstract class DocLocScreenshotTestCase : `[`TestCase`](./index.md) |
