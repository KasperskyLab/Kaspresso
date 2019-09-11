[kaspresso](../../index.md) / [com.kaspersky.kaspresso.logger](../index.md) / [UiTestLoggerImpl](./index.md)

# UiTestLoggerImpl

`class UiTestLoggerImpl : `[`UiTestLogger`](../-ui-test-logger.md)

The default implementation of [UiTestLogger](../-ui-test-logger.md) using [android.util.Log](https://developer.android.com/reference/android/util/Log.html).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiTestLoggerImpl(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>The default implementation of [UiTestLogger](../-ui-test-logger.md) using [android.util.Log](https://developer.android.com/reference/android/util/Log.html). |

### Functions

| Name | Summary |
|---|---|
| [d](d.md) | `fun d(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Debug level of logging.`fun d(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Debug level of logging with tag. |
| [e](e.md) | `fun e(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Error level of logging.`fun e(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Error level of logging with tag. |
| [footer](footer.md) | `fun footer(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws up info [i](i.md) as header block. |
| [header](header.md) | `fun header(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws up info [i](i.md) as header block. |
| [i](i.md) | `fun i(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Info level of logging.`fun i(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Info level of logging with tag. |
| [line](line.md) | `fun line(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws line info. |
| [section](section.md) | `fun section(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Draws up info [i](i.md) as section block. |
