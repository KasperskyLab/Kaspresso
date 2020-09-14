//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite](../index.md)/[TestRunCompositeWatcherInterceptor](index.md)/[onMainSectionFinishedFailed](on-main-section-finished-failed.md)



# onMainSectionFinishedFailed  
[androidJvm]  
Brief description  


Called on "main" section finishes with failure, delegates the interception to watcherInterceptors.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| testInfo| <br><br>the test info to pass to watcherInterceptors.<br><br>
| throwable| <br><br>the error occured to pass to  watcherInterceptors.<br><br>
  
  
Content  
open override fun [onMainSectionFinishedFailed](on-main-section-finished-failed.md)(testInfo: [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  



