//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose.pack](../index.md)/[ActionsOnElementsPack](index.md)



# ActionsOnElementsPack  
 [androidJvm] 

The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-compose-provider/compose.md) method.

class [ActionsOnElementsPack](index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ActionsOnElementsPack](-actions-on-elements-pack.md)|  [androidJvm] fun [ActionsOnElementsPack](-actions-on-elements-pack.md)()   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [or](or.md)| [androidJvm]  <br>Brief description  <br><br><br>Adds the element of type [Type](or.md) and the action to complexComposeBranchBuilders and action for future composing where [Type](or.md) is bounding by KBaseView (Kakao)<br><br>  <br>Content  <br>fun <[Type](or.md) : BaseActions, BaseAssertions, Interceptable<ViewInteraction, ViewAssertion, ViewAction>> [or](or.md)(element: [Type](or.md), action: [Type](or.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ComplexComposeBranchBuilder](../../com.kaspersky.kaspresso.compose.pack.branch/-complex-compose-branch-builder/index.md)<[Type](or.md)>  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Adds the element of type [Type](or.md) and the action to complexComposeBranchBuilders and action for future composing where [Type](or.md) is bounding by UiBaseView (Kautomator)<br><br>  <br>Content  <br>fun <[Type](or.md) : UiBaseActions, UiBaseAssertions, UiInterceptable<UiObjectInteraction, UiOperation<UiObject2>, UiOperation<UiObject2>>> [or](or.md)(element: [Type](or.md), action: [Type](or.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ComplexComposeBranchBuilder](../../com.kaspersky.kaspresso.compose.pack.branch/-complex-compose-branch-builder/index.md)<[Type](or.md)>  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

