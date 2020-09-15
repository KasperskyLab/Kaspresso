//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md)/[LoggingViewAssertionWatcherInterceptor](index.md)/[intercept](intercept.md)



# intercept  
[androidJvm]  
Brief description  


Writes info to logger.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| exception| <br><br>indicates that a given matcher did not match any elements in the view hierarchy.<br><br>
| view| <br><br>an Android [View](https://developer.android.com/reference/kotlin/android/view/View.html), on which viewAssertion is performed.<br><br>
| viewAssertion| <br><br>responsible for performing assertions on a [View](https://developer.android.com/reference/kotlin/android/view/View.html) element.<br><br>
  
  
Content  
open override fun [intercept](intercept.md)(viewAssertion: ViewAssertion, view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, exception: NoMatchingViewException?)  



