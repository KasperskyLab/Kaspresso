//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.logcat](../index.md)/[LogcatImpl](index.md)/[readLogcatRowsViaAdb](read-logcat-rows-via-adb.md)



# readLogcatRowsViaAdb  
[androidJvm]  
Brief description  




Required Permissions: READ_EXTERNAL_STORAGE. Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"



Get logcat dump via ADB and analyze each row. Logcat reading stops if analyzerBlock returns false on some row



Needed in cases when you want to check not only your application logs (with another PID). For example: if you need to check Firebase Analytics logs





## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| buffer| <br><br>one of available logcat buffers<br><br>
| excludePattern| <br><br>logcat will EXCLUDE rows that match the Regex<br><br>
| includePattern| <br><br>logcat will contains ONLY rows that match the Regex<br><br>
| logcatFilePath| <br><br>path on device where logcat_dump will located. Must be accessible from app. For example: Environment.getExternalStorageDirectory().absolutePath<br><br>
| readingBlock| <br><br>lambda with String input and Boolean output. Invokes on every readed logcat row. Stop reading logcat if lambda returns false. If you needed all rows of log always return false<br><br>
  
  
Content  
fun [readLogcatRowsViaAdb](read-logcat-rows-via-adb.md)(excludePattern: [Regex](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html)?, includePattern: [Regex](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html)?, buffer: [Logcat.Buffer](../-logcat/-buffer/index.md), logcatFilePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), readingBlock: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -> [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  



