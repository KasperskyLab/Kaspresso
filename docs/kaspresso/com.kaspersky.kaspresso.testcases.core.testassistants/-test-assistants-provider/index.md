//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.core.testassistants](../index.md)/[TestAssistantsProvider](index.md)



# TestAssistantsProvider  
 [androidJvm] 

Provider of test assistants allowed in [com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md), [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md) and their inheritors

interface [TestAssistantsProvider](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [adbServer](index.md#com.kaspersky.kaspresso.testcases.core.testassistants/TestAssistantsProvider/adbServer/#/PointingToDeclaration/)|  [androidJvm] abstract val [adbServer](index.md#com.kaspersky.kaspresso.testcases.core.testassistants/TestAssistantsProvider/adbServer/#/PointingToDeclaration/): [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)   <br>
| [device](index.md#com.kaspersky.kaspresso.testcases.core.testassistants/TestAssistantsProvider/device/#/PointingToDeclaration/)|  [androidJvm] abstract val [device](index.md#com.kaspersky.kaspresso.testcases.core.testassistants/TestAssistantsProvider/device/#/PointingToDeclaration/): [Device](../../com.kaspersky.kaspresso.device/-device/index.md)   <br>
| [params](index.md#com.kaspersky.kaspresso.testcases.core.testassistants/TestAssistantsProvider/params/#/PointingToDeclaration/)|  [androidJvm] abstract val [params](index.md#com.kaspersky.kaspresso.testcases.core.testassistants/TestAssistantsProvider/params/#/PointingToDeclaration/): [Params](../../com.kaspersky.kaspresso.params/-params/index.md)   <br>
| [testLogger](index.md#com.kaspersky.kaspresso.testcases.core.testassistants/TestAssistantsProvider/testLogger/#/PointingToDeclaration/)|  [androidJvm] abstract val [testLogger](index.md#com.kaspersky.kaspresso.testcases.core.testassistants/TestAssistantsProvider/testLogger/#/PointingToDeclaration/): [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)   <br>


## Inheritors  
  
|  Name| 
|---|
| [BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md)
| [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md)

