package com.kaspersky.kaspresso.extensions.spoonext

import android.os.Build
import java.io.File
import java.io.IOException

internal abstract class Chmod {

    protected abstract fun plusR(file: File)
    protected abstract fun plusRWX(file: File)

    companion object {

        private val INSTANCE: Chmod

        init {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                INSTANCE =
                    Java6Chmod()
            } else {
                INSTANCE =
                    Java5Chmod()
            }
        }

        fun chmodPlusR(file: File) {
            INSTANCE.plusR(file)
        }

        fun chmodPlusRWX(file: File) {
            INSTANCE.plusRWX(file)
        }
    }

    private class Java5Chmod : Chmod() {

        override fun plusR(file: File) {
            try {
                Runtime.getRuntime().exec(arrayOf("chmod", "644", file.absolutePath))
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }

        override fun plusRWX(file: File) {
            try {
                Runtime.getRuntime().exec(arrayOf("chmod", "777", file.absolutePath))
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }

    private class Java6Chmod : Chmod() {

        override fun plusR(file: File) {
            file.setReadable(true, false)
        }

        override fun plusRWX(file: File) {
            file.setReadable(true, false)
            file.setWritable(true, false)
            file.setExecutable(true, false)
        }
    }
}