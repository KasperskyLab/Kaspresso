[kaspresso](../index.md) / [com.kaspersky.kaspresso.extensions.kakaoext](index.md) / [compositeCheck](./composite-check.md)

# compositeCheck

`fun <T : BaseAssertions> `[`T`](composite-check.md#T)`.compositeCheck(vararg asserts: `[`T`](composite-check.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

A multiple assertion.
If at least one of assertions passes, [compositeCheck](./composite-check.md) is passed.
If no one of assertions passes, [compositeCheck](./composite-check.md) is failed.

