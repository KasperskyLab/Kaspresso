//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.kautomator](../index.md)/[KautomatorWatcherInterceptor](index.md)/[interceptPerform](intercept-perform.md)



# interceptPerform  
[androidJvm]  
Brief description  


Called to do some stuff before UiInteraction.perform is actually called.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>responsible for performing an activity (action) on the given interaction<br><br>
| interaction| <br><br>a Kautomator UiInteraction on which action is performed<br><br>
  
  
Content  
abstract fun [interceptPerform](intercept-perform.md)(interaction: [Interaction](index.md), action: [Action](index.md))  



