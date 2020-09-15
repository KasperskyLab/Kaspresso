//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md)/[LoggingWebAssertionWatcherInterceptor](index.md)/[intercept](intercept.md)



# intercept  
[androidJvm]  
Brief description  


Writes info to compositeLogger.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| result| <br><br>a result of androidx.test.espresso.web.assertion.WebAssertion.<br><br>
| view| <br><br>an Android [android.view.View](https://developer.android.com/reference/kotlin/android/view/View.html), on which androidx.test.espresso.web.assertion.WebAssertion     is performed.<br><br>
| webAssertionProxy| <br><br>a proxy-wrapper of androidx.test.espresso.web.assertion.WebAssertion for     interceptors calls.<br><br>
  
  
Content  
open override fun [intercept](intercept.md)(webAssertionProxy: [WebAssertionProxy](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)<*>, view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, result: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html))  



