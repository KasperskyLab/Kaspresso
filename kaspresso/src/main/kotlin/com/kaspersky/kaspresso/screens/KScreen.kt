package com.kaspersky.kaspresso.screens

import io.github.kakaocup.kakao.screen.Screen

abstract class KScreen<out T : KScreen<T>> : Screen<T>() {

    abstract val layoutId: Int?
    abstract val viewClass: Class<*>?
}
