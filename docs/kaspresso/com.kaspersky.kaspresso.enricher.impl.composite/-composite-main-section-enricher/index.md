[kaspresso](../../index.md) / [com.kaspersky.kaspresso.enricher.impl.composite](../index.md) / [CompositeMainSectionEnricher](./index.md)

# CompositeMainSectionEnricher

`class CompositeMainSectionEnricher<Data> : `[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<Data>`

The implementation of the [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface.
Composes all of [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)s list into one composite [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) that is actually
called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface. Composes all of [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)s list into one composite [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) that is actually called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event.`CompositeMainSectionEnricher(mainSectionEnrichers: List<`[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<Data>>, exceptions: MutableList<Throwable>)` |

### Functions

| Name | Summary |
|---|---|
| [afterMainSectionRun](after-main-section-run.md) | This method will be invoked right after execution of "run" block in your test case.`fun `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<Data>.afterMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [beforeMainSectionRun](before-main-section-run.md) | This method will be invoked right before execution of "run" block in your test case.`fun `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<Data>.beforeMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
