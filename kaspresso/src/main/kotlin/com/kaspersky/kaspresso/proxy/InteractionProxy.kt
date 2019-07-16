package com.kaspersky.kaspresso.proxy

import com.kaspersky.kaspresso.interceptors.interactors.Interactor

interface InteractionProxy<Interaction> {

    val interaction: Interaction
    val interactors: List<Interactor<Interaction>>

    fun <R> interact(view: Any?, interactable: () -> R): R {
        return interactors.fold(interactable) { acc, elem -> { elem.interact(view, interaction, acc) } }.invoke()
    }
}