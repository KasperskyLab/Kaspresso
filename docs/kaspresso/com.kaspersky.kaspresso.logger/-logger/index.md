[kaspresso](../../index.md) / [com.kaspersky.kaspresso.logger](../index.md) / [Logger](./index.md)

# Logger

`interface Logger`

The interface for base logging with 3 levels: info, debug and error.

### Functions

| Name | Summary |
|---|---|
| [d](d.md) | `abstract fun d(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Debug level of logging.`abstract fun d(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Debug level of logging with tag. |
| [e](e.md) | `abstract fun e(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Error level of logging.`abstract fun e(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Error level of logging with tag. |
| [i](i.md) | `abstract fun i(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Info level of logging.`abstract fun i(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Info level of logging with tag. |

### Inheritors

| Name | Summary |
|---|---|
| [UiTestLogger](../-ui-test-logger.md) | `interface UiTestLogger : `[`FormattedLogger`](../-formatted-logger/index.md)`, `[`Logger`](./index.md)<br>Base interface for all loggers used in Kaspresso. |
