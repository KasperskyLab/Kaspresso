[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [BaseTestCaseRule](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`BaseTestCaseRule(kaspressoBuilder: Builder = Kaspresso.Builder.simple(), testClassName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, dataProducer: ((InitData.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)?) -> Data, mainSectionEnrichers: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<Data>> = emptyList())`

The base class for all parametrized test cases rules.

### Parameters

`InitData` - data initialized in before section.

`Data` - data transformed from [InitData](index.md#InitData) by special function.