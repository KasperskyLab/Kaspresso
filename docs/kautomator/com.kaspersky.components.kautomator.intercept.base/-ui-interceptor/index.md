[kautomator](../../index.md) / [com.kaspersky.components.kautomator.intercept.base](../index.md) / [UiInterceptor](./index.md)

# UiInterceptor

`class UiInterceptor<Interaction, Assertion, Action>`

Base class for intercepting the call chain from Kautomator to UiAutomator.

Interceptors can be provided through [KautomatorConfigurator](../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md) runtime,
different [UiScreens](#) as well as [UiViews](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md).

Interceptors are stacked during the runtime for any UiAutomator_DSL-UiAutomator `check` and `perform` operations.
The stack ordering is following: UiView interceptor -&gt; UiScreen interceptors -&gt; UiAutomatorDsl interceptor.

Any of the interceptors in the chain can break the chain call by setting `isOverride` to true
in [onCheck](-builder/on-check.md), [onPerform](-builder/on-perform.md) or [onAll](-builder/on-all.md) interception
functions during the configuration. Doing this will not only prevent underlying
interceptors from being invoked, but prevents UiAutomator from executing the operation. In that case,
responsibility for actually making UiAutomator call lies on developer.

For each operation the interceptor invocation cycle will be as follows:

```
// For check operation
onAll?.invoke()
onCheck?.invoke()

// For perform operation
onAll?.invoke()
onPerform?.invoke()
```

**See Also**

[com.kaspersky.components.kautomator.KautomatorConfigurator](../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md)

[com.kaspersky.components.kautomator.component.screen.UiScreen](#)

[com.kaspersky.components.kautomator.component.common.views.UiBaseView](../../com.kaspersky.components.kautomator.component.common.views/-ui-base-view/index.md)

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | Builder class that is used to build a single instance of [UiInterceptor](./index.md).`class Builder<Interaction, Assertion, Action>` |
| [Configuration](-configuration/index.md) | `data class Configuration` |
| [Configurator](-configurator/index.md) | Configuration class that is used for building interceptors on the [KautomatorConfigurator](../../com.kaspersky.components.kautomator/-kautomator-configurator/index.md) runtime and [UiScreens](#) levels.`class Configurator` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Base class for intercepting the call chain from Kautomator to UiAutomator.`UiInterceptor(onCheck: `[`UiInterception`](../-ui-interception/index.md)`<(Interaction, Assertion) -> Unit>?, onPerform: `[`UiInterception`](../-ui-interception/index.md)`<(Interaction, Action) -> Unit>?, onAll: `[`UiInterception`](../-ui-interception/index.md)`<(Interaction) -> Unit>?)` |

### Properties

| Name | Summary |
|---|---|
| [onAll](on-all.md) | `val onAll: `[`UiInterception`](../-ui-interception/index.md)`<(Interaction) -> Unit>?` |
| [onCheck](on-check.md) | `val onCheck: `[`UiInterception`](../-ui-interception/index.md)`<(Interaction, Assertion) -> Unit>?` |
| [onPerform](on-perform.md) | `val onPerform: `[`UiInterception`](../-ui-interception/index.md)`<(Interaction, Action) -> Unit>?` |