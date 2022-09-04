//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll](../index.md)/[AutoScrollWebBehaviorInterceptor](index.md)



# AutoScrollWebBehaviorInterceptor  
 [androidJvm] 

The implementation of [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor/index.md) and [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces. Provides autoscroll on failure functionality for Web.WebInteraction.perform and Web.WebInteraction.check calls.

class [AutoScrollWebBehaviorInterceptor](index.md)(**params**: [AutoScrollParams](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md), **logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [WebBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor/index.md), [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md)<Web.WebInteraction<*>>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [AutoScrollWebBehaviorInterceptor](-auto-scroll-web-behavior-interceptor.md)|  [androidJvm] fun [AutoScrollWebBehaviorInterceptor](-auto-scroll-web-behavior-interceptor.md)(params: [AutoScrollParams](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md), logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given action invocation with the autoscrolling on failure.<br><br>  <br>Content  <br>open override fun <[T](intercept.md)> [intercept](intercept.md)(interaction: Web.WebInteraction<*>, action: () -> [T](intercept.md)): [T](intercept.md)  <br><br><br>
| scroll| [androidJvm]  <br>Brief description  <br><br><br>Performs the autoscrolling functionality. Performs scroll and re-invokes the given action.<br><br>  <br>Content  <br>open override fun <T> scroll(interaction: Web.WebInteraction<*>, action: () -> T, cachedError: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): T  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| withAutoScroll| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action and calls scroll if it fails. Helps in cases when test fails because of the need to scroll to interacted view.<br><br>  <br>Content  <br>open override fun <T> withAutoScroll(interaction: Web.WebInteraction<*>, action: () -> T): T  <br><br><br>

