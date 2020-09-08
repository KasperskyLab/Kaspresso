//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.flakysafety](../index.md)/[ContinuouslyProviderImpl](index.md)/[continuously](continuously.md)



# continuously  
[androidJvm]  
Brief description  


Invokes the given action during set timeout.



#### Return  


the action invocation result.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the action to invoke.<br><br>
  
  
Content  
open override fun <[T](continuously.md)> [continuously](continuously.md)(action: () -> [T](continuously.md)): [T](continuously.md)  


[androidJvm]  
Brief description  


Invokes the given action during set timeout.



#### Return  


the action invocation result.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the action to invoke.<br><br>
| failureMessage| <br><br>the message to log on failure.<br><br>
| intervalMs| <br><br>the interval at which attempts will be made.<br><br>
| timeoutMs| <br><br>the timeout during which attempts will be made.<br><br>
  
  
Content  
open override fun <[T](continuously.md)> [continuously](continuously.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, failureMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, action: () -> [T](continuously.md)): [T](continuously.md)  



