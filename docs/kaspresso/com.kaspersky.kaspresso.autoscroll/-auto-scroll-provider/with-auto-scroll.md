//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.autoscroll](../index.md)/[AutoScrollProvider](index.md)/[withAutoScroll](with-auto-scroll.md)



# withAutoScroll  
[androidJvm]  
Brief description  


Invokes the given action and calls [scroll](scroll.md) if it fails. Helps in cases when test fails because of the need to scroll to interacted view.



#### Return  


the result of action invocation.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the actual action on the interacted view.<br><br>
| interaction| <br><br>the interaction interface to perform actions and assertions.<br><br>
  
  
Content  
abstract fun <[T](with-auto-scroll.md)> [withAutoScroll](with-auto-scroll.md)(interaction: [Interaction](index.md), action: () -> [T](with-auto-scroll.md)): [T](with-auto-scroll.md)  



