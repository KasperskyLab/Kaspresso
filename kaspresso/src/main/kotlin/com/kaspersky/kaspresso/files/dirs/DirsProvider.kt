package com.kaspersky.kaspresso.files.dirs

import java.io.File

interface DirsProvider {
    fun provideNew(dest: File): File
    fun provideCleared(dest: File): File
}
