//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose](../index.md)/[WebComposeProviderImpl](index.md)/[unsafeCompose](unsafe-compose.md)



# unsafeCompose  
[androidJvm]  
Brief description  


Composes a block of actions with their web views to invoke on in one composite action that succeeds if at least one of it's parts succeeds. Please, be aware of or sections are executing without flakySafely mechanism     even though there may be flakySafely interceptors in your Kaspresso settings!



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| block| <br><br>the actions to compose.<br><br>
  
  
Content  
open override fun WebElementBuilder.[unsafeCompose](unsafe-compose.md)(block: [ActionsOnWebElementsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-on-web-elements-pack/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  
open override fun WebElementBuilder.KWebInteraction.[unsafeCompose](unsafe-compose.md)(webElementBuilder: WebElementBuilder, block: [ActionsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)<WebElementBuilder.KWebInteraction>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  



