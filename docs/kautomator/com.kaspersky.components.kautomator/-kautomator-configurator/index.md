[kautomator](../../index.md) / [com.kaspersky.components.kautomator](../index.md) / [KautomatorConfigurator](./index.md)

# KautomatorConfigurator

`object KautomatorConfigurator`

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Sets the interceptors for the whole UiAutomator DSL runtime. Interceptors will be invoked on all of the interactions with the UiView instances.`fun intercept(configurator: Configurator.() -> Unit): Unit` |
| [invoke](invoke.md) | Operator that allows usage of DSL style`operator fun invoke(function: `[`KautomatorConfigurator`](./index.md)`.() -> Unit): Unit` |
| [reset](reset.md) | Removes the interceptors from the UiAutomator DSL runtime.`fun reset(): Unit` |
