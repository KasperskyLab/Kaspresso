[kaspresso](../../index.md) / [com.kaspersky.kaspresso.enricher.impl.composite](../index.md) / [CompositeMainSectionEnricher](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`CompositeMainSectionEnricher(mainSectionEnrichers: List<`[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<Data>>, exceptions: MutableList<Throwable>)`

The implementation of the [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface.
Composes all of [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)s list into one composite [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) that is actually
called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event.

