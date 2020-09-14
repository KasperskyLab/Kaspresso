//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.compose.pack.branch](../index.md)/[ComplexComposeBranch](index.md)



# ComplexComposeBranch  
 [androidJvm] 

The base data class using in compose

data class [ComplexComposeBranch](index.md)<[ElementType](index.md)>(**element**: [ElementType](index.md), **check**: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), **postAction**: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ComplexComposeBranch](-complex-compose-branch.md)|  [androidJvm] fun <[ElementType](index.md)> [ComplexComposeBranch](-complex-compose-branch.md)(element: [ElementType](index.md), check: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), postAction: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [component1](component1.md)| [androidJvm]  <br>Content  <br>operator fun [component1](component1.md)(): [ElementType](index.md)  <br><br><br>
| [component2](component2.md)| [androidJvm]  <br>Content  <br>operator fun [component2](component2.md)(): () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)  <br><br><br>
| [component3](component3.md)| [androidJvm]  <br>Content  <br>operator fun [component3](component3.md)(): () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?  <br><br><br>
| [copy](copy.md)| [androidJvm]  <br>Content  <br>fun [copy](copy.md)(element: [ElementType](index.md), check: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), postAction: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?): [ComplexComposeBranch](index.md)<[ElementType](index.md)>  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [check](index.md#com.kaspersky.kaspresso.compose.pack.branch/ComplexComposeBranch/check/#/PointingToDeclaration/)|  [androidJvm] val [check](index.md#com.kaspersky.kaspresso.compose.pack.branch/ComplexComposeBranch/check/#/PointingToDeclaration/): () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)   <br>
| [element](index.md#com.kaspersky.kaspresso.compose.pack.branch/ComplexComposeBranch/element/#/PointingToDeclaration/)|  [androidJvm] val [element](index.md#com.kaspersky.kaspresso.compose.pack.branch/ComplexComposeBranch/element/#/PointingToDeclaration/): [ElementType](index.md)   <br>
| [postAction](index.md#com.kaspersky.kaspresso.compose.pack.branch/ComplexComposeBranch/postAction/#/PointingToDeclaration/)|  [androidJvm] val [postAction](index.md#com.kaspersky.kaspresso.compose.pack.branch/ComplexComposeBranch/postAction/#/PointingToDeclaration/): () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?   <br>

