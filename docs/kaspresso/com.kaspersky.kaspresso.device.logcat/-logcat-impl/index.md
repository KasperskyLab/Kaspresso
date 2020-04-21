[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.logcat](../index.md) / [LogcatImpl](./index.md)

# LogcatImpl

`class LogcatImpl : `[`Logcat`](../-logcat/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LogcatImpl(isNeededToPrintExecutedCommand: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, defaultBufferSize: `[`LogcatBufferSize`](../-logcat-buffer-size/index.md)` = LogcatBufferSize(DEFAULT_BUFFER_SIZE, LogcatBufferSize.Dimension.KILOBYTES), isNeededToDisableChatty: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true)` |

### Functions

| Name | Summary |
|---|---|
| [clear](clear.md) | `fun clear(buffer: `[`Logcat.Buffer`](../-logcat/-buffer/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear (flush) the selected buffers and exit. The default buffer set is main, system and crash. |
| [readLogcatRows](read-logcat-rows.md) | `fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, buffer: `[`Logcat.Buffer`](../-logcat/-buffer/index.md)`, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Get logcat dump as list of strings`fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, buffer: `[`Logcat.Buffer`](../-logcat/-buffer/index.md)`, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?, readingBlock: (logcatRow: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Get logcat dump and analyze each row. Logcat reading stops if analyzerBlock returns false on some row |
| [setBufferSize](set-buffer-size.md) | `fun setBufferSize(size: `[`LogcatBufferSize`](../-logcat-buffer-size/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set new logcat buffer size |
| [setDefaultBufferSize](set-default-buffer-size.md) | `fun setDefaultBufferSize(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set default buffer size |

### Companion Object Properties

| Name | Summary |
|---|---|
| [DEFAULT_BUFFER_SIZE](-d-e-f-a-u-l-t_-b-u-f-f-e-r_-s-i-z-e.md) | `const val DEFAULT_BUFFER_SIZE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [DEFAULT_LOGCAT_CLEAR_DELAY](-d-e-f-a-u-l-t_-l-o-g-c-a-t_-c-l-e-a-r_-d-e-l-a-y.md) | `const val DEFAULT_LOGCAT_CLEAR_DELAY: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
