[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [default](./default.md)

# default

`fun default(): `[`Kaspresso.Builder`](index.md)

Puts the default preferences and entities pack to [Builder](index.md).
Please be aware if you add some settings after [default](./default.md) method. You can catch inconsistent state of the
[Builder](index.md). For example if you change [libLogger](lib-logger.md) after [default](./default.md) method than all interceptors will work
with old [libLogger](lib-logger.md).

**Return**
the new instance of [Builder](index.md).

