[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.logcat](../index.md) / [Logcat](index.md) / [clear](./clear.md)

# clear

`abstract fun clear(buffer: `[`Logcat.Buffer`](-buffer/index.md)` = Buffer.DEFAULT): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Clear (flush) the selected buffers and exit.
The default buffer set is main, system and crash.

### Parameters

`buffer` - one of available logcat buffers