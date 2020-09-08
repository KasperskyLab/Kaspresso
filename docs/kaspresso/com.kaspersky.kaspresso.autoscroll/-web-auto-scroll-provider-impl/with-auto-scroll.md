//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.autoscroll](../index.md)/[WebAutoScrollProviderImpl](index.md)/[withAutoScroll](with-auto-scroll.md)



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
| interaction| <br><br>the instance of Web.WebInteraction interface to perform actions and assertions.<br><br>
  
  
Content  
open override fun <[T](with-auto-scroll.md)> [withAutoScroll](with-auto-scroll.md)(interaction: Web.WebInteraction<*>, action: () -> [T](with-auto-scroll.md)): [T](with-auto-scroll.md)  



