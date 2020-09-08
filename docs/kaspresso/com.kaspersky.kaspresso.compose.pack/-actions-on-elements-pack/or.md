//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose.pack](../index.md)/[ActionsOnElementsPack](index.md)/[or](or.md)



# or  
[androidJvm]  
Brief description  


Adds the element of type [Type](or.md) and the action to complexComposeBranchBuilders and action for future composing where [Type](or.md) is bounding by KBaseView (Kakao)



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>actions or assertions on the interacted view.<br><br>
| element| <br><br>the interacted view.<br><br>
  
  
Content  
fun <[Type](or.md) : BaseActions, BaseAssertions, Interceptable<ViewInteraction, ViewAssertion, ViewAction>> [or](or.md)(element: [Type](or.md), action: [Type](or.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ComplexComposeBranchBuilder](../../com.kaspersky.kaspresso.compose.pack.branch/-complex-compose-branch-builder/index.md)<[Type](or.md)>  


[androidJvm]  
Brief description  


Adds the element of type [Type](or.md) and the action to complexComposeBranchBuilders and action for future composing where [Type](or.md) is bounding by UiBaseView (Kautomator)



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>actions or assertions on the interacted view.<br><br>
| element| <br><br>the interacted view.<br><br>
  
  
Content  
fun <[Type](or.md) : UiBaseActions, UiBaseAssertions, UiInterceptable<UiObjectInteraction, UiOperation<UiObject2>, UiOperation<UiObject2>>> [or](or.md)(element: [Type](or.md), action: [Type](or.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ComplexComposeBranchBuilder](../../com.kaspersky.kaspresso.compose.pack.branch/-complex-compose-branch-builder/index.md)<[Type](or.md)>  



