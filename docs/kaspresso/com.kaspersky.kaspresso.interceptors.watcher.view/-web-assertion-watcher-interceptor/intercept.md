//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.watcher.view](../index.md)/[WebAssertionWatcherInterceptor](index.md)/[intercept](intercept.md)



# intercept  
[androidJvm]  
Brief description  


Called to do some stuff before androidx.test.espresso.web.assertion.WebAssertion.checkResult is actually called.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| result| <br><br>a result of androidx.test.espresso.web.assertion.WebAssertion.<br><br>
| view| <br><br>an Android View, on which androidx.test.espresso.web.assertion.WebAssertion is performed.<br><br>
| webAssertionProxy| <br><br>a proxy-wrapper of androidx.test.espresso.web.assertion.WebAssertion for     interceptors calls.<br><br>
  
  
Content  
abstract fun [intercept](intercept.md)(webAssertionProxy: [WebAssertionProxy](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)<*>, view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, result: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html))  



