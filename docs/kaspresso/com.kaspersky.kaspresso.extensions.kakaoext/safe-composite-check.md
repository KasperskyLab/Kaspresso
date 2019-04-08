[kaspresso](../index.md) / [com.kaspersky.kaspresso.extensions.kakaoext](index.md) / [safeCompositeCheck](./safe-composite-check.md)

# safeCompositeCheck

`fun <T : BaseAssertions> `[`T`](safe-composite-check.md#T)`.safeCompositeCheck(vararg asserts: `[`T`](safe-composite-check.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A safe multiple assertion.
If at least one of assertions passes, [safeCompositeCheck](./safe-composite-check.md) is passed and true is returned.
If no one of assertions passes, [safeCompositeCheck](./safe-composite-check.md) is failed and false is returned.

**Return**
true if check is successful, otherwise - false.

