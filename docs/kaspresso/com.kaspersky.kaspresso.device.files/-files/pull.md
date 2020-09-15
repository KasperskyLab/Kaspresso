//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.files](../index.md)/[Files](index.md)/[pull](pull.md)



# pull  
[androidJvm]  
Brief description  




Performs adb pull.



Required Permissions: INTERNET.





## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| devicePath| <br><br>a file path relative to the device directory.<br><br>
| serverPath| <br><br>a path to copy. (If empty - pulls in adbServer directory (folder with file "adbserver-desktop.jar"))<br><br>
  
  
Content  
abstract fun [pull](pull.md)(devicePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), serverPath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  



