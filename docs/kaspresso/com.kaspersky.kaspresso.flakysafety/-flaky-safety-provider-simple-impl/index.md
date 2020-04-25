[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [FlakySafetyProviderSimpleImpl](./index.md)

# FlakySafetyProviderSimpleImpl

`class FlakySafetyProviderSimpleImpl : `[`FlakySafetyProvider`](../-flaky-safety-provider/index.md)

The implementation of the [FlakySafetyProvider](../-flaky-safety-provider/index.md) interface.
By default, this implementation is using to struggle with flaky UI libs inside a View

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [FlakySafetyProvider](../-flaky-safety-provider/index.md) interface. By default, this implementation is using to struggle with flaky UI libs inside a View`FlakySafetyProviderSimpleImpl(params: `[`FlakySafetyParams`](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [flakySafely](flaky-safely.md) | Invokes the given [action](flaky-safely.md#com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl$flakySafely(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl.flakySafely.T)))/action) flaky safely.`fun <T> flakySafely(action: () -> T): T`<br>`fun <T> flakySafely(timeoutMs: Long?, intervalMs: Long?, allowedExceptions: Set<Class<out Throwable>>?, failureMessage: String?, action: () -> T): T` |
