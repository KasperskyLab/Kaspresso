package com.kaspersky.uitest_framework.kakaoext

import com.kaspersky.uitest_framework.kakao.common.assertions.BaseAssertions
import java.lang.Exception

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