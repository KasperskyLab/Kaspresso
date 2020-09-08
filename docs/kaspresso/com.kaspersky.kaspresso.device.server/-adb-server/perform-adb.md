//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.server](../index.md)/[AdbServer](index.md)/[performAdb](perform-adb.md)



# performAdb  
[androidJvm]  
Brief description  




Performs adb commands blocking current thread. Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown



Required Permissions: INTERNET.





#### Return  


list of answers of commands' execution



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| commands| <br><br>commands to execute.<br><br>
  
  
Content  
abstract fun [performAdb](perform-adb.md)(vararg commands: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<Out [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  



