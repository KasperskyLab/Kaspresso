package www.kaspersky.connector

data class ChatMessage(val id: Long, val body: CharSequence, val needSync: Boolean)