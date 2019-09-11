[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.tokakao](../index.md) / [KakaoInterceptor](./index.md)

# KakaoInterceptor

`abstract class KakaoInterceptor<Interaction, Action, Assertion>`

The base class for Kaspresso's implementations of Kakao's interceptors.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `KakaoInterceptor(kaspresso: `[`Kaspresso`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md)`)`<br>The base class for Kaspresso's implementations of Kakao's interceptors. |

### Properties

| Name | Summary |
|---|---|
| [kaspresso](kaspresso.md) | `val kaspresso: `[`Kaspresso`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md) |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `abstract fun interceptCheck(interaction: `[`Interaction`](index.md#Interaction)`, assertion: `[`Assertion`](index.md#Assertion)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [interceptPerform](intercept-perform.md) | `abstract fun interceptPerform(interaction: `[`Interaction`](index.md#Interaction)`, action: `[`Action`](index.md#Action)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
