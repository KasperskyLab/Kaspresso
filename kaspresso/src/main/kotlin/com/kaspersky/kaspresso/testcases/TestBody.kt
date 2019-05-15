package com.kaspersky.kaspresso.testcases

class TestBody(
    private val title: String,
    private val beforeTestActions: () -> Unit,
    private val afterTestActions: (Boolean) -> Unit,
    private val mainSection: Scenario.() -> Unit
) {

    companion object {
        fun builder(): Builder =
            Builder()
    }

    fun run() {
        var stepsPassed = true

        try {
            beforeTestActions.invoke()
            mainSection.invoke(Scenario(title))
        } catch (e: Throwable) {
            stepsPassed = false
            throw e
        } finally {
            afterTestActions.invoke(stepsPassed)
        }
    }

    class Builder {
        private var beforeTestSection: (() -> Unit)? = null
        private var afterTestSection: ((Boolean) -> Unit)? = null
        private var mainSection: (Scenario.() -> Unit)? = null
        private var className: String? = null

        fun beforeSection(action: () -> Unit): Builder {
            beforeTestSection = action
            return this
        }

        fun afterSection(action: (Boolean) -> Unit): Builder {
            afterTestSection = action
            return this
        }

        fun mainSection(action: Scenario.() -> Unit): Builder {
            mainSection = action
            return this
        }

        fun className(name: String): Builder {
            className = name
            return this
        }


        fun build(): TestBody {
            if (beforeTestSection == null || afterTestSection == null || mainSection == null || className == null)
                throw IllegalArgumentException("Check all section filled")

            return TestBody(
                className!!,
                beforeTestSection!!,
                afterTestSection!!,
                mainSection!!
            )
        }
    }
}