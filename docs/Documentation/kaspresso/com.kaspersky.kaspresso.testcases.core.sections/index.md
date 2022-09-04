//[kaspresso](../index.md)/[com.kaspersky.kaspresso.testcases.core.sections](index.md)



# Package com.kaspersky.kaspresso.testcases.core.sections  


## Types  
  
|  Name|  Summary| 
|---|---|
| [AfterTestSection](-after-test-section/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The representation of a set of actions to invoke after the test.<br><br>  <br>Content  <br>class [AfterTestSection](-after-test-section/index.md)<[InitData](-after-test-section/index.md), [Data](-after-test-section/index.md)>  <br><br><br>
| [BeforeTestSection](-before-test-section/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The representation of a set of actions to be invoked before the test.<br><br>  <br>Content  <br>class [BeforeTestSection](-before-test-section/index.md)<[InitData](-before-test-section/index.md), [Data](-before-test-section/index.md)>  <br><br><br>
| [InitSection](-init-section/index.md)| [androidJvm]  <br>Content  <br>interface [InitSection](-init-section/index.md)<[InitData](-init-section/index.md), [Data](-init-section/index.md)>  <br><br><br>
| [MainTestSection](-main-test-section/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The representation of an actual test.<br><br>  <br>Content  <br>class [MainTestSection](-main-test-section/index.md)<[InitData](-main-test-section/index.md), [Data](-main-test-section/index.md)> : [InitSection](-init-section/index.md)<[InitData](-main-test-section/index.md), [Data](-main-test-section/index.md)> , [TransformSection](-transform-section/index.md)<[Data](-main-test-section/index.md)>   <br><br><br>
| [TransformSection](-transform-section/index.md)| [androidJvm]  <br>Content  <br>interface [TransformSection](-transform-section/index.md)<[Data](-transform-section/index.md)>  <br><br><br>

