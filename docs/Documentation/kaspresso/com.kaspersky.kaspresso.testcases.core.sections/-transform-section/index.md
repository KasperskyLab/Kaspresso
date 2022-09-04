//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.core.sections](../index.md)/[TransformSection](index.md)



# TransformSection  
 [androidJvm] interface [TransformSection](index.md)<[Data](index.md)>   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [run](run.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Runs:<br><br><ol><li>Optional [BeforeTestSection](../-before-test-section/index.md),</li><li>Optional [InitSection.init](../-init-section/init.md),</li><li>Optional [transform](transform.md)'s sections (only if [InitSection.init](../-init-section/init.md) was called before),</li><li>[MainTestSection](../-main-test-section/index.md)'s steps,</li><li>[AfterTestSection](../-after-test-section/index.md). [AfterTestSection](../-after-test-section/index.md) is invoked even if [BeforeTestSection](../-before-test-section/index.md) or [BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md)'s steps failed.</li></ol><br><br>  <br>Content  <br>abstract fun [run](run.md)(steps: [TestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)<[Data](index.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [transform](transform.md)| [androidJvm]  <br>Brief description  <br><br><br>Can be invoked after [BeforeTestSection](../-before-test-section/index.md) and [InitSection.init](../-init-section/init.md) but before [MainTestSection](../-main-test-section/index.md). It's possible to add multiple transform blocks.<br><br>  <br>Content  <br>abstract fun [transform](transform.md)(actions: [Data](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [TransformSection](index.md)<[Data](index.md)>  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [MainTestSection](../-main-test-section/index.md)

