[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.logcat](../index.md) / [LogcatImpl](index.md) / [readLogcatRowsViaAdb](./read-logcat-rows-via-adb.md)

# readLogcatRowsViaAdb

`fun readLogcatRowsViaAdb(excludePattern: Regex? = null, includePattern: Regex? = null, buffer: Buffer = Logcat.Buffer.DEFAULT, logcatFilePath: String, readingBlock: (logRow: String) -> Boolean): Boolean`

Required Permissions: READ_EXTERNAL_STORAGE.
Required: Started AdbServer
    1. Download a file "kaspresso/artifacts/desktop.jar"
    2. Start AdbServer =&gt; input in cmd "java jar path_to_file/desktop.jar"

Get logcat dump via ADB and analyze each row.
Logcat reading stops if analyzerBlock returns false on some row

Needed in cases when you want to check not only your application logs (with another PID).
For example: if you need to check Firebase Analytics logs

### Parameters

`excludePattern` - logcat will EXCLUDE rows that match the Regex

`includePattern` - logcat will contains ONLY rows that match the Regex

`buffer` - one of available logcat buffers

`logcatFilePath` - path on device where logcat_dump will located. Must be accessible from app.
For example: Environment.getExternalStorageDirectory().absolutePath

`readingBlock` - lambda with String input and Boolean output. Invokes on
every readed logcat row. Stop reading logcat if lambda returns false. If you needed
all rows of log always return false