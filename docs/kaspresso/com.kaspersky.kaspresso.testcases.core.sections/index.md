[kaspresso](../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](./index.md)

## Package com.kaspersky.kaspresso.testcases.core.sections

### Types

| Name | Summary |
|---|---|
| [AfterTestSection](-after-test-section/index.md) | `class AfterTestSection<InitData, Data>`<br>The representation of a set of actions to invoke after the test. |
| [BeforeTestSection](-before-test-section/index.md) | `class BeforeTestSection<InitData, Data>`<br>The representation of a set of actions to be invoked before the test. |
| [InitSection](-init-section/index.md) | `interface InitSection<InitData, Data>` |
| [MainTestSection](-main-test-section/index.md) | `class MainTestSection<InitData, Data> : `[`InitSection`](-init-section/index.md)`<`[`InitData`](-main-test-section/index.md#InitData)`, `[`Data`](-main-test-section/index.md#Data)`>, `[`TransformSection`](-transform-section/index.md)`<`[`Data`](-main-test-section/index.md#Data)`>`<br>The representation of an actual test. |
| [TransformSection](-transform-section/index.md) | `interface TransformSection<Data>` |
