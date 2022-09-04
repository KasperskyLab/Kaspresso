//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.enricher.impl.composite](../index.md)/[CompositeMainSectionEnricher](index.md)



# CompositeMainSectionEnricher  
 [androidJvm] 

The implementation of the [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface. Composes all of [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)s list into one composite [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) that is actually called by com.kaspersky.kaspresso.testcases.core.TestRunner on each test event.

class [CompositeMainSectionEnricher](index.md)<[Data](index.md)>(**mainSectionEnrichers**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](index.md)>>, **exceptions**: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>) : [MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](index.md)>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [CompositeMainSectionEnricher](-composite-main-section-enricher.md)|  [androidJvm] fun <[Data](index.md)> [CompositeMainSectionEnricher](-composite-main-section-enricher.md)(mainSectionEnrichers: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](index.md)>>, exceptions: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [afterMainSectionRun](after-main-section-run.md)| [androidJvm]  <br>Brief description  <br><br><br>This method will be invoked right after execution of "run" block in your test case.<br><br>  <br>Content  <br>open override fun [TestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)<[Data](index.md)>.[afterMainSectionRun](after-main-section-run.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [beforeMainSectionRun](before-main-section-run.md)| [androidJvm]  <br>Brief description  <br><br><br>This method will be invoked right before execution of "run" block in your test case.<br><br>  <br>Content  <br>open override fun [TestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)<[Data](index.md)>.[beforeMainSectionRun](before-main-section-run.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

