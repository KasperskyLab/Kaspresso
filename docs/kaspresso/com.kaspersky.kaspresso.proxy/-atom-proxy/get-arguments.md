[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [AtomProxy](index.md) / [getArguments](./get-arguments.md)

# getArguments

`fun getArguments(elementContext: ElementReference?): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>`

Simply calls [Atom.getArguments](#) on wrapped [atom](#).

### Parameters

`elementContext` - null unless an ElementReference has been supplied to execute this atom with.

**Return**
a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of objects to pass to the script as arguments.

