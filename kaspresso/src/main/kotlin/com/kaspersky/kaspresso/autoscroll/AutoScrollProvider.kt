package com.kaspersky.kaspresso.autoscroll

interface AutoScrollProvider<Interaction> {

    val params: AutoScrollParams

    fun <R> autoScroll(interaction: Interaction, action: () -> R, cachedError: Throwable): R
}