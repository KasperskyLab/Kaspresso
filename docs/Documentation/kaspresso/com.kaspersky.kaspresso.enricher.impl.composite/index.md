//[kaspresso](../index.md)/[com.kaspersky.kaspresso.enricher.impl.composite](index.md)



# Package com.kaspersky.kaspresso.enricher.impl.composite  


## Types  
  
|  Name|  Summary| 
|---|---|
| [CompositeMainSectionEnricher](-composite-main-section-enricher/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The implementation of the [MainSectionEnricher](../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface. Composes all of [MainSectionEnricher](../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)s list into one composite [MainSectionEnricher](../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) that is actually called by com.kaspersky.kaspresso.testcases.core.TestRunner on each test event.<br><br>  <br>Content  <br>class [CompositeMainSectionEnricher](-composite-main-section-enricher/index.md)<[Data](-composite-main-section-enricher/index.md)>(**mainSectionEnrichers**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](-composite-main-section-enricher/index.md)>>, **exceptions**: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>) : [MainSectionEnricher](../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](-composite-main-section-enricher/index.md)>   <br><br><br>

