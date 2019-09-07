package com.kaspersky.kaspresso.autoscroll

interface AutoScrollProvider<Interaction> {

    fun <T> withAutoScroll(interaction: Interaction, action: () -> T): T

    fun <T> scroll(interaction: Interaction, action: () -> T, cachedError: Throwable): T
}