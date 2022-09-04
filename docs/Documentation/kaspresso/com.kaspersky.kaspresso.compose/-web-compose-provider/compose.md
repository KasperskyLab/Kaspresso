//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose](../index.md)/[WebComposeProvider](index.md)/[compose](compose.md)



# compose  
[androidJvm]  
Brief description  


Composes a block of actions with their web views to invoke on in one composite action that succeeds if at least one of it's parts succeeds. Please, be aware of or sections are executing flakySafely!



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| allowedExceptions| <br><br>the set of exceptions that allow to continue an attempt of or sections execution.<br><br>
| block| <br><br>the actions to compose.<br><br>
| intervalMs| <br><br>the interval at which attempts to execute or sections will be made.<br><br>
| timeoutMs| <br><br>the timeout during which attempts to execute or sections will be made.<br><br>
  
  
Content  
abstract fun WebElementBuilder.[compose](compose.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, block: [ActionsOnWebElementsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-on-web-elements-pack/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  
abstract fun WebElementBuilder.KWebInteraction.[compose](compose.md)(webElementBuilder: WebElementBuilder, timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, block: [ActionsPack](../../com.kaspersky.kaspresso.compose.pack/-actions-pack/index.md)<WebElementBuilder.KWebInteraction>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  



