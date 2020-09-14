//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.core.sections](../index.md)/[BeforeTestSection](index.md)/[beforeTest](before-test.md)



# beforeTest  
[androidJvm]  
Brief description  


Wraps actions in a lambda, that will invoke these actions and make screenshot if actions will fail when it will be invoked itself, and sets this lambda as the TestBody.beforeTestActions.



#### Return  


[AfterTestSection](../-after-test-section/index.md) to continue building a test.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| actions| <br><br>actions to be wrapped and invoked before the test.<br><br>
  
  
Content  
fun [beforeTest](before-test.md)(actions: [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [AfterTestSection](../-after-test-section/index.md)<[InitData](index.md), [Data](index.md)>  



