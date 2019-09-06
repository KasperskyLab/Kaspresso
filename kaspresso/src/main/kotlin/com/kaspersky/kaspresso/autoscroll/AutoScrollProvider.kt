package com.kaspersky.kaspresso.autoscroll

interface AutoScrollProvider<Interaction> {

    val params: AutoScrollParams

    fun <R> autoscroll(interaction: Interaction, action: () -> R, cachedError: Throwable): R
}