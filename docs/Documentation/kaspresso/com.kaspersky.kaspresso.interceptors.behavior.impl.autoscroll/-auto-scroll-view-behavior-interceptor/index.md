//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll](../index.md)/[AutoScrollViewBehaviorInterceptor](index.md)



# AutoScrollViewBehaviorInterceptor  
 [androidJvm] 

The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor/index.md) and [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces. Provides autoscroll on failure functionality for ViewInteraction.perform and ViewInteraction.check calls.

class [AutoScrollViewBehaviorInterceptor](index.md)(**params**: [AutoScrollParams](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md), **logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor/index.md), [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md)<ViewInteraction>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [AutoScrollViewBehaviorInterceptor](-auto-scroll-view-behavior-interceptor.md)|  [androidJvm] fun [AutoScrollViewBehaviorInterceptor](-auto-scroll-view-behavior-interceptor.md)(params: [AutoScrollParams](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md), logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given action invocation with the autoscrolling on failure.<br><br>  <br>Content  <br>open override fun <[T](intercept.md)> [intercept](intercept.md)(interaction: ViewInteraction, action: () -> [T](intercept.md)): [T](intercept.md)  <br><br><br>
| scroll| [androidJvm]  <br>Brief description  <br><br><br>Performs the autoscrolling functionality. Performs scroll and re-invokes the given action.<br><br>  <br>Content  <br>open override fun <T> scroll(interaction: ViewInteraction, action: () -> T, cachedError: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): T  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| withAutoScroll| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action and calls scroll if it fails. Helps in cases when test fails because of the need to scroll to interacted view.<br><br>  <br>Content  <br>open override fun <T> withAutoScroll(interaction: ViewInteraction, action: () -> T): T  <br><br><br>

