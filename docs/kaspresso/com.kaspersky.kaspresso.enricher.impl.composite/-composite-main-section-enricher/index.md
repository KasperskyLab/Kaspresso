[kaspresso](../../index.md) / [com.kaspersky.kaspresso.enricher.impl.composite](../index.md) / [CompositeMainSectionEnricher](./index.md)

# CompositeMainSectionEnricher

`class CompositeMainSectionEnricher<Data> : `[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<`[`Data`](index.md#Data)`>`

The implementation of the [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface.
Composes all of [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)s list into one composite [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) that is actually
called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CompositeMainSectionEnricher(mainSectionEnrichers: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<`[`Data`](index.md#Data)`>>, exceptions: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>)`<br>The implementation of the [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface. Composes all of [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)s list into one composite [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) that is actually called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event. |

### Functions

| Name | Summary |
|---|---|
| [afterMainSectionRun](after-main-section-run.md) | `fun `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.afterMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method will be invoked right after execution of "run" block in your test case. |
| [beforeMainSectionRun](before-main-section-run.md) | `fun `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.beforeMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This method will be invoked right before execution of "run" block in your test case. |
