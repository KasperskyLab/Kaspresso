//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view](../index.md)/[ViewActionWatcherInterceptor](index.md)/[intercept](intercept.md)



# intercept  
[androidJvm]  
Brief description  


Called to do some stuff before ViewAction.perform is actually called.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| view| <br><br>an Android [View](https://developer.android.com/reference/kotlin/android/view/View.html), on which viewAction is performed.<br><br>
| viewAction| <br><br>responsible for performing an interaction on the given [View](https://developer.android.com/reference/kotlin/android/view/View.html) element.<br><br>
  
  
Content  
abstract fun [intercept](intercept.md)(viewAction: ViewAction, view: [View](https://developer.android.com/reference/kotlin/android/view/View.html))  



