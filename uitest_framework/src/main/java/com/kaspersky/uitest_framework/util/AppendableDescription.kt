package com.kaspersky.uitest_framework.util

import org.hamcrest.BaseDescription

class AppendableDescription(private val appendable: Appendable) : BaseDescription() {

    override fun append(c: Char) {
        appendable.append(c)
    }
}