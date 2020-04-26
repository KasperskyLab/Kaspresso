[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.logcat](../index.md) / [Logcat](./index.md)

# Logcat

`interface Logcat`

The interface to work with logcat.

### Types

| Name | Summary |
|---|---|
| [Buffer](-buffer/index.md) | `enum class Buffer` |

### Functions

| Name | Summary |
|---|---|
| [clear](clear.md) | Clear (flush) the selected buffers and exit. The default buffer set is main, system and crash.`abstract fun clear(buffer: Buffer = Buffer.DEFAULT): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [readLogcatRows](read-logcat-rows.md) | Get logcat dump as list of strings`abstract fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, buffer: Buffer = Buffer.DEFAULT, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Get logcat dump and analyze each row. Logcat reading stops if analyzerBlock returns false on some row`abstract fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, buffer: Buffer = Buffer.DEFAULT, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null, readingBlock: (logcatRow: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setBufferSize](set-buffer-size.md) | Set new logcat buffer size`abstract fun setBufferSize(size: `[`LogcatBufferSize`](../-logcat-buffer-size/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setDefaultBufferSize](set-default-buffer-size.md) | Set default logcat buffer size`abstract fun setDefaultBufferSize(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [LogcatImpl](../-logcat-impl/index.md) | `class LogcatImpl : `[`Logcat`](./index.md) |
