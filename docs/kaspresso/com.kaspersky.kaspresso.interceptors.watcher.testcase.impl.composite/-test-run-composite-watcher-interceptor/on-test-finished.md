//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite](../index.md)/[TestRunCompositeWatcherInterceptor](index.md)/[onTestFinished](on-test-finished.md)



# onTestFinished  
[androidJvm]  
Brief description  


Called on the whole test finishes, delegates the interception to watcherInterceptors.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| success| <br><br>the while test was finished successfully or not, to pass to watcherInterceptors.<br><br>
| testInfo| <br><br>the test info to pass to watcherInterceptors.<br><br>
  
  
Content  
open override fun [onTestFinished](on-test-finished.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), success: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  



