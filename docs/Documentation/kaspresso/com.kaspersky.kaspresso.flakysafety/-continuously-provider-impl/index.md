//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.flakysafety](../index.md)/[ContinuouslyProviderImpl](index.md)



# ContinuouslyProviderImpl  
 [androidJvm] 

The implementation of the [ContinuouslyProvider](../-continuously-provider/index.md) interface.

class [ContinuouslyProviderImpl](index.md)(**kaspresso**: [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md)) : [ContinuouslyProvider](../-continuously-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ContinuouslyProviderImpl](-continuously-provider-impl.md)|  [androidJvm] fun [ContinuouslyProviderImpl](-continuously-provider-impl.md)(kaspresso: [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [continuously](continuously.md)| [androidJvm]  <br>Brief description  <br><br><br>Invokes the given action during set timeout.<br><br>  <br>Content  <br>open override fun <[T](continuously.md)> [continuously](continuously.md)(action: () -> [T](continuously.md)): [T](continuously.md)  <br>open override fun <[T](continuously.md)> [continuously](continuously.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, failureMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, action: () -> [T](continuously.md)): [T](continuously.md)  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

