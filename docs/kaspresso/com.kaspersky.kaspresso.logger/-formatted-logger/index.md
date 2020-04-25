[kaspresso](../../index.md) / [com.kaspersky.kaspresso.logger](../index.md) / [FormattedLogger](./index.md)

# FormattedLogger

`interface FormattedLogger`

The interface for formatted logging.

### Functions

| Name | Summary |
|---|---|
| [footer](footer.md) | Draws up the text as a header block.`abstract fun footer(text: String): Unit` |
| [header](header.md) | Draws up the text as a header block.`abstract fun header(text: String): Unit` |
| [line](line.md) | Draws a line.`abstract fun line(): Unit` |
| [section](section.md) | Draws up the text as a section block.`abstract fun section(text: String): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [UiTestLogger](../-ui-test-logger.md) | Base interface for all loggers used in Kaspresso.`interface UiTestLogger : `[`FormattedLogger`](./index.md)`, `[`Logger`](../-logger/index.md) |
