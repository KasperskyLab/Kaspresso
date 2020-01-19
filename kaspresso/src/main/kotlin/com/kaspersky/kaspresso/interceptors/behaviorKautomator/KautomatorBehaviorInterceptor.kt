package com.kaspersky.kaspresso.interceptors.behaviorKautomator

interface KautomatorBehaviorInterceptor<Interaction, Assertion, Action> {

    /**
     * todo update comment
     * Called to do some stuff and actually check an interaction with element.
     *
     * @param activity a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> interceptCheck(interaction: Interaction, assertion: Assertion, activity: () -> T): T

    /**
     * todo update comment
     * Called to do some stuff and actually perform an interaction with element.
     *
     * @param activity a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> interceptPerform(interaction: Interaction, action: Action, activity: () -> T): T
}