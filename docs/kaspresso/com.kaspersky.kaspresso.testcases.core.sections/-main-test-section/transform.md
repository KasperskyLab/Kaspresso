//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.core.sections](../index.md)/[MainTestSection](index.md)/[transform](transform.md)



# transform  
[androidJvm]  
Brief description  




Can be invoked after [BeforeTestSection](../-before-test-section/index.md) and [init](init.md) but before [MainTestSection](index.md).



It's possible to add multiple transform blocks.





#### Return  


[TransformSection](../-transform-section/index.md) to continue building a test.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| actions| <br><br>actions to run.<br><br>
  
  
Content  
open override fun [transform](transform.md)(actions: [Data](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [TransformSection](../-transform-section/index.md)<[Data](index.md)>  



