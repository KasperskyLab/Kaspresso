[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.logcat](../index.md) / [LogcatImpl](./index.md)

# LogcatImpl

`class LogcatImpl : `[`Logcat`](../-logcat/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LogcatImpl(adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`, isNeededToPrintExecutedCommand: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, defaultBufferSize: `[`LogcatBufferSize`](../-logcat-buffer-size/index.md)` = LogcatBufferSize(DEFAULT_BUFFER_SIZE, LogcatBufferSize.Dimension.KILOBYTES), isNeededToDisableChatty: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true)` |

### Functions

| Name | Summary |
|---|---|
| [clear](clear.md) | Clear (flush) the selected buffers and exit. The default buffer set is main, system and crash.`fun clear(buffer: Buffer): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [readLogcatRows](read-logcat-rows.md) | Get logcat dump as list of strings`fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, buffer: Buffer, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>Get logcat dump and analyze each row. Logcat reading stops if analyzerBlock returns false on some row`fun readLogcatRows(excludePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, excludePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, includePattern: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, includePatternIsIgnoreCase: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, buffer: Buffer, rowLimit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?, readingBlock: (logcatRow: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [readLogcatRowsViaAdb](read-logcat-rows-via-adb.md) | Required Permissions: READ_EXTERNAL_STORAGE. Required: Started AdbServer     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"     2. Start AdbServer =&gt; input in cmd "java jar path_to_file/adbserver-desktop.jar"`fun readLogcatRowsViaAdb(excludePattern: `[`Regex`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html)`? = null, includePattern: `[`Regex`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html)`? = null, buffer: Buffer = Logcat.Buffer.DEFAULT, logcatFilePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, readingBlock: (logRow: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setBufferSize](set-buffer-size.md) | Set new logcat buffer size`fun setBufferSize(size: `[`LogcatBufferSize`](../-logcat-buffer-size/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setDefaultBufferSize](set-default-buffer-size.md) | Set default buffer size`fun setDefaultBufferSize(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [DEFAULT_BUFFER_SIZE](-d-e-f-a-u-l-t_-b-u-f-f-e-r_-s-i-z-e.md) | `const val DEFAULT_BUFFER_SIZE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [DEFAULT_LOGCAT_CLEAR_DELAY](-d-e-f-a-u-l-t_-l-o-g-c-a-t_-c-l-e-a-r_-d-e-l-a-y.md) | `const val DEFAULT_LOGCAT_CLEAR_DELAY: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
