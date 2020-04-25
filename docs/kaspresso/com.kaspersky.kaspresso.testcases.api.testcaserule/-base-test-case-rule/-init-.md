[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [BaseTestCaseRule](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`BaseTestCaseRule(kaspressoBuilder: Builder = Kaspresso.Builder.advanced(), testClassName: String, dataProducer: ((InitData.() -> Unit)?) -> Data, mainSectionEnrichers: List<`[`MainSectionEnricher`](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)`<Data>> = emptyList())`

The base class for all parametrized test cases rules.

### Parameters

`InitData` - data initialized in before section.

`Data` - data transformed from [InitData](index.md#InitData) by special function.