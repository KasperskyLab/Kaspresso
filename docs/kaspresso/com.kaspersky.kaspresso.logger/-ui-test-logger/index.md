[kaspresso](../../index.md) / [com.kaspersky.kaspresso.logger](../index.md) / [UiTestLogger](./index.md)

# UiTestLogger

`interface UiTestLogger`

Base interface for all loggers used in Kaspresso.

### Functions

| Name | Summary |
|---|---|
| [d](d.md) | `abstract fun d(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Debug level of logging.`abstract fun d(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Debug level of logging with tag. |
| [e](e.md) | `abstract fun e(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Error level of logging.`abstract fun e(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Error level of logging with tag. |
| [i](i.md) | `abstract fun i(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Info level of logging.`abstract fun i(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Info level of logging with tag. |

### Inheritors

| Name | Summary |
|---|---|
| [KLogger](../-k-logger/index.md) | `object KLogger : `[`UiTestLogger`](./index.md)<br>A singletone to provide access to external logger outside. Implements [UiTestLogger](./index.md) interface and delegates all calls to wrapped implementation of [UiTestLogger](./index.md). |
| [UiTestLoggerImpl](../-ui-test-logger-impl/index.md) | `class UiTestLoggerImpl : `[`UiTestLogger`](./index.md)<br>Default implementation of [UiTestLogger](./index.md) using [android.util.Log](https://developer.android.com/reference/android/util/Log.html). |
