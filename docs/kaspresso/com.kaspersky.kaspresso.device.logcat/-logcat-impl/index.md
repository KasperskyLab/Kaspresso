[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.logcat](../index.md) / [LogcatImpl](./index.md)

# LogcatImpl

`class LogcatImpl : `[`Logcat`](../-logcat/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LogcatImpl(adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`, isNeededToPrintExecutedCommand: Boolean = false, defaultBufferSize: `[`LogcatBufferSize`](../-logcat-buffer-size/index.md)` = LogcatBufferSize(DEFAULT_BUFFER_SIZE, LogcatBufferSize.Dimension.KILOBYTES), isNeededToDisableChatty: Boolean = true)` |

### Functions

| Name | Summary |
|---|---|
| [clear](clear.md) | Clear (flush) the selected buffers and exit. The default buffer set is main, system and crash.`fun clear(buffer: Buffer): Unit` |
| [readLogcatRows](read-logcat-rows.md) | Get logcat dump as list of strings`fun readLogcatRows(excludePattern: String?, excludePatternIsIgnoreCase: Boolean, includePattern: String?, includePatternIsIgnoreCase: Boolean, buffer: Buffer, rowLimit: Int?): List<String>`<br>Get logcat dump and analyze each row. Logcat reading stops if analyzerBlock returns false on some row`fun readLogcatRows(excludePattern: String?, excludePatternIsIgnoreCase: Boolean, includePattern: String?, includePatternIsIgnoreCase: Boolean, buffer: Buffer, rowLimit: Int?, readingBlock: (logcatRow: String) -> Boolean): Boolean` |
| [readLogcatRowsViaAdb](read-logcat-rows-via-adb.md) | Required Permissions: READ_EXTERNAL_STORAGE. Required: Started AdbServer     1. Download a file "kaspresso/artifacts/desktop.jar"     2. Start AdbServer =&gt; input in cmd "java jar path_to_file/desktop.jar"`fun readLogcatRowsViaAdb(excludePattern: Regex? = null, includePattern: Regex? = null, buffer: Buffer = Logcat.Buffer.DEFAULT, logcatFilePath: String, readingBlock: (logRow: String) -> Boolean): Boolean` |
| [setBufferSize](set-buffer-size.md) | Set new logcat buffer size`fun setBufferSize(size: `[`LogcatBufferSize`](../-logcat-buffer-size/index.md)`): Unit` |
| [setDefaultBufferSize](set-default-buffer-size.md) | Set default buffer size`fun setDefaultBufferSize(): Unit` |

### Companion Object Properties

| Name | Summary |
|---|---|
| [DEFAULT_BUFFER_SIZE](-d-e-f-a-u-l-t_-b-u-f-f-e-r_-s-i-z-e.md) | `const val DEFAULT_BUFFER_SIZE: Int` |
| [DEFAULT_LOGCAT_CLEAR_DELAY](-d-e-f-a-u-l-t_-l-o-g-c-a-t_-c-l-e-a-r_-d-e-l-a-y.md) | `const val DEFAULT_LOGCAT_CLEAR_DELAY: Long` |
