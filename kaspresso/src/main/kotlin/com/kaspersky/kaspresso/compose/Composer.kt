package com.kaspersky.kaspresso.compose

import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.ExtCompositeException

internal object Composer {

    /**
     * Invokes the given list of [actions] and succeeds if at least one of them succeeds.
     *
     * @param actions the list of actions to invoke.
     * @param logger the logger
     *
     * @return the order number of a succeed branch
     */
    @Throws(ExtCompositeException::class)
    fun getCompositeAction(
        actions: List<() -> Unit>,
        logger: UiTestLogger
    ): () -> Int = {
        logger.i("Composite action started.")
        var branchIndex = 0
        var success = false
        val cachedErrors: MutableList<Throwable> = mutableListOf()

        for (action in actions) {
            try {
                action.invoke()
                success = true
                logger.i("Composed action #$branchIndex succeeded.")
                break
            } catch (error: Throwable) {
                cachedErrors += error
                logger.e("Composed action #$branchIndex failed. The reason is ${error.javaClass.simpleName}")
                branchIndex++
            }
        }

        if (success) {
            branchIndex
        } else {
            logger.i("All composed actions totally failed.")
            throw ExtCompositeException(cachedErrors)
        }
    }
}