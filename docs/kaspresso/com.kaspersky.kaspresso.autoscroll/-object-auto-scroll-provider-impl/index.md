//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.autoscroll](../index.md)/[ObjectAutoScrollProviderImpl](index.md)



# ObjectAutoScrollProviderImpl  
 [androidJvm] 

The implementation of the [AutoScrollProvider](../-auto-scroll-provider/index.md) interface for UiObjectInteraction

class [ObjectAutoScrollProviderImpl](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), **autoScrollParams**: [AutoScrollParams](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md)) : [AutoScrollProvider](../-auto-scroll-provider/index.md)<UiObjectInteraction>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ObjectAutoScrollProviderImpl](-object-auto-scroll-provider-impl.md)|  [androidJvm] fun [ObjectAutoScrollProviderImpl](-object-auto-scroll-provider-impl.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), autoScrollParams: [AutoScrollParams](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [scroll](scroll.md)| [androidJvm]  <br>Brief description  <br><br><br>Performs the autoscrolling functionality. Performs scroll and re-invokes the given action.<br><br>  <br>Content  <br>open override fun <[T](scroll.md)> [scroll](scroll.md)(interaction: UiObjectInteraction, action: () -> [T](scroll.md), cachedError: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [T](scroll.md)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [withAutoScroll](with-auto-scroll.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the need to scroll to interacted view.<br><br>  <br>Content  <br>open override fun <[T](with-auto-scroll.md)> [withAutoScroll](with-auto-scroll.md)(interaction: UiObjectInteraction, action: () -> [T](with-auto-scroll.md)): [T](with-auto-scroll.md)  <br><br><br>

