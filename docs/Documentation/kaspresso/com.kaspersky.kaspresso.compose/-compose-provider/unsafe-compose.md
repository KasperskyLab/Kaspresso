//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose](../index.md)/[ComposeProvider](index.md)/[unsafeCompose](unsafe-compose.md)



# unsafeCompose  
[androidJvm]  
Brief description  


Composes a block of actions with their views to invoke on in one composite action that succeeds if at least one of it's parts succeeds. Please, be aware of or sections are executing without flakySafely mechanism     even though there may be flakySafely interceptors in your Kaspresso settings!



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| block| <br><br>the actions to compose.<br><br>
  
  
Content  
abstract fun [unsafeCompose](unsafe-compose.md)(block: [ActionsOnElementsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-on-elements-pack/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  
abstract fun <[Type](unsafe-compose.md) : BaseActions, BaseAssertions, Interceptable<ViewInteraction, ViewAssertion, ViewAction>> [Type](unsafe-compose.md).[unsafeCompose](unsafe-compose.md)(block: [ActionsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)<[Type](unsafe-compose.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  
abstract fun <[Type](unsafe-compose.md) : UiBaseActions, UiBaseAssertions, UiInterceptable<UiObjectInteraction, UiOperation<UiObject2>, UiOperation<UiObject2>>> [Type](unsafe-compose.md).[unsafeCompose](unsafe-compose.md)(block: [ActionsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)<[Type](unsafe-compose.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  



