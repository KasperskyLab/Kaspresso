[kaspresso](../index.md) / [com.kaspersky.kaspresso.logger](index.md) / [UiTestLogger](./-ui-test-logger.md)

# UiTestLogger

`interface UiTestLogger : `[`FormattedLogger`](-formatted-logger/index.md)`, `[`Logger`](-logger/index.md)

Base interface for all loggers used in Kaspresso.

### Inherited Functions

| Name | Summary |
|---|---|
| [d](-logger/d.md) | `abstract fun d(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Debug level of logging.`abstract fun d(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Debug level of logging with tag. |
| [e](-logger/e.md) | `abstract fun e(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Error level of logging.`abstract fun e(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Error level of logging with tag. |
| [footer](-formatted-logger/footer.md) | `abstract fun footer(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws up the text as a header block. |
| [header](-formatted-logger/header.md) | `abstract fun header(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws up the text as a header block. |
| [i](-logger/i.md) | `abstract fun i(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Info level of logging.`abstract fun i(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Info level of logging with tag. |
| [line](-formatted-logger/line.md) | `abstract fun line(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws a line. |
| [section](-formatted-logger/section.md) | `abstract fun section(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws up the text as a section block. |

### Inheritors

| Name | Summary |
|---|---|
| [UiTestLoggerImpl](-ui-test-logger-impl/index.md) | `class UiTestLoggerImpl : `[`UiTestLogger`](./-ui-test-logger.md)<br>The default implementation of [UiTestLogger](./-ui-test-logger.md) using [android.util.Log](https://developer.android.com/reference/android/util/Log.html). |
