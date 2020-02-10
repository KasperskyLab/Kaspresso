[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [default](./default.md)

# default

`fun ~~default~~(customize: `[`Kaspresso.Builder`](index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Kaspresso.Builder`](index.md)
**Deprecated:** Use `advanced()` builder.

Default (preconfigured full-featured with screenshot functionality) [Builder](index.md) for test environment configuration.

If you desire to override some variables in Configurator please use [customize](default.md#com.kaspersky.kaspresso.kaspresso.Kaspresso.Builder.Companion$default(kotlin.Function1((com.kaspersky.kaspresso.kaspresso.Kaspresso.Builder, kotlin.Unit)))/customize) parameter here.

The example is:

```
class ComposeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.default {
        adbServer = CustomAdbServerImpl()
    }
)
```

In this case your implementation of `adbServer` will be used for initializing of all other variables.
So, your implementation has a priority over the default implementation while using [customize](default.md#com.kaspersky.kaspresso.kaspresso.Kaspresso.Builder.Companion$default(kotlin.Function1((com.kaspersky.kaspresso.kaspresso.Kaspresso.Builder, kotlin.Unit)))/customize) parameter for the overriding.

Otherwise, if you don't set custom implementation then default implementation of `adbServer` will be explored.

Please, be aware, that overridings like:

```
class ComposeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.default().apply {
        adbServer = CustomAdbServerImpl()
    }
)
```

don't give you desired effect! Yes, you have an possibility to use your custom `adbServer`, but
in all interceptors and other variables default implementation of `adbServer` will be exploring.

We strongly recommend to use `apply` construction in such cases as an updating of a list of interceptors or
to change the order of interceptors in the list (for example, it are variables like
`viewActionWatcherInterceptors`, `viewAssertionWatcherInterceptors`, etc).

@return the new instance of [Builder](index.md).

