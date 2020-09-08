//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcase](../index.md)/[TestCase](index.md)



# TestCase  
 [androidJvm] 

The base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.

abstract class [TestCase](index.md)(**kaspressoBuilder**: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md)) : [BaseTestCase](../-base-test-case/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [TestCase](-test-case.md)|  [androidJvm] fun [TestCase](-test-case.md)(kaspressoBuilder: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| createBaseTestBodyBuilder| [androidJvm]  <br>Content  <br>override fun createBaseTestBodyBuilder(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): TestBody.Builder<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [adbServer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/adbServer/#/PointingToDeclaration/)|  [androidJvm] open override val [adbServer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/adbServer/#/PointingToDeclaration/): [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)   <br>
| [dataProducer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/dataProducer/#/PointingToDeclaration/)|  [androidJvm] override val [dataProducer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/dataProducer/#/PointingToDeclaration/): ([Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)   <br>
| [device](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/device/#/PointingToDeclaration/)|  [androidJvm] open override val [device](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/device/#/PointingToDeclaration/): [Device](../../com.kaspersky.kaspresso.device/-device/index.md)   <br>
| [mainSectionEnrichers](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/mainSectionEnrichers/#/PointingToDeclaration/)|  [androidJvm] override val [mainSectionEnrichers](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/mainSectionEnrichers/#/PointingToDeclaration/): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>>   <br>
| [params](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/params/#/PointingToDeclaration/)|  [androidJvm] open override val [params](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/params/#/PointingToDeclaration/): [Params](../../com.kaspersky.kaspresso.params/-params/index.md)   <br>
| [testAssistantsProvider](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/testAssistantsProvider/#/PointingToDeclaration/)|  [androidJvm] override val [testAssistantsProvider](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/testAssistantsProvider/#/PointingToDeclaration/): TestAssistantsProviderImpl   <br>
| [testCaseName](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/testCaseName/#/PointingToDeclaration/)|  [androidJvm] override val [testCaseName](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/testCaseName/#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>
| [testLogger](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/testLogger/#/PointingToDeclaration/)|  [androidJvm] open override val [testLogger](index.md#com.kaspersky.kaspresso.testcases.api.testcase/TestCase/testLogger/#/PointingToDeclaration/): [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)   <br>


## Inheritors  
  
|  Name| 
|---|
| [DocLocScreenshotTestCase](../-doc-loc-screenshot-test-case/index.md)

