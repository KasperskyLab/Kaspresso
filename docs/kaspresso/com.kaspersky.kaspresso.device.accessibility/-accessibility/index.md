[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.accessibility](../index.md) / [Accessibility](./index.md)

# Accessibility

`interface Accessibility`

The interface to work with accessibility.

### Functions

| Name | Summary |
|---|---|
| [disable](disable.md) | `abstract fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Disables accessibility. Available since api 24. |
| [enable](enable.md) | `abstract fun enable(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Enables accessibility. Available since api 24. |

### Inheritors

| Name | Summary |
|---|---|
| [AccessibilityImpl](../-accessibility-impl/index.md) | `class AccessibilityImpl : `[`Accessibility`](./index.md)<br>The implementation of the [Accessibility](./index.md) interface. |
