//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.core.sections](../index.md)/[TransformSection](index.md)/[transform](transform.md)



# transform  
[androidJvm]  
Brief description  


Can be invoked after [BeforeTestSection](../-before-test-section/index.md) and [InitSection.init](../-init-section/init.md) but before [MainTestSection](../-main-test-section/index.md). It's possible to add multiple transform blocks.



#### Return  


[TransformSection](index.md) to continue building a test.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| actions| <br><br>actions to run.<br><br>
  
  
Content  
abstract fun [transform](transform.md)(actions: [Data](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [TransformSection](index.md)<[Data](index.md)>  



