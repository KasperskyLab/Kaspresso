[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.logcat](../index.md) / [LogcatImpl](index.md) / [readLogcatRows](./read-logcat-rows.md)

# readLogcatRows

`fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, buffer: `[`Logcat.Buffer`](../-logcat/-buffer/index.md)`, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`

Overrides [Logcat.readLogcatRows](../-logcat/read-logcat-rows.md)

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
[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) with logcat rows

`fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, buffer: `[`Logcat.Buffer`](../-logcat/-buffer/index.md)`, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?, readingBlock: (logcatRow: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Overrides [Logcat.readLogcatRows](../-logcat/read-logcat-rows.md)

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