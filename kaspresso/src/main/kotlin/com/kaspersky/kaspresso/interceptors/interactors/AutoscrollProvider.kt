package com.kaspersky.kaspresso.interceptors.interactors

interface AutoscrollProvider<Interaction> {

    fun <R> autoscroll(interaction: Interaction, action: () -> R, cachedError: Throwable): R
}