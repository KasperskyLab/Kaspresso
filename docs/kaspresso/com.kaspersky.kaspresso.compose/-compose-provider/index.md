//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose](../index.md)/[ComposeProvider](index.md)



# ComposeProvider  
 [androidJvm] 

The interface to provide composing actions and assertions functionality.

interface [ComposeProvider](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [compose](compose.md)| [androidJvm]  <br>Brief description  <br><br><br>Composes a block of actions with their views to invoke on in one composite action that succeeds if at least one of it's parts succeeds. Please, be aware of or sections are executing flakySafely!<br><br>  <br>Content  <br>abstract fun [compose](compose.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, block: [ActionsOnElementsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-on-elements-pack/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Composes a block of actions on the given view of type [Type](compose.md) in one composite action that succeeds if at least one of it's parts succeeds. Please, be aware of or sections are executing flakySafely!<br><br>  <br>Content  <br>abstract fun <[Type](compose.md) : BaseActions, BaseAssertions, Interceptable<ViewInteraction, ViewAssertion, ViewAction>> [Type](compose.md).[compose](compose.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, block: [ActionsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)<[Type](compose.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br>abstract fun <[Type](compose.md) : UiBaseActions, UiBaseAssertions, UiInterceptable<UiObjectInteraction, UiOperation<UiObject2>, UiOperation<UiObject2>>> [Type](compose.md).[compose](compose.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, block: [ActionsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)<[Type](compose.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [unsafeCompose](unsafe-compose.md)| [androidJvm]  <br>Brief description  <br><br><br>Composes a block of actions with their views to invoke on in one composite action that succeeds if at least one of it's parts succeeds. Please, be aware of or sections are executing without flakySafely mechanism     even though there may be flakySafely interceptors in your Kaspresso settings!<br><br>  <br>Content  <br>abstract fun [unsafeCompose](unsafe-compose.md)(block: [ActionsOnElementsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-on-elements-pack/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br>abstract fun <[Type](unsafe-compose.md) : BaseActions, BaseAssertions, Interceptable<ViewInteraction, ViewAssertion, ViewAction>> [Type](unsafe-compose.md).[unsafeCompose](unsafe-compose.md)(block: [ActionsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)<[Type](unsafe-compose.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br>abstract fun <[Type](unsafe-compose.md) : UiBaseActions, UiBaseAssertions, UiInterceptable<UiObjectInteraction, UiOperation<UiObject2>, UiOperation<UiObject2>>> [Type](unsafe-compose.md).[unsafeCompose](unsafe-compose.md)(block: [ActionsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)<[Type](unsafe-compose.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [ComposeProviderImpl](../-compose-provider-impl/index.md)
| [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md)

