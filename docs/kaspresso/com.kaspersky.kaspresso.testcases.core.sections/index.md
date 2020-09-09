[kaspresso](../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](./index.md)

## Package com.kaspersky.kaspresso.testcases.core.sections

### Types

| Name | Summary |
|---|---|
| [AfterTestSection](-after-test-section/index.md) | The representation of a set of actions to invoke after the test.`class AfterTestSection<InitData, Data>` |
| [BeforeTestSection](-before-test-section/index.md) | The representation of a set of actions to be invoked before the test.`class BeforeTestSection<InitData, Data>` |
| [InitSection](-init-section/index.md) | `interface InitSection<InitData, Data>` |
| [MainTestSection](-main-test-section/index.md) | The representation of an actual test.`class MainTestSection<InitData, Data> : `[`InitSection`](-init-section/index.md)`<InitData, Data>, `[`TransformSection`](-transform-section/index.md)`<Data>` |
| [TransformSection](-transform-section/index.md) | `interface TransformSection<Data>` |
