//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcase](../index.md)/[BaseTestCase](index.md)



# BaseTestCase  
 [androidJvm] 

The base class for all parametrized test cases. Extend this class with a single base project-wide inheritor of [TestCase](../-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not recommended, use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.

abstract class [BaseTestCase](index.md)<[InitData](index.md), [Data](index.md)>(**kaspressoBuilder**: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md), **dataProducer**: ([InitData](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) -> [Data](index.md), **mainSectionEnrichers**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](index.md)>>) : [TestAssistantsProvider](../../com.kaspersky.kaspresso.testcases.core.testassistants/-test-assistants-provider/index.md)   


## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| Data| <br><br>data transformed from [InitData](index.md) by special function.<br><br>
| InitData| <br><br>data initialized in before section.<br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [BaseTestCase](-base-test-case.md)|  [androidJvm] <br><br>data initialized in before section.<br><br>fun <[InitData](index.md), [Data](index.md)> [BaseTestCase](-base-test-case.md)(kaspressoBuilder: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md), dataProducer: ([InitData](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) -> [Data](index.md), mainSectionEnrichers: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](index.md)>>)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [adbServer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/BaseTestCase/adbServer/#/PointingToDeclaration/)|  [androidJvm] open override val [adbServer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/BaseTestCase/adbServer/#/PointingToDeclaration/): [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)   <br>
| [device](index.md#com.kaspersky.kaspresso.testcases.api.testcase/BaseTestCase/device/#/PointingToDeclaration/)|  [androidJvm] open override val [device](index.md#com.kaspersky.kaspresso.testcases.api.testcase/BaseTestCase/device/#/PointingToDeclaration/): [Device](../../com.kaspersky.kaspresso.device/-device/index.md)   <br>
| [params](index.md#com.kaspersky.kaspresso.testcases.api.testcase/BaseTestCase/params/#/PointingToDeclaration/)|  [androidJvm] open override val [params](index.md#com.kaspersky.kaspresso.testcases.api.testcase/BaseTestCase/params/#/PointingToDeclaration/): [Params](../../com.kaspersky.kaspresso.params/-params/index.md)   <br>
| [testLogger](index.md#com.kaspersky.kaspresso.testcases.api.testcase/BaseTestCase/testLogger/#/PointingToDeclaration/)|  [androidJvm] open override val [testLogger](index.md#com.kaspersky.kaspresso.testcases.api.testcase/BaseTestCase/testLogger/#/PointingToDeclaration/): [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)   <br>


## Inheritors  
  
|  Name| 
|---|
| [TestCase](../-test-case/index.md)

