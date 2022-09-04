//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md)/[TestCaseRule](index.md)



# TestCaseRule  
 [androidJvm] 

The base class for all test cases. Extend this class with a single base project-wide inheritor of [com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.

class [TestCaseRule](index.md)(**testClassName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **kaspressoBuilder**: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md)) : [BaseTestCaseRule](../-base-test-case-rule/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [TestCaseRule](-test-case-rule.md)|  [androidJvm] fun [TestCaseRule](-test-case-rule.md)(testClassName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), kaspressoBuilder: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [apply](../-base-test-case-rule/apply.md)| [androidJvm]  <br>Content  <br>open override fun [apply](../-base-test-case-rule/apply.md)(base: Statement?, description: Description?): Statement  <br><br><br>
| [before](../-base-test-case-rule/before.md)| [androidJvm]  <br>Brief description  <br><br><br>Starts the building a test, sets the BeforeTestSection actions and returns an existing instance of AfterTestSection to continue building a test.<br><br>  <br>Content  <br>override fun [before](../-base-test-case-rule/before.md)(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), actions: [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>  <br><br><br>
| createBaseTestBodyBuilder| [androidJvm]  <br>Content  <br>override fun createBaseTestBodyBuilder(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): TestBody.Builder<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| init| [androidJvm]  <br>Brief description  <br><br><br>Starts the building of a test from data initialization section. Sets [com.kaspersky.kaspresso.testcases.core.sections.InitSection](../../com.kaspersky.kaspresso.testcases.core.sections/-init-section/index.md) actions and returns an existing instance of MainTestSection to continue building a tests.<br><br>  <br>Content  <br>override fun init(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), actions: [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [TransformSection](../../com.kaspersky.kaspresso.testcases.core.sections/-transform-section/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>  <br><br><br>
| run| [androidJvm]  <br>Brief description  <br><br><br>Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) steps, creates an instance of MainTestSection and runs it<br><br>  <br>Content  <br>override fun run(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), steps: [TestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [dataProducer](index.md#com.kaspersky.kaspresso.testcases.api.testcaserule/TestCaseRule/dataProducer/#/PointingToDeclaration/)|  [androidJvm] override val [dataProducer](index.md#com.kaspersky.kaspresso.testcases.api.testcaserule/TestCaseRule/dataProducer/#/PointingToDeclaration/): ([Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)   <br>
| [kaspresso](index.md#com.kaspersky.kaspresso.testcases.api.testcaserule/TestCaseRule/kaspresso/#/PointingToDeclaration/)|  [androidJvm] override val [kaspresso](index.md#com.kaspersky.kaspresso.testcases.api.testcaserule/TestCaseRule/kaspresso/#/PointingToDeclaration/): [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md)   <br>
| [mainSectionEnrichers](index.md#com.kaspersky.kaspresso.testcases.api.testcaserule/TestCaseRule/mainSectionEnrichers/#/PointingToDeclaration/)|  [androidJvm] override val [mainSectionEnrichers](index.md#com.kaspersky.kaspresso.testcases.api.testcaserule/TestCaseRule/mainSectionEnrichers/#/PointingToDeclaration/): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>>   <br>
| [testClassName](index.md#com.kaspersky.kaspresso.testcases.api.testcaserule/TestCaseRule/testClassName/#/PointingToDeclaration/)|  [androidJvm] override val [testClassName](index.md#com.kaspersky.kaspresso.testcases.api.testcaserule/TestCaseRule/testClassName/#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>

