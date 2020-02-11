[kautomator](../../index.md) / [com.kaspersky.components.kautomator.screen](../index.md) / [UiScreen](./index.md)

# UiScreen

`abstract class UiScreen<out T : `[`UiScreen`](./index.md)`<`[`T`](index.md#T)`>> : `[`UiScreenActions`](../-ui-screen-actions/index.md)

Container class for UiAutomator elements.

This class groups UI elements and grants access to basic actions,
such as pressBack

### Parameters

`T` - type of your screen, done to enable invoke() for its children

**See Also**

[UiScreenActions](../-ui-screen-actions/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UiScreen()`<br>Container class for UiAutomator elements. |

### Properties

| Name | Summary |
|---|---|
| [packageName](package-name.md) | `abstract val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [view](view.md) | `open val view: `[`UiDeviceInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md)<br>UiDeviceDelegate on which all actions are performed |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun intercept(configurator: `[`UiInterceptor.Configurator`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/-configurator/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the interceptors for the screen. Interceptors will be invoked on all interactions while the screen is active. |
| [invoke](invoke.md) | `operator fun invoke(function: `[`T`](index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |
| [reset](reset.md) | `fun reset(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes the interceptors from the screen. |

### Inherited Functions

| Name | Summary |
|---|---|
| [pressBack](../-ui-screen-actions/press-back.md) | `open fun pressBack(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Simulates a short press on the BACK button. |
