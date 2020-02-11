[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.logcat](../index.md) / [Logcat](index.md) / [readLogcatRows](./read-logcat-rows.md)

# readLogcatRows

`abstract fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, buffer: `[`Logcat.Buffer`](-buffer/index.md)` = Buffer.DEFAULT, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`

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

`abstract fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, buffer: `[`Logcat.Buffer`](-buffer/index.md)` = Buffer.DEFAULT, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null, readingBlock: (logcatRow: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

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