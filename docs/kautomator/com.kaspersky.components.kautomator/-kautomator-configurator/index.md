[kautomator](../../index.md) / [com.kaspersky.components.kautomator](../index.md) / [KautomatorConfigurator](./index.md)

# KautomatorConfigurator

`object KautomatorConfigurator`

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun intercept(configurator: `[`UiInterceptor.Configurator`](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/-configurator/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the interceptors for the whole UiAutomator DSL runtime. Interceptors will be invoked on all of the interactions with the UiView instances. |
| [invoke](invoke.md) | `operator fun invoke(function: `[`KautomatorConfigurator`](./index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |
| [reset](reset.md) | `fun reset(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes the interceptors from the UiAutomator DSL runtime. |
