//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcase](../index.md)/[BaseTestCase](index.md)/[BaseTestCase](-base-test-case.md)



# BaseTestCase  
[androidJvm]  
Brief description  


## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| Data| <br><br>data transformed from [InitData](index.md) by special function.<br><br>
| InitData| <br><br>data initialized in before section.<br><br>
  
  
Content  
fun <[InitData](index.md), [Data](index.md)> [BaseTestCase](-base-test-case.md)(kaspressoBuilder: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md), dataProducer: ([InitData](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) -> [Data](index.md), mainSectionEnrichers: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](index.md)>>)  



