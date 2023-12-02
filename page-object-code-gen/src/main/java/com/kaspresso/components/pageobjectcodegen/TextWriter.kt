package com.kaspresso.components.pageobjectcodegen

import java.lang.StringBuilder

class TextWriter(private val indentation: Int = 0) {

    private val line = StringBuilder()
    private val lines = mutableListOf<Any>()

    init {
        line.append(" ".repeat(indentation))
    }

    fun append(append: String): TextWriter = apply { line.append(append) }

    fun appendLine(append: String): TextWriter = apply { nextLine().append(append) }

    fun nextLine(count: Int = 1): TextWriter = apply {
        for (i in 0 until count) {
            commitLine()
            initNewLine()
        }
    }

    private fun commitLine() {
        lines.add(line.toString())
    }

    private fun initNewLine() {
        line.setLength(0)
        line.append(" ".repeat(indentation))
    }

    private fun withIncreasedIndentation(): TextWriter {
        nextLine()
        val writer = TextWriter(indentation + INDENTATION_STEP)
        lines.add(writer)
        return writer
    }

    override fun toString(): String {
        commitLine()
        return lines.joinToString("\n")
    }

    fun codeBlock(header: String, block: TextWriter.() -> Unit) {
        appendLine("$header {")
        with(withIncreasedIndentation()) {
            block()
        }
        append("}")
    }

    companion object Constants {
        private const val INDENTATION_STEP: Int = 4
    }
}
