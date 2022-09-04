//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.logcat](../index.md)/[Logcat](index.md)/[readLogcatRows](read-logcat-rows.md)



# readLogcatRows  
[androidJvm]  
Brief description  


Get logcat dump as list of strings



#### Return  


List<String> with logcat rows



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| buffer| <br><br>one of available logcat buffers<br><br>
| excludePattern| <br><br>logcat will EXCLUDE rows that match the pattern<br><br>
| excludePatternIsIgnoreCase| <br><br>boolean is exclude pattern must ignore string case<br><br>
| includePattern| <br><br>logcat will contains ONLY rows that match the pattern<br><br>
| includePatternIsIgnoreCase| <br><br>boolean is include pattern must ignore string case<br><br>
| rowLimit| <br><br>limiter of logcat output, starts FROM BEGINNING of logcat dump WITH EXTRA ROW of buffer beginning, if null return all rows<br><br>
  
  
Content  
abstract fun [readLogcatRows](read-logcat-rows.md)(excludePattern: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, excludePatternIsIgnoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), includePattern: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, includePatternIsIgnoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), buffer: [Logcat.Buffer](-buffer/index.md), rowLimit: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  


[androidJvm]  
Brief description  


Get logcat dump and analyze each row. Logcat reading stops if analyzerBlock returns false on some row



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| buffer| <br><br>one of available logcat buffers<br><br>
| excludePattern| <br><br>logcat will EXCLUDE rows that match the pattern<br><br>
| excludePatternIsIgnoreCase| <br><br>boolean is exclude pattern must ignore string case<br><br>
| includePattern| <br><br>logcat will contains ONLY rows that match the pattern<br><br>
| includePatternIsIgnoreCase| <br><br>boolean is include pattern must ignore string case<br><br>
| readingBlock| <br><br>lambda with String input and Boolean output. Invokes on every readed logcat row. Stop reading logcat if lambda returns false. If you needed all rows of log always return false<br><br>
| rowLimit| <br><br>limiter of logcat output, starts FROM BEGINNING of logcat dump WITH EXTRA ROW of buffer beginning, if null return all rows<br><br>
  
  
Content  
abstract fun [readLogcatRows](read-logcat-rows.md)(excludePattern: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, excludePatternIsIgnoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), includePattern: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, includePatternIsIgnoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), buffer: [Logcat.Buffer](-buffer/index.md), rowLimit: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?, readingBlock: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -> [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  



