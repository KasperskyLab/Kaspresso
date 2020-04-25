[kautomator](../../../index.md) / [com.kaspersky.components.kautomator.intercept.base](../../index.md) / [UiInterceptor](../index.md) / [Builder](index.md) / [onPerform](./on-perform.md)

# onPerform

`fun onPerform(isOverride: Boolean = false, interceptor: (Interaction, Action) -> Unit): Unit`

Sets the interceptor for the `execute` operation for a given interaction.
If overridden, breaks the call chain of operation and transfers the responsibility
to invoke the UiAutomator on the developer.

### Parameters

`isOverride` - if `true` - breaks the call chain, false otherwise

`interceptor` - lambda with intercepting logic