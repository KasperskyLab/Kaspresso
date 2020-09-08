//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.core.sections](../index.md)/[AfterTestSection](index.md)/[after](after.md)



# after  
[androidJvm]  
Brief description  


Wraps actions in a lambda, that will invoke these actions and make screenshot if actions will fail when it will be invoked itself, and sets this lambda as the TestBody.afterTestActions.



#### Return  


[InitSection](../-init-section/index.md) to continue building a test.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| actions| <br><br>actions to be wrapped and invoked after the test.<br><br>
  
  
Content  
fun [after](after.md)(actions: [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [InitSection](../-init-section/index.md)<[InitData](index.md), [Data](index.md)>  



