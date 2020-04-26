[kautomator](../../index.md) / [com.kaspersky.components.kautomator](../index.md) / [KautomatorConfigurator](./index.md)

# KautomatorConfigurator

`object KautomatorConfigurator`

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Sets the interceptors for the whole UiAutomator DSL runtime. Interceptors will be invoked on all of the interactions with the UiView instances.`fun intercept(configurator: Configurator.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [invoke](invoke.md) | Operator that allows usage of DSL style`operator fun invoke(function: `[`KautomatorConfigurator`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [reset](reset.md) | Removes the interceptors from the UiAutomator DSL runtime.`fun reset(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
