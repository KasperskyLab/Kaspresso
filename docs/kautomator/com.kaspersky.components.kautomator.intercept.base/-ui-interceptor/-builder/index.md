[kautomator](../../../index.md) / [com.kaspersky.components.kautomator.intercept.base](../../index.md) / [UiInterceptor](../index.md) / [Builder](./index.md)

# Builder

`class Builder<Interaction, Assertion, Action>`

Builder class that is used to build a single instance of [UiInterceptor](../index.md).

**See Also**

[UiInterceptor](../index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Builder class that is used to build a single instance of [UiInterceptor](../index.md).`Builder()` |

### Functions

| Name | Summary |
|---|---|
| [onAll](on-all.md) | Sets the interceptor for the `check` and `execute` operations for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer.`fun onAll(isOverride: Boolean = false, interceptor: (Interaction) -> Unit): Unit` |
| [onCheck](on-check.md) | Sets the interceptor for the `check` operation for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer.`fun onCheck(isOverride: Boolean = false, interceptor: (Interaction, Assertion) -> Unit): Unit` |
| [onPerform](on-perform.md) | Sets the interceptor for the `execute` operation for a given interaction. If overridden, breaks the call chain of operation and transfers the responsibility to invoke the UiAutomator on the developer.`fun onPerform(isOverride: Boolean = false, interceptor: (Interaction, Action) -> Unit): Unit` |
