package com.kaspersky.kaspresso.files.extensions

enum class FileExtension {
    TXT {
        override fun toString() = ".txt"
    },
    XML {
        override fun toString() = ".xml"
    },
    PNG {
        override fun toString() = ".png"
    },
    MP4 {
        override fun toString() = ".mp4"
    }
}
