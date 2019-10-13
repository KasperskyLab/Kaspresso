[kaspresso](../../index.md) / [com.kaspersky.kaspresso.flakysafety](../index.md) / [CheckDuringProviderImpl](index.md) / [checkDuring](./check-during.md)

# checkDuring

`fun <T> checkDuring(action: () -> `[`T`](check-during.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [CheckDuringProvider.checkDuring](../-check-during-provider/check-during.md)

Invokes the given [action](check-during.md#com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl$checkDuring(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl.checkDuring.T)))/action) during set timeout.

### Parameters

`action` - the action to invoke.

### Exceptions

`Throwable` - if any of attempts failed.

**Return**
the [action](check-during.md#com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl$checkDuring(kotlin.Function0((com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl.checkDuring.T)))/action) invocation result.

`fun <T> checkDuring(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?, failureMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, action: () -> `[`T`](check-during.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [CheckDuringProvider.checkDuring](../-check-during-provider/check-during.md)

Invokes the given [action](check-during.md#com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl$checkDuring(kotlin.Long, kotlin.Long, kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl.checkDuring.T)))/action) during set timeout.

### Parameters

`timeoutMs` - the timeout during which attempts will be made.

`intervalMs` - the interval at which attempts will be made.

`failureMessage` - the message to log on failure.

`action` - the action to invoke.

### Exceptions

`Throwable` - if any of attempts failed.

**Return**
the [action](check-during.md#com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl$checkDuring(kotlin.Long, kotlin.Long, kotlin.String, kotlin.Function0((com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl.checkDuring.T)))/action) invocation result.

