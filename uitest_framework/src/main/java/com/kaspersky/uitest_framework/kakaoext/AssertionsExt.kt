package com.kaspersky.uitest_framework.kakaoext

import com.kaspersky.uitest_framework.kakao.BaseAssertions
import java.lang.Exception

/**
 * Created by egor.kurnikov on 06.03.2019
 */

fun BaseAssertions.check(
        vararg asserts: BaseAssertions.() -> Unit
): Boolean {

    var successfully = false

    asserts.forEach { assert ->
        try {
            assert.invoke(this)
            successfully = true
        } catch (e: Exception) {
            //
        }
    }

    return successfully
}