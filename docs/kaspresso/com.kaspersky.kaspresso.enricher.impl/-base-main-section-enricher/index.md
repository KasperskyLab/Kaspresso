[kaspresso](../../index.md) / [com.kaspersky.kaspresso.enricher.impl](../index.md) / [BaseMainSectionEnricher](./index.md)

# BaseMainSectionEnricher

`abstract class BaseMainSectionEnricher<Data> : `[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<`[`Data`](index.md#Data)`>`

Base implementation of [com.kaspersky.kaspresso.enricher.MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface for supporting DSL-style
of enriching main section.

In [enrichMainSection](enrich-main-section.md) method you should add before/after actions into your enricher, like this:

```
        override fun enrichMainSection() {
            beforeMainSectionRun { testInfo ->
                // here, this == TestContext<Data>, so we can use all properties and functions of TestContext
                step("My before main section run action | test info: $testInfo") {
                    // do something
                }
            }

            afterMainSectionRun { testInfo ->
                // here, this == TestContext<Data>, so we can use all properties and functions of TestContext
            }
        }

    }
```

### Parameters

`Data` -
* The same data type as in your [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseMainSectionEnricher()`<br>Base implementation of [com.kaspersky.kaspresso.enricher.MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface for supporting DSL-style of enriching main section. |

### Functions

| Name | Summary |
|---|---|
| [afterMainSectionRun](after-main-section-run.md) | `fun afterMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, testContext: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method will be invoked right after execution of "run" block in your test case.`fun afterMainSectionRun(action: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.(`[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Use this method to add actions after main section 'run' block. |
| [beforeMainSectionRun](before-main-section-run.md) | `fun beforeMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, testContext: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method will be invoked right before execution of "run" block in your test case.`fun beforeMainSectionRun(action: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.(`[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Use this method to add actions before main section 'run' block. |
| [enrichMainSection](enrich-main-section.md) | `abstract fun enrichMainSection(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>In this method you will add [beforeMainSectionRun](before-main-section-run.md) and [afterMainSectionRun](after-main-section-run.md) actions for your enricher. |
