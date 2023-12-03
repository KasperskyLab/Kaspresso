package com.kaspresso.components.pageobjectcodegen

import org.w3c.dom.Document
import java.io.File
import java.nio.charset.Charset
import javax.xml.parsers.DocumentBuilderFactory

/**
 * inputs:
 * 1. Path to xml file with UI Dump
 * 2. Name of generated class
 * 3. Path for generated file
 * output:
 * Kotlin file with screen code in the same directory as jar execute
 */
fun main(vararg args: String) {
    lateinit var inputFilePath: String
    lateinit var className: String

    try {
        inputFilePath = args[0]
    } catch (e: Exception) {
        throw Exception("No file path")
    }

    if (!File(inputFilePath).isFile) {
        throw Exception("File is not exist or directory")
    }

    className = try {
        args[1]
    } catch (e: Exception) {
        println("You put empty class name, we change it to \"TestClass\"")
        "TestClass"
    }

    if (!className.contains(Regex("^[A-Z]\\S*$"))) {
        println("You put incorrect class name, we change it to \"TestClass\"")
        className = "TestClass"
    }

    val outputFilePath: String = try {
        args[2] + "/$className.kt"
    } catch (e: Exception) {
        println("Output file will be locate in directory where you ran this script with name $className.kt")
        "$className.kt"
    }

    val filePackage = outputFilePath.findPackage()

    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
    val documentBuilder = documentBuilderFactory.newDocumentBuilder()
    val doc = documentBuilder.parse(inputFilePath)

    val screenElements: List<View> = findAllViewInDump(doc)

    PageObjectGenerator(screenElements, filePackage, className).writeToFile(outputFilePath)
}

fun findAllViewInDump(document: Document): List<View> {
    val collectableElements = listOf("android.widget.Button", "android.widget.TextView", "android.widget.ImageView")
    val screenElements: MutableList<View> = mutableListOf()
    val bookNodeList = document.getElementsByTagName("node")

    for (i in 0 until bookNodeList.length) {
        val bookNodeAttr = bookNodeList.item(i).attributes
        if (bookNodeAttr.getNamedItem("class").nodeValue in collectableElements) {
            val elem = View(
                bookNodeAttr.getNamedItem("resource-id").nodeValue.substringAfterLast("/"),
                bookNodeAttr.getNamedItem("class").nodeValue.substringAfterLast("."),
                bookNodeAttr.getNamedItem("package").nodeValue,
            )
            screenElements.add(elem)
        }
    }

    return screenElements
}

fun String.findPackage(): String {
    return split("/").toMutableList().dropLast(1)
        .dropWhile { it != "com" }.joinToString(separator = ".")
}

fun Generator.writeToFile(filePath: String) {
    val writer = TextWriter()
    generate(writer)
    val file = File(filePath)
    val printWriter = file.printWriter(Charset.forName("UTF-8"))
    try {
        printWriter.print(writer.toString())
    } finally {
        printWriter.close()
    }
}
