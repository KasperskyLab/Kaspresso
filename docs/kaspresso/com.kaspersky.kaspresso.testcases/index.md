[kaspresso](../index.md) / [com.kaspersky.kaspresso.testcases](./index.md)

## Package com.kaspersky.kaspresso.testcases

### Types

| Name | Summary |
|---|---|
| [AfterTestSection](-after-test-section/index.md) | `class AfterTestSection`<br>A representation of a set of actions to invoke after the test. |
| [BeforeTestSection](-before-test-section/index.md) | `class BeforeTestSection`<br>A representation of a set of actions to be invoked before the test. |
| [Scenario](-scenario/index.md) | `class Scenario`<br>A representation of a sequence of test's actions. |
| [SubCase](-sub-case/index.md) | `abstract class SubCase`<br>A base class for all subcases. A representation of some repeating scenario inside the [TestCase](-test-case/index.md). |
| [TestCase](-test-case/index.md) | `abstract class TestCase`<br>A base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Configurator](../com.kaspersky.kaspresso.configurator/-configurator/index.md), use [Scenario](-scenario/index.md) instead. |
