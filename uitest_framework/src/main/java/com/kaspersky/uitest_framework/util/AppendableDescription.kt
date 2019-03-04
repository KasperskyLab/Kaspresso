package com.kaspersky.uitest_framework.util

import org.hamcrest.BaseDescription

/**
 * Created by egor.kurnikov on 03.03.2019
 */

class AppendableDescription(private val appendable: Appendable) : BaseDescription() {

    override fun append(c: Char) {
        appendable.append(c)
    }
}