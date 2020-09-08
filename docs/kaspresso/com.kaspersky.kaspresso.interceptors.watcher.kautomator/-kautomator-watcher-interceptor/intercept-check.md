//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.kautomator](../index.md)/[KautomatorWatcherInterceptor](index.md)/[interceptCheck](intercept-check.md)



# interceptCheck  
[androidJvm]  
Brief description  


Called to do some stuff before UiInteraction.check is actually called.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| assertion| <br><br>responsible for performing an activity (assertion) on the given interaction<br><br>
| interaction| <br><br>a Kautomator UiInteraction on which assertion is performed<br><br>
  
  
Content  
abstract fun [interceptCheck](intercept-check.md)(interaction: [Interaction](index.md), assertion: [Assertion](index.md))  



