[kautomator](../../../index.md) / [com.kaspersky.components.kautomator.intercept.base](../../index.md) / [UiInterceptor](../index.md) / [Builder](index.md) / [onAll](./on-all.md)

# onAll

`fun onAll(isOverride: Boolean = false, interceptor: (Interaction) -> Unit): Unit`

Sets the interceptor for the `check` and `execute` operations for a given interaction.
If overridden, breaks the call chain of operation and transfers the responsibility
to invoke the UiAutomator on the developer.

This interceptor is prioritized and is being invoked first for both operations.

### Parameters

`isOverride` - if `true` - breaks the call chain, false otherwise

`interceptor` - lambda with intercepting logic