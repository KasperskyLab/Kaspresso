[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md) / [BaseTestCaseRule](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`BaseTestCaseRule(kaspressoBuilder: `[`Kaspresso.Builder`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md)` = Kaspresso.Builder.default(), dataProducer: ((`[`InitData`](index.md#InitData)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)?) -> `[`Data`](index.md#Data)`, testClassName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`

The base class for all parametrized test cases rules.

### Parameters

`InitData` - data initialized in before section.

`Data` - data transformed from [InitData](index.md#InitData) by special function.