//[kaspresso](../index.md)/[com.kaspersky.kaspresso.enricher](index.md)



# Package com.kaspersky.kaspresso.enricher  


## Types  
  
|  Name|  Summary| 
|---|---|
| [MainSectionEnricher](-main-section-enricher/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Special object for enriching 'run'-block functionality. With this object you can add some additional test steps for each TestCase, that has this enricher, like this:<br><br><br><br><code> class MyMainSectionEnricher : MainSectionEnricher<TestCaseData> {<br><br>    override fun TestContext<TestCaseData>.beforeMainSectionRun(testInfo: TestInfo) {<br>        step("New step before 'run' block") {<br>            step("Nested step inside") {<br>                // do additional stuff<br>            }<br>        }<br>    }<br><br>} </code><br><br><br><br>  <br>Content  <br>interface [MainSectionEnricher](-main-section-enricher/index.md)<[Data](-main-section-enricher/index.md)>  <br><br><br>

