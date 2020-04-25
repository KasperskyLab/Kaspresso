[kautomator](../../index.md) / [com.kaspersky.components.kautomator.screen](../index.md) / [UiScreen](./index.md)

# UiScreen

`abstract class UiScreen<out T : `[`UiScreen`](./index.md)`<T>> : `[`UiScreenActions`](../-ui-screen-actions/index.md)

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
| [&lt;init&gt;](-init-.md) | Container class for UiAutomator elements.`UiScreen()` |

### Properties

| Name | Summary |
|---|---|
| [packageName](package-name.md) | `abstract val packageName: String` |
| [view](view.md) | UiDeviceDelegate on which all actions are performed`open val view: `[`UiDeviceInteractionDelegate`](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md) |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Sets the interceptors for the screen. Interceptors will be invoked on all interactions while the screen is active.`fun intercept(configurator: Configurator.() -> Unit): Unit` |
| [invoke](invoke.md) | Operator that allows usage of DSL style`operator fun invoke(function: T.() -> Unit): Unit` |
| [reset](reset.md) | Removes the interceptors from the screen.`fun reset(): Unit` |
