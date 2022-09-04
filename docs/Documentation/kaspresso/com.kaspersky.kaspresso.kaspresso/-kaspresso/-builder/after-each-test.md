//[kaspresso](../../../index.md)/[com.kaspersky.kaspresso.kaspresso](../../index.md)/[Kaspresso](../index.md)/[Builder](index.md)/[afterEachTest](after-each-test.md)



# afterEachTest  
[androidJvm]  
Brief description  


Set the action which will be executed after the test. The action has access to BaseTestContext. If you set @param override in false then the final beforeAction will be     beforeAction of the parent TestCase plus current @param action.     Otherwise final beforeAction will be only @param action.

  
Content  
fun [afterEachTest](after-each-test.md)(override: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), action: [BaseTestContext](../../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  



