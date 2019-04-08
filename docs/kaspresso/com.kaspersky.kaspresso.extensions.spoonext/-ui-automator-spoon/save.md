[kaspresso](../../index.md) / [com.kaspersky.kaspresso.extensions.spoonext](../index.md) / [UiAutomatorSpoon](index.md) / [save](./save.md)

# save

`fun save(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)

Alternative to [.save](#)

### Parameters

`context` - Context used to access files.

`path` - Path to the file you want to save.

**Return**
the copy that was created.

`fun save(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, file: `[`File`](https://developer.android.com/reference/java/io/File.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)

Save a file from this test run. The file will be saved under the current class &amp; method.
The file will be copied to, so make sure all the data you want have been
written to the file before calling save.

### Parameters

`context` - Context used to access files.

`file` - The file to save.

**Return**
the copy that was created.

