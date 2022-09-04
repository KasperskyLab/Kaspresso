//[kaspresso](../index.md)/[com.kaspersky.kaspresso.testcases.api.testcaserule](index.md)



# Package com.kaspersky.kaspresso.testcases.api.testcaserule  


## Types  
  
|  Name|  Summary| 
|---|---|
| [BaseTestCaseRule](-base-test-case-rule/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The base class for all parametrized test cases rules.<br><br>  <br>Content  <br>open class [BaseTestCaseRule](-base-test-case-rule/index.md)<[InitData](-base-test-case-rule/index.md), [Data](-base-test-case-rule/index.md)>(**kaspressoBuilder**: [Kaspresso.Builder](../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md), **testClassName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **dataProducer**: ([InitData](-base-test-case-rule/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) -> [Data](-base-test-case-rule/index.md), **mainSectionEnrichers**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Data](-base-test-case-rule/index.md)>>) : TestRule  <br><br><br>
| [TestCaseRule](-test-case-rule/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The base class for all test cases. Extend this class with a single base project-wide inheritor of [com.kaspersky.kaspresso.testcases.api.testcase.TestCase](../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an exception caused by re-initialization of the [Kaspresso](../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.<br><br>  <br>Content  <br>class [TestCaseRule](-test-case-rule/index.md)(**testClassName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **kaspressoBuilder**: [Kaspresso.Builder](../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md)) : [BaseTestCaseRule](-base-test-case-rule/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>   <br><br><br>

