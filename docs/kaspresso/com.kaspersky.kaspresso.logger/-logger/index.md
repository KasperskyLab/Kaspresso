[kaspresso](../../index.md) / [com.kaspersky.kaspresso.logger](../index.md) / [Logger](./index.md)

# Logger

`interface Logger`

The interface for base logging with 3 levels: info, debug and error.

### Functions

| Name | Summary |
|---|---|
| [d](d.md) | Debug level of logging.`abstract fun d(text: String): Unit`<br>Debug level of logging with tag.`abstract fun d(tag: String, text: String): Unit` |
| [e](e.md) | Error level of logging.`abstract fun e(text: String): Unit`<br>Error level of logging with tag.`abstract fun e(tag: String, text: String): Unit` |
| [i](i.md) | Info level of logging.`abstract fun i(text: String): Unit`<br>Info level of logging with tag.`abstract fun i(tag: String, text: String): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiTestLogger](../-ui-test-logger.md) | Base interface for all loggers used in Kaspresso.`interface UiTestLogger : `[`FormattedLogger`](../-formatted-logger/index.md)`, `[`Logger`](./index.md) |
