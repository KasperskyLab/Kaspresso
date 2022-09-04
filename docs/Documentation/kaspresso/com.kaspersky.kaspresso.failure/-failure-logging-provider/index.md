//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.failure](../index.md)/[FailureLoggingProvider](index.md)



# FailureLoggingProvider  
 [androidJvm] 

An interface to provide the logging failures functionality.

interface [FailureLoggingProvider](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [logDescriptionAndThrow](log-description-and-throw.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the error description got by viewMatcher and throws the error.<br><br>  <br>Content  <br>abstract fun [logDescriptionAndThrow](log-description-and-throw.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?, viewMatcher: Matcher<[View](https://developer.android.com/reference/kotlin/android/view/View.html)>?)  <br><br><br>
| [logStackTrace](log-stack-trace.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the error's stacktrace.<br><br>  <br>Content  <br>abstract fun [logStackTrace](log-stack-trace.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [withLoggingOnFailure](with-logging-on-failure.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action and logs if it fails.<br><br>  <br>Content  <br>abstract fun <[T](with-logging-on-failure.md)> [withLoggingOnFailure](with-logging-on-failure.md)(action: () -> [T](with-logging-on-failure.md)): [T](with-logging-on-failure.md)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [FailureLoggingProviderImpl](../-failure-logging-provider-impl/index.md)
| [LoggingFailureHandler](../-logging-failure-handler/index.md)
| [FailureLoggingDataBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.failure/-failure-logging-data-behavior-interceptor/index.md)
| [FailureLoggingViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.failure/-failure-logging-view-behavior-interceptor/index.md)
| [FailureLoggingWebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior.impl.failure/-failure-logging-web-behavior-interceptor/index.md)
| [FailureLoggingDeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure/-failure-logging-device-behavior-interceptor/index.md)
| [FailureLoggingObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure/-failure-logging-object-behavior-interceptor/index.md)

