[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [FlakySafetyProviderGlobalImpl](./index.md)

# FlakySafetyProviderGlobalImpl

`class FlakySafetyProviderGlobalImpl : `[`FlakySafetyProvider`](../-flaky-safety-provider/index.md)

The implementation of the [FlakySafetyProvider](../-flaky-safety-provider/index.md) interface.

By default, this implementation is using in tests (not inside a View).

Why? Have a glance at the example:

```
someView {
    flakySafety(timeout = 20.sec) {
        isVisible()
    }
}
```

In this case, Kaspresso tries to execute someView.isVisible() for 10 seconds (default timeout for FlakySafety interceptors).
After 10 seconds someView.isVisible() fails, throws an exception and writes info in logs.
This exception is catching by extra `flakySafety` that tries to repeat.

But such log messages make logs dirty and confuse a developer or tester.

Besides, extra `flakySafety` can contain more actions,
that's why there is probability of not having time to execute entire block.
Honestly, we don't recommend to put into flakySafety block more than one concrete action.

All of this pushed us to write special implementation of [FlakySafetyProvider](../-flaky-safety-provider/index.md).

The algorithm:

1. [FlakySafetyProviderGlobalImpl](./index.md) removes all interceptors implementing FlakySafety behavior from Kakao/Kautomator before the action.
2. [FlakySafetyProviderGlobalImpl](./index.md) executes the entire! action inside own FlakySafety.
3. [FlakySafetyProviderGlobalImpl](./index.md) restores all interceptors implementing FlakySafety behavior from Kakao/Kautomator after the action.

Such behavior allows us to observe more predictable log information (a developer will see only one and correct error message in described above example)
and avoids potentially inconsistent execution.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FlakySafetyProviderGlobalImpl(kaspresso: `[`Kaspresso`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md)`)`<br>The implementation of the [FlakySafetyProvider](../-flaky-safety-provider/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [flakySafely](flaky-safely.md) | `fun <T> flakySafely(action: () -> `[`T`](flaky-safely.md#T)`): `[`T`](flaky-safely.md#T)<br>`fun <T> flakySafely(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, allowedExceptions: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>?, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, action: () -> `[`T`](flaky-safely.md#T)`): `[`T`](flaky-safely.md#T)<br>Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl.flakySafely.T)))/action) flaky safely. |
