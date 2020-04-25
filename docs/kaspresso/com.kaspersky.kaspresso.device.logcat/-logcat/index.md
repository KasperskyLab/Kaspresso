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
| [clear](clear.md) | Clear (flush) the selected buffers and exit. The default buffer set is main, system and crash.`abstract fun clear(buffer: Buffer = Buffer.DEFAULT): Unit` |
| [readLogcatRows](read-logcat-rows.md) | Get logcat dump as list of strings`abstract fun readLogcatRows(excludePattern: String? = null, excludePatternIsIgnoreCase: Boolean = false, includePattern: String? = null, includePatternIsIgnoreCase: Boolean = false, buffer: Buffer = Buffer.DEFAULT, rowLimit: Int? = null): List<String>`<br>Get logcat dump and analyze each row. Logcat reading stops if analyzerBlock returns false on some row`abstract fun readLogcatRows(excludePattern: String? = null, excludePatternIsIgnoreCase: Boolean = false, includePattern: String? = null, includePatternIsIgnoreCase: Boolean = false, buffer: Buffer = Buffer.DEFAULT, rowLimit: Int? = null, readingBlock: (logcatRow: String) -> Boolean): Boolean` |
| [setBufferSize](set-buffer-size.md) | Set new logcat buffer size`abstract fun setBufferSize(size: `[`LogcatBufferSize`](../-logcat-buffer-size/index.md)`): Unit` |
| [setDefaultBufferSize](set-default-buffer-size.md) | Set default logcat buffer size`abstract fun setDefaultBufferSize(): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [LogcatImpl](../-logcat-impl/index.md) | `class LogcatImpl : `[`Logcat`](./index.md) |
