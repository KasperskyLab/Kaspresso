package com.kaspersky.kaspresso.files

import java.io.File

interface DirsProvider {
    fun provideNew(path: File): File
    fun provideCleared(dir: File): File
}
