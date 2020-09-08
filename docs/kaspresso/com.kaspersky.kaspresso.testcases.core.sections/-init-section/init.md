//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.core.sections](../index.md)/[InitSection](index.md)/[init](init.md)



# init  
[androidJvm]  
Brief description  


Can be invoked after [BeforeTestSection](../-before-test-section/index.md). Running to init test data using dsl.



#### Return  


[TransformSection](../-transform-section/index.md) to continue building a test.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| actions| <br><br>actions to be wrapped and invoked before the test.<br><br>
  
  
Content  
abstract fun [init](init.md)(actions: [InitData](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [TransformSection](../-transform-section/index.md)<[Data](index.md)>  



