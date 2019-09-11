package com.kaspersky.kaspresso.internal.extensions.other

/**
 * Returns an array of all directly and indirectly implemented interfaces.
 *
 * @return array of all interfaces this class implements.
 */
@PublishedApi
internal fun <T> Class<T>.getAllInterfaces(): Array<Class<*>> {
    var currentClass: Class<*>? = this
    val interfaces = mutableSetOf<Class<*>>()

    if (this.isInterface) interfaces.add(this)

    while (currentClass != null) {
        interfaces.addAll(currentClass.interfaces)
        currentClass = currentClass.superclass
    }

    return interfaces.toTypedArray()
}
