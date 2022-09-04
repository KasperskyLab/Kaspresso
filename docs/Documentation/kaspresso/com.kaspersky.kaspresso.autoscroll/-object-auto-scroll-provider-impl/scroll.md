//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.autoscroll](../index.md)/[ObjectAutoScrollProviderImpl](index.md)/[scroll](scroll.md)



# scroll  
[androidJvm]  
Brief description  


Performs the autoscrolling functionality. Performs scroll and re-invokes the given action.



#### Return  


the result of action invocation.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the actual action on the interacted view.<br><br>
| cachedError| <br><br>the error to be thrown if autoscroll would not help.<br><br>
| interaction| <br><br>the instance of UiObjectInteraction interface to perform actions and assertions.<br><br>
  
  
Content  
open override fun <[T](scroll.md)> [scroll](scroll.md)(interaction: UiObjectInteraction, action: () -> [T](scroll.md), cachedError: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [T](scroll.md)  



