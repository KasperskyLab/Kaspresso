//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.core.sections](../index.md)/[BeforeTestSection](index.md)



# BeforeTestSection  
 [androidJvm] 

The representation of a set of actions to be invoked before the test.

class [BeforeTestSection](index.md)<[InitData](index.md), [Data](index.md)>   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [beforeTest](before-test.md)| [androidJvm]  <br>Brief description  <br><br><br>Wraps actions in a lambda, that will invoke these actions and make screenshot if actions will fail when it will be invoked itself, and sets this lambda as the TestBody.beforeTestActions.<br><br>  <br>Content  <br>fun [beforeTest](before-test.md)(actions: [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [AfterTestSection](../-after-test-section/index.md)<[InitData](index.md), [Data](index.md)>  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

