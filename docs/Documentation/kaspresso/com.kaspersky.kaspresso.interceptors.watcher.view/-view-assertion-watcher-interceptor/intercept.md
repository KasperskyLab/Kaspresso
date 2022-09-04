//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view](../index.md)/[ViewAssertionWatcherInterceptor](index.md)/[intercept](intercept.md)



# intercept  
[androidJvm]  
Brief description  


Called to do some stuff before androidx.test.espresso.ViewAssertion.check is actually called.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| exception| <br><br>indicates that a given matcher did not match any elements in the view hierarchy.<br><br>
| view| <br><br>an Android [View](https://developer.android.com/reference/kotlin/android/view/View.html), on which viewAssertion is performed.<br><br>
| viewAssertion| <br><br>responsible for performing assertions on a [View](https://developer.android.com/reference/kotlin/android/view/View.html) element.<br><br>
  
  
Content  
abstract fun [intercept](intercept.md)(viewAssertion: ViewAssertion, view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, exception: NoMatchingViewException?)  



