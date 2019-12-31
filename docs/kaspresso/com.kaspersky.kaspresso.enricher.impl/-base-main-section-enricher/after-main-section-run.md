[kaspresso](../../index.md) / [com.kaspersky.kaspresso.enricher.impl](../index.md) / [BaseMainSectionEnricher](index.md) / [afterMainSectionRun](./after-main-section-run.md)

# afterMainSectionRun

`fun afterMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, testContext: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [MainSectionEnricher.afterMainSectionRun](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/after-main-section-run.md)

This method will be invoked right after execution of "run" block in your test case.

### Parameters

`testInfo` -
* test information, such as test identifier

`testContext` -
* context of your test with data.
`fun afterMainSectionRun(action: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.(`[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Use this method to add actions after main section 'run' block.

