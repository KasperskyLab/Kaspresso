[kaspresso](../../index.md) / [com.kaspersky.kaspresso.enricher](../index.md) / [MainSectionEnricher](./index.md)

# MainSectionEnricher

`interface MainSectionEnricher<Data>`

Special object for enriching 'run'-block functionality.
With this object you can add some additional test steps for each TestCase, that has this enricher, like this:

```
    override fun TestContext<TestCaseData>.beforeMainSectionRun(testInfo: TestInfo) {
        step("New step before 'run' block") {
            step("Nested step inside") {
                // do additional stuff
            }
        }
    }
```

}

### Parameters

`Data` -
* The same data type as in your [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md).

### Functions

| Name | Summary |
|---|---|
| [afterMainSectionRun](after-main-section-run.md) | `open fun `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.afterMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method will be invoked right after execution of "run" block in your test case. |
| [beforeMainSectionRun](before-main-section-run.md) | `open fun `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.beforeMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method will be invoked right before execution of "run" block in your test case. |

### Inheritors

| Name | Summary |
|---|---|
| [CompositeMainSectionEnricher](../../com.kaspersky.kaspresso.enricher.impl.composite/-composite-main-section-enricher/index.md) | `class CompositeMainSectionEnricher<Data> : `[`MainSectionEnricher`](./index.md)`<`[`Data`](../../com.kaspersky.kaspresso.enricher.impl.composite/-composite-main-section-enricher/index.md#Data)`>`<br>The implementation of the [MainSectionEnricher](./index.md) interface. Composes all of [MainSectionEnricher](./index.md)s list into one composite [MainSectionEnricher](./index.md) that is actually called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event. |
