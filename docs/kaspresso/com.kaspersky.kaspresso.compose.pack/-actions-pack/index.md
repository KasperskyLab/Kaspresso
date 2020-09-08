//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose.pack](../index.md)/[ActionsPack](index.md)



# ActionsPack  
 [androidJvm] 

The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-compose-provider/compose.md) and [com.kaspersky.kaspresso.compose.WebComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-web-compose-provider/compose.md)] methods.

class [ActionsPack](index.md)<[T](index.md)>(**element**: [T](index.md))   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ActionsPack](-actions-pack.md)|  [androidJvm] fun <[T](index.md)> [ActionsPack](-actions-pack.md)(element: [T](index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [or](or.md)| [androidJvm]  <br>Brief description  <br><br><br>Builds the lambda to add to actions that invokes the given action on the interacted view of type [T](index.md).<br><br>  <br>Content  <br>fun [or](or.md)(action: [T](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ComplexComposeBranchBuilder](../../com.kaspersky.kaspresso.compose.pack.branch/-complex-compose-branch-builder/index.md)<[T](index.md)>  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

