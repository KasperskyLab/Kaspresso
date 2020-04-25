[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [AtomProxy](index.md) / [getArguments](./get-arguments.md)

# getArguments

`fun getArguments(elementContext: ElementReference?): MutableList<Any>`

Simply calls [Atom.getArguments](#) on wrapped [atom](atom.md).

### Parameters

`elementContext` - null unless an ElementReference has been supplied to interact this atom with.

**Return**
a [List](#) of objects to pass to the script as arguments.

