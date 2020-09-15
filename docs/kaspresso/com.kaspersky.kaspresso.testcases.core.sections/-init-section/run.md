//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.core.sections](../index.md)/[InitSection](index.md)/[run](run.md)



# run  
[androidJvm]  
Brief description  




Runs:

<ol><li>Optional [BeforeTestSection](../-before-test-section/index.md),</li><li>Optional [init](init.md),</li><li>Optional [TransformSection.transform](../-transform-section/transform.md)'s sections (only if [init](init.md) was called before),</li><li>[MainTestSection](../-main-test-section/index.md)'s steps,</li><li>[AfterTestSection](../-after-test-section/index.md). [AfterTestSection](../-after-test-section/index.md) is invoked even if [BeforeTestSection](../-before-test-section/index.md) or [BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md)'s steps failed.</li></ol>



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| steps| <br><br>steps to run.<br><br>
  
  
Content  
abstract fun [run](run.md)(steps: [TestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)<[Data](index.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  



