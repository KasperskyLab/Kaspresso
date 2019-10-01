package com.kaspersky.kaspresso.failure.describe

interface FailedAssertionDescriber<Interaction> {
    fun getDescription(interaction: Interaction): String
}