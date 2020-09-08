//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose.pack](../index.md)/[ActionsOnWebElementsPack](index.md)/[orWithElement](or-with-element.md)



# orWithElement  
[androidJvm]  
Brief description  


Builds the lambda to add to actions that invokes the given action on the web element built by webElementBuilder with given locator and value.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>actions or assertions on the interacted view.<br><br>
| locator| <br><br>the locator type of web view element.<br><br>
| value| <br><br>the value to be searched for in web view.<br><br>
  
  
Content  
fun [orWithElement](or-with-element.md)(locator: Locator, value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), action: WebElementBuilder.KWebInteraction.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ComplexComposeBranchBuilder](../../com.kaspersky.kaspresso.compose.pack.branch/-complex-compose-branch-builder/index.md)<WebElementBuilder.KWebInteraction>  



