//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll](../index.md)/[AutoScrollObjectBehaviorInterceptor](index.md)



# AutoScrollObjectBehaviorInterceptor  
 [androidJvm] 

The implementation of [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor/index.md) and [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces. Provides autoscroll on failure functionality for UiObjectInteraction.perform and UiObjectInteraction.check calls.

class [AutoScrollObjectBehaviorInterceptor](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), **autoScrollParams**: [AutoScrollParams](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md)) : [ObjectBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor/index.md), [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md)<UiObjectInteraction>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [AutoScrollObjectBehaviorInterceptor](-auto-scroll-object-behavior-interceptor.md)|  [androidJvm] fun [AutoScrollObjectBehaviorInterceptor](-auto-scroll-object-behavior-interceptor.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), autoScrollParams: [AutoScrollParams](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [interceptCheck](intercept-check.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given assertion invocation with the autoscrolling on failure.<br><br>  <br>Content  <br>open override fun <[T](intercept-check.md)> [interceptCheck](intercept-check.md)(interaction: UiObjectInteraction, assertion: UiOperation<UiObject2>, activity: () -> [T](intercept-check.md)): [T](intercept-check.md)  <br><br><br>
| [interceptPerform](intercept-perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps the given action invocation with the autoscrolling on failure.<br><br>  <br>Content  <br>open override fun <[T](intercept-perform.md)> [interceptPerform](intercept-perform.md)(interaction: UiObjectInteraction, action: UiOperation<UiObject2>, activity: () -> [T](intercept-perform.md)): [T](intercept-perform.md)  <br><br><br>
| scroll| [androidJvm]  <br>Brief description  <br><br><br>Performs the autoscrolling functionality. Performs scroll and re-invokes the given action.<br><br>  <br>Content  <br>open override fun <T> scroll(interaction: UiObjectInteraction, action: () -> T, cachedError: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): T  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| withAutoScroll| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action and calls scroll if it fails. Helps in cases when test fails because of the need to scroll to interacted view.<br><br>  <br>Content  <br>open override fun <T> withAutoScroll(interaction: UiObjectInteraction, action: () -> T): T  <br><br><br>

