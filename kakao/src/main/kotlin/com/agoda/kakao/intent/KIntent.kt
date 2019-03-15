@file:Suppress("unused")

package com.agoda.kakao.intent

import android.app.Instrumentation.ActivityResult
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.VerificationMode

/**
 * This class is intended to be used when you need to check if some intent has been sent
 * or to mock specific intent with result
 *
 * @param builder Builder for intent matching
 */
class KIntent(builder: IntentBuilder.() -> Unit) {
    private val matcher = IntentBuilder().apply(builder).getMatcher()
    private val res = IntentBuilder().apply(builder).getResult()

    /**
     * Checks if this intent has been sent previously
     *
     * @param verificationMode Verification mode for this intent. null by default
     */
    fun intended(verificationMode: VerificationMode? = null) {
        verificationMode?.run {
            Intents.intended(matcher, this)
        } ?: Intents.intended(matcher)
    }

    /**
     * Mocks next coming intent that will match with provided result.
     * If no result provided as parameter, function will look up default one
     * set via withResult() in IntentBuilder. If none are present, IllegalStateException
     * will be thrown
     *
     * @param result Activity result to return when matched intent is sent. null by default
     *
     * @throws IllegalStateException if result is null and default result is not set
     *
     * @see IntentBuilder.withResult
     */
    fun intending(result: ActivityResult? = null) {
        val chosen = result ?: res

        chosen?.let {
            Intents.intending(matcher).respondWith(it)
        } ?: throw IllegalStateException("Cannot mock intent with empty result! Please set it" +
                " at declaration site or pass as parameter!")
    }

    /**
     * Mocks next coming intent that will match with provided result.
     *
     * @param result Builder for activity result to return when matching intent is sent
     */
    fun intending(result: ActivityResultBuilder.() -> Unit) {
        Intents.intending(matcher).respondWith(ActivityResultBuilder().apply(result).getResult())
    }

    operator fun invoke(function: KIntent.() -> Unit) {
        function(this)
    }
}