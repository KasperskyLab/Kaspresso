[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.logcat](../index.md) / [LogcatImpl](index.md) / [readLogcatRows](./read-logcat-rows.md)

# readLogcatRows

`fun readLogcatRows(excludePattern: String?, excludePatternIsIgnoreCase: Boolean, includePattern: String?, includePatternIsIgnoreCase: Boolean, buffer: Buffer, rowLimit: Int?): List<String>`

Get logcat dump as list of strings

### Parameters

`excludePattern` - logcat will EXCLUDE rows that match the pattern

`excludePatternIsIgnoreCase` - boolean is exclude pattern must ignore string case

`includePattern` - logcat will contains ONLY rows that match the pattern

`includePatternIsIgnoreCase` - boolean is include pattern must ignore string case

`buffer` - one of available logcat buffers

`rowLimit` - limiter of logcat output, starts FROM BEGINNING of logcat dump
WITH EXTRA ROW of buffer beginning, if null return all rows

**Return**
[List](#) with logcat rows

`fun readLogcatRows(excludePattern: String?, excludePatternIsIgnoreCase: Boolean, includePattern: String?, includePatternIsIgnoreCase: Boolean, buffer: Buffer, rowLimit: Int?, readingBlock: (logcatRow: String) -> Boolean): Boolean`

Get logcat dump and analyze each row.
Logcat reading stops if analyzerBlock returns false on some row

### Parameters

`excludePattern` - logcat will EXCLUDE rows that match the pattern

`excludePatternIsIgnoreCase` - boolean is exclude pattern must ignore string case

`includePattern` - logcat will contains ONLY rows that match the pattern

`includePatternIsIgnoreCase` - boolean is include pattern must ignore string case

`buffer` - one of available logcat buffers

`rowLimit` - limiter of logcat output, starts FROM BEGINNING of logcat dump
WITH EXTRA ROW of buffer beginning, if null return all rows

`readingBlock` - lambda with String input and Boolean output. Invokes on
every readed logcat row. Stop reading logcat if lambda returns false. If you needed
all rows of log always return false