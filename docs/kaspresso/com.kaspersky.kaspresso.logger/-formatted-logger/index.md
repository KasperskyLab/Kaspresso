[kaspresso](../../index.md) / [com.kaspersky.kaspresso.logger](../index.md) / [FormattedLogger](./index.md)

# FormattedLogger

`interface FormattedLogger`

The interface for formatted logging.

### Functions

| Name | Summary |
|---|---|
| [footer](footer.md) | Draws up the text as a header block.`abstract fun footer(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [header](header.md) | Draws up the text as a header block.`abstract fun header(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [line](line.md) | Draws a line.`abstract fun line(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [section](section.md) | Draws up the text as a section block.`abstract fun section(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [UiTestLogger](../-ui-test-logger.md) | Base interface for all loggers used in Kaspresso.`interface UiTestLogger : `[`FormattedLogger`](./index.md)`, `[`Logger`](../-logger/index.md) |
