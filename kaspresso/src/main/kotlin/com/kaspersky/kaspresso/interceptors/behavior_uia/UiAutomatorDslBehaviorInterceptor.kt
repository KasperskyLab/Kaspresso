package com.kaspersky.kaspresso.interceptors.behavior_uia

interface UiAutomatorDslBehaviorInterceptor<INTERACTION, ASSERTION, ACTION> {

    /**
     * todo update comment
     * Called to do some stuff and actually check an interaction with element.
     *
     * @param activity a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> interceptCheck(interaction: INTERACTION, assertion: ASSERTION, activity: () -> T): T

    /**
     * todo update comment
     * Called to do some stuff and actually perform an interaction with element.
     *
     * @param activity a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> interceptPerform(interaction: INTERACTION, action: ACTION, activity: () -> T): T

}