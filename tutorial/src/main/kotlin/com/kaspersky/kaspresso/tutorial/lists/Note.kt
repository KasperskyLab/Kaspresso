package com.kaspersky.kaspresso.tutorial.lists

data class Note(
    val id: Int,
    val text: String,
    val priority: Priority
)

enum class Priority {
    LOW, MEDIUM, HIGH
}
