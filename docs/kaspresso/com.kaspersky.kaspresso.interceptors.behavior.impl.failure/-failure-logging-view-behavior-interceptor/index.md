//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behavior.impl.failure](../index.md)/[FailureLoggingViewBehaviorInterceptor](index.md)



# FailureLoggingViewBehaviorInterceptor  
 [androidJvm] 



The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor/index.md) and [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for ViewInteraction.perform and ViewInteraction.check calls.



By default, this interceptor is not used in Kaspresso. If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) directly



class [FailureLoggingViewBehaviorInterceptor](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor/index.md), [FailureLoggingProvider](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [FailureLoggingViewBehaviorInterceptor](-failure-logging-view-behavior-interceptor.md)|  [androidJvm] fun [FailureLoggingViewBehaviorInterceptor](-failure-logging-view-behavior-interceptor.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given action invocation with the failure logging.<br><br>  <br>Content  <br>open override fun <[T](intercept.md)> [intercept](intercept.md)(interaction: ViewInteraction, action: () -> [T](intercept.md)): [T](intercept.md)  <br><br><br>
| [logDescriptionAndThrow](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/log-description-and-throw.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the error description got by viewMatcher and throws the error.<br><br>  <br>Content  <br>open override fun [logDescriptionAndThrow](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/log-description-and-throw.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?, viewMatcher: Matcher<[View](https://developer.android.com/reference/kotlin/android/view/View.html)>?)  <br><br><br>
| [logStackTrace](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/log-stack-trace.md)| [androidJvm]  <br>Brief description  <br><br><br>Logs the error's stacktrace.<br><br>  <br>Content  <br>open override fun [logStackTrace](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/log-stack-trace.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [withLoggingOnFailure](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/with-logging-on-failure.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action and logs if it fails.<br><br>  <br>Content  <br>open override fun <T> [withLoggingOnFailure](../../com.kaspersky.kaspresso.failure/-failure-logging-provider/with-logging-on-failure.md)(action: () -> T): T  <br><br><br>

