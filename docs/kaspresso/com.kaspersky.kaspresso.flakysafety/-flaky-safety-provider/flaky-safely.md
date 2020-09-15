//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.flakysafety](../index.md)/[FlakySafetyProvider](index.md)/[flakySafely](flaky-safely.md)



# flakySafely  
[androidJvm]  
Brief description  


Invokes the given action flaky safely.



#### Return  


the action invocation result.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the action to invoke.<br><br>
  
  
Content  
abstract fun <[T](flaky-safely.md)> [flakySafely](flaky-safely.md)(action: () -> [T](flaky-safely.md)): [T](flaky-safely.md)  


[androidJvm]  
Brief description  


Invokes the given action flaky safely.



#### Return  


the action invocation result.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the action to invoke.<br><br>
| allowedExceptions| <br><br>the set of exceptions that allow to continue an attempt of execution.<br><br>
| failureMessage| <br><br>the message to log on failure.<br><br>
| intervalMs| <br><br>the interval at which attempts will be made.<br><br>
| timeoutMs| <br><br>the timeout during which attempts will be made.<br><br>
  
  
Content  
abstract fun <[T](flaky-safely.md)> [flakySafely](flaky-safely.md)(timeoutMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, intervalMs: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, allowedExceptions: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>>?, failureMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, action: () -> [T](flaky-safely.md)): [T](flaky-safely.md)  



