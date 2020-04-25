[kaspresso](../../index.md) / [com.kaspersky.kaspresso.logger](../index.md) / [UiTestLoggerImpl](./index.md)

# UiTestLoggerImpl

`class UiTestLoggerImpl : `[`UiTestLogger`](../-ui-test-logger.md)

The default implementation of [UiTestLogger](../-ui-test-logger.md) using [android.util.Log](#).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The default implementation of [UiTestLogger](../-ui-test-logger.md) using [android.util.Log](#).`UiTestLoggerImpl(tag: String)` |

### Functions

| Name | Summary |
|---|---|
| [d](d.md) | Debug level of logging.`fun d(text: String): Unit`<br>Debug level of logging with tag.`fun d(tag: String, text: String): Unit` |
| [e](e.md) | Error level of logging.`fun e(text: String): Unit`<br>Error level of logging with tag.`fun e(tag: String, text: String): Unit` |
| [footer](footer.md) | Draws up info [i](i.md) as header block.`fun footer(text: String): Unit` |
| [header](header.md) | Draws up info [i](i.md) as header block.`fun header(text: String): Unit` |
| [i](i.md) | Info level of logging.`fun i(text: String): Unit`<br>Info level of logging with tag.`fun i(tag: String, text: String): Unit` |
| [line](line.md) | Draws line info.`fun line(): Unit` |
| [section](section.md) | Draws up info [i](i.md) as section block.`fun section(text: String): Unit` |
