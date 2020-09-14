//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.proxy](../index.md)/[ViewAssertionProxy](index.md)/[check](check.md)



# check  
[androidJvm]  
Brief description  


Calls watcher interceptors before ViewAssertion.check on wrapped viewAssertion is called.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| noViewFoundException| <br><br>an exception detailing why the view could not be found or null if     the view was found.<br><br>
| view| <br><br>the view, if one was found during the view interaction or null if it was not (which     may be an acceptable option for an assertion).<br><br>
  
  
Content  
open override fun [check](check.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, noViewFoundException: NoMatchingViewException?)  



