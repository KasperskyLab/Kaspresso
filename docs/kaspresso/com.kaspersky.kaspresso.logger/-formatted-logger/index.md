[kaspresso](../../index.md) / [com.kaspersky.kaspresso.logger](../index.md) / [FormattedLogger](./index.md)

# FormattedLogger

`interface FormattedLogger`

The interface for formatted logging.

### Functions

| Name | Summary |
|---|---|
| [footer](footer.md) | `abstract fun footer(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws up the text as a header block. |
| [header](header.md) | `abstract fun header(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws up the text as a header block. |
| [line](line.md) | `abstract fun line(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws a line. |
| [section](section.md) | `abstract fun section(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws up the text as a section block. |

### Inheritors

| Name | Summary |
|---|---|
| [UiTestLogger](../-ui-test-logger.md) | `interface UiTestLogger : `[`FormattedLogger`](./index.md)`, `[`Logger`](../-logger/index.md)<br>Base interface for all loggers used in Kaspresso. |
