//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcaserule](../index.md)/[BaseTestCaseRule](index.md)/[before](before.md)



# before  
[androidJvm]  
Brief description  


Starts the building a test, sets the [BeforeTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-before-test-section/index.md) actions and returns an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md) to continue building a test.



#### Return  


an existing instance of [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md).



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| actions| <br><br>actions to invoke in before test section.<br><br>
| testName| <br><br>a name of the test.<br><br>
  
  
Content  
fun [before](before.md)(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), actions: [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [AfterTestSection](../../com.kaspersky.kaspresso.testcases.core.sections/-after-test-section/index.md)<[InitData](index.md), [Data](index.md)>  



