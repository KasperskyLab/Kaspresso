//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.enricher](../index.md)/[MainSectionEnricher](index.md)



# MainSectionEnricher  
 [androidJvm] 



Special object for enriching 'run'-block functionality. With this object you can add some additional test steps for each TestCase, that has this enricher, like this:



<code> class MyMainSectionEnricher : MainSectionEnricher<TestCaseData> {

    override fun TestContext<TestCaseData>.beforeMainSectionRun(testInfo: TestInfo) {
        step("New step before 'run' block") {
            step("Nested step inside") {
                // do additional stuff
            }
        }
    }

} </code>



interface [MainSectionEnricher](index.md)<[Data](index.md)>   


## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| Data| <ul><li>The same data type as in your [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md).</li></ul>
  


## Functions  
  
|  Name|  Summary| 
|---|---|
| [afterMainSectionRun](after-main-section-run.md)| [androidJvm]  <br>Brief description  <br><br><br>This method will be invoked right after execution of "run" block in your test case.<br><br>  <br>Content  <br>open fun [TestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)<[Data](index.md)>.[afterMainSectionRun](after-main-section-run.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [beforeMainSectionRun](before-main-section-run.md)| [androidJvm]  <br>Brief description  <br><br><br>This method will be invoked right before execution of "run" block in your test case.<br><br>  <br>Content  <br>open fun [TestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)<[Data](index.md)>.[beforeMainSectionRun](before-main-section-run.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [CompositeMainSectionEnricher](../../com.kaspersky.kaspresso.enricher.impl.composite/-composite-main-section-enricher/index.md)

