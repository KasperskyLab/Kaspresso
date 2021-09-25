package com.kaspersky.kaspresso.files.resources

interface ResourceTagsProvider<T> {
    fun provide(source: T): String
}
