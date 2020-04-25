[kautomator](../../../index.md) / [com.kaspersky.components.kautomator.intercept.base](../../index.md) / [UiInterceptor](../index.md) / [Configurator](./index.md)

# Configurator

`class Configurator`

Configuration class that is used for building interceptors on the
[KautomatorConfigurator](../../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md) runtime and [UiScreens](#) levels.

**See Also**

[com.kaspersky.components.kautomator.KautomatorConfigurator](../../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md)

[com.kaspersky.components.kautomator.component.screen.UiScreen](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Configuration class that is used for building interceptors on the [KautomatorConfigurator](../../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md) runtime and [UiScreens](#) levels.`Configurator()` |

### Functions

| Name | Summary |
|---|---|
| [onUiDeviceInteraction](on-ui-device-interaction.md) | Setups the interceptor for `check` and `execute` operations happening through [UiDeviceInteraction](../../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`fun onUiDeviceInteraction(builder: Builder<`[`UiDeviceInteraction`](../../../com.kaspersky.components.kautomator.intercept.interaction/-ui-device-interaction/index.md)`, `[`UiDeviceAssertion`](../../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-assertion.md)`, `[`UiDeviceAction`](../../../com.kaspersky.components.kautomator.intercept.operation/-ui-device-action.md)`>.() -> Unit): Unit` |
| [onUiInteraction](on-ui-interaction.md) | Setups the interceptor for `check` and `execute` operations happening through [UiObjectInteraction](../../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`fun onUiInteraction(builder: Builder<`[`UiObjectInteraction`](../../../com.kaspersky.components.kautomator.intercept.interaction/-ui-object-interaction/index.md)`, `[`UiObjectAssertion`](../../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-assertion.md)`, `[`UiObjectAction`](../../../com.kaspersky.components.kautomator.intercept.operation/-ui-object-action.md)`>.() -> Unit): Unit` |
