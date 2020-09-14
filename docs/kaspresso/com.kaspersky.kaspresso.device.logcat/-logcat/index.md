//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.logcat](../index.md)/[Logcat](index.md)



# Logcat  
 [androidJvm] 

The interface to work with logcat.

interface [Logcat](index.md)   


## Types  
  
|  Name|  Summary| 
|---|---|
| [Buffer](-buffer/index.md)| [androidJvm]  <br>Content  <br>enum [Buffer](-buffer/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)<[Logcat.Buffer](-buffer/index.md)>   <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [clear](clear.md)| [androidJvm]  <br>Brief description  <br><br><br>Clear (flush) the selected buffers and exit. The default buffer set is main, system and crash.<br><br>  <br>Content  <br>abstract fun [clear](clear.md)(buffer: [Logcat.Buffer](-buffer/index.md))  <br><br><br>
| [disableChatty](disable-chatty.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>NOT WORKING ON ANDROID 8+<br><br><br><br>The problem: Android OS has a special introduced mechanism to filter and collapse of some bunches of logs produced by applications. The name of the such mechanism is Chatty. Chatty turns on when an application writes a lot of logs. The goal of Logcat interface is to analyze all logs. But Chatty prevents achievement of the mentioned goal. That's why, there is this method to disable Chatty. Please, call the method in "before" section of a test.<br><br><br><br>  <br>Content  <br>abstract fun [disableChatty](disable-chatty.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [readLogcatRows](read-logcat-rows.md)| [androidJvm]  <br>Brief description  <br><br><br>Get logcat dump as list of strings<br><br>  <br>Content  <br>abstract fun [readLogcatRows](read-logcat-rows.md)(excludePattern: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, excludePatternIsIgnoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), includePattern: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, includePatternIsIgnoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), buffer: [Logcat.Buffer](-buffer/index.md), rowLimit: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Get logcat dump and analyze each row. Logcat reading stops if analyzerBlock returns false on some row<br><br>  <br>Content  <br>abstract fun [readLogcatRows](read-logcat-rows.md)(excludePattern: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, excludePatternIsIgnoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), includePattern: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, includePatternIsIgnoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), buffer: [Logcat.Buffer](-buffer/index.md), rowLimit: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?, readingBlock: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -> [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [setBufferSize](set-buffer-size.md)| [androidJvm]  <br>Brief description  <br><br><br>Set new logcat buffer size<br><br>  <br>Content  <br>abstract fun [setBufferSize](set-buffer-size.md)(size: [LogcatBufferSize](../-logcat-buffer-size/index.md))  <br><br><br>
| [setDefaultBufferSize](set-default-buffer-size.md)| [androidJvm]  <br>Brief description  <br><br><br>Set default logcat buffer size<br><br>  <br>Content  <br>abstract fun [setDefaultBufferSize](set-default-buffer-size.md)()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [LogcatImpl](../-logcat-impl/index.md)

