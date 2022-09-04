//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md)/[BaseTestCaseRule](index.md)



# BaseTestCaseRule  
 [androidJvm] 

The base class for all parametrized test cases rules.

open class [BaseTestCaseRule](index.md)<[InitData](index.md), [Data](index.md)>(**kaspressoBuilder**: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md), **testClassName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **dataProducer**: ([InitData](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) -> [Data](index.md), **mainSectionEnrichers**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](index.md)>>) : TestRule   


## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| Data| <br><br>data transformed from [InitData](index.md) by special function.<br><br>
| InitData| <br><br>data initialized in before section.<br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [BaseTestCaseRule](-base-test-case-rule.md)|  [androidJvm] <br><br>data initialized in before section.<br><br>fun <[InitData](index.md), [Data](index.md)> [BaseTestCaseRule](-base-test-case-rule.md)(kaspressoBuilder: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md), testClassName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), dataProducer: ([InitData](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) -> [Data](index.md), mainSectionEnrichers: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](index.md)>>)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [apply](apply.md)| [androidJvm]  <br>Content  <br>open override fun [apply](apply.md)(base: Statement?, description: Description?): Statement  <br><br><br>
| [before](before.md)| [androidJvm]  <br>Brief description  <br><br><br>Starts the building a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-before-test-section/index.md) actions and returns an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md) to continue building a test.<br><br>  <br>Content  <br>fun [before](before.md)(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), actions: [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)<[InitData](index.md), [Data](index.md)>  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [init](init.md)| [androidJvm]  <br>Brief description  <br><br><br>Starts the building of a test from data initialization section. Sets [com.kaspersky.kaspresso.testcases.core.sections.InitSection](../../com.kaspersky.kaspresso.testcases.core.sections/-init-section/index.md) actions and returns an existing instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) to continue building a tests.<br><br>  <br>Content  <br>fun [init](init.md)(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), actions: [InitData](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [TransformSection](../../com.kaspersky.kaspresso.testcases.core.sections/-transform-section/index.md)<[Data](index.md)>  <br><br><br>
| [run](run.md)| [androidJvm]  <br>Brief description  <br><br><br>Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) steps, creates an instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) and runs it<br><br>  <br>Content  <br>fun [run](run.md)(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), steps: [TestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)<[Data](index.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [TestCaseRule](../-test-case-rule/index.md)

