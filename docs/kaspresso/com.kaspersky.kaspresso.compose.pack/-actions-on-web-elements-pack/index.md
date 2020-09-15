//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose.pack](../index.md)/[ActionsOnWebElementsPack](index.md)



# ActionsOnWebElementsPack  
 [androidJvm] 

The builder class for parameters of [com.kaspersky.kaspresso.compose.WebComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-web-compose-provider/compose.md) method.

class [ActionsOnWebElementsPack](index.md)(**webElementBuilder**: WebElementBuilder)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ActionsOnWebElementsPack](-actions-on-web-elements-pack.md)|  [androidJvm] fun [ActionsOnWebElementsPack](-actions-on-web-elements-pack.md)(webElementBuilder: WebElementBuilder)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [orWithElement](or-with-element.md)| [androidJvm]  <br>Brief description  <br><br><br>Builds the lambda to add to actions that invokes the given action on the web element built by webElementBuilder with given locator and value.<br><br>  <br>Content  <br>fun [orWithElement](or-with-element.md)(locator: Locator, value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), action: WebElementBuilder.KWebInteraction.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ComplexComposeBranchBuilder](../../com.kaspersky.kaspresso.compose.pack.branch/-complex-compose-branch-builder/index.md)<WebElementBuilder.KWebInteraction>  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

