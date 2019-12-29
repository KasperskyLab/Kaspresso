[kaspresso](../../index.md) / [com.kaspersky.kaspresso.enricher.impl.composite](../index.md) / [CompositeMainSectionEnricher](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`CompositeMainSectionEnricher(mainSectionEnrichers: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<`[`Data`](index.md#Data)`>>, exceptions: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>)`

The implementation of the [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface.
Composes all of [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)s list into one composite [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) that is actually
called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event.

