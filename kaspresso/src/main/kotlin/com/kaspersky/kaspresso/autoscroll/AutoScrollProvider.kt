package com.kaspersky.kaspresso.autoscroll

/**
 * The interface to provide autoscroll functionality.
 */
interface AutoScrollProvider<Interaction> {

    /**
     * Invokes the given [action] and calls [scroll] if it fails. Helps in cases when test fails because of the
     * need to scroll to interacted view.
     *
     * @param interaction the interaction interface to perform actions and assertions.
     * @param action the actual action on the interacted view.
     *
     * @return the result of [action] invocation.
     */
    fun <T> withAutoScroll(interaction: Interaction, action: () -> T): T

    /**
     * Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action].
     *
     * @param interaction the interaction interface to perform actions and assertions.
     * @param action the actual action on the interacted view.
     * @param cachedError the error to be thrown if autoscroll would not help.
     *
     * @return the result of [action] invocation.
     */
    fun <T> scroll(interaction: Interaction, action: () -> T, cachedError: Throwable): T
}
