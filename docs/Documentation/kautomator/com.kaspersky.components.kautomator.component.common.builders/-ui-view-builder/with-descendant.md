//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.component.common.builders](../index.md)/[UiViewBuilder](index.md)/[withDescendant](with-descendant.md)



# withDescendant  
[androidJvm]  
Brief description  


Matches the view which has descendant of given matcher



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| function| <br><br>ViewBuilder which will result in descendant matcher<br><br>
  
  
Content  
fun [withDescendant](with-descendant.md)(function: [UiViewBuilder](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  


[androidJvm]  
Brief description  


Matches the view which has descendant of given matcher with the maximum depth under the element to search the descendant



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| function| <br><br>ViewBuilder which will result in descendant matcher<br><br>
| maxDepth| <br><br>The maximum depth under the element to search the descendant<br><br>
  
  
Content  
fun [withDescendant](with-descendant.md)(maxDepth: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), function: [UiViewBuilder](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  



