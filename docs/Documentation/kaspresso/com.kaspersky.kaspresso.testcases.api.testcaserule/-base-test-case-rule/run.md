//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md)/[BaseTestCaseRule](index.md)/[run](run.md)



# run  
[androidJvm]  
Brief description  


Sets [com.kaspersky.kaspresso.testcases.core.sections.MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) steps, creates an instance of [MainTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-main-test-section/index.md) and runs it



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| steps| <br><br>actions to invoke in main test section.<br><br>
| testName| <br><br>a name of the test.<br><br>
  
  
Content  
fun [run](run.md)(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), steps: [TestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)<[Data](index.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  



