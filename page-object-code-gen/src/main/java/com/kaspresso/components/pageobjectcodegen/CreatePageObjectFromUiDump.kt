package com.kaspresso.components.pageobjectcodegen

import com.kaspresso.components.pageobjectcodegen.ViewType.Companion.collectableElements
import com.kaspresso.components.pageobjectcodegen.ViewType.Companion.elementsWithChild
import org.w3c.dom.Node
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

    var outputFilePath: String = try {
        args[2]
    } catch (e: Exception) {
        println("Output file will be locate in directory where you ran this script with name $className.kt")
        ""
    }

    if (!File(outputFilePath).exists() && outputFilePath.isNotEmpty()) {
        File(outputFilePath).mkdirs()
    }

    outputFilePath = if (outputFilePath.isNotEmpty()) {
        "$outputFilePath/$className.kt"
    } else {
        "$className.kt"
    }

    val filePackage = outputFilePath.findPackage()

    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
    val documentBuilder = documentBuilderFactory.newDocumentBuilder()
    val doc = documentBuilder.parse(inputFilePath)

    val screenElements: List<BaseView> = findAllViewInDump(doc.firstChild.firstChild)

    PageObjectGenerator(screenElements, filePackage, className).writeToFile(outputFilePath)
}

fun findAllViewInDump(root: Node, goToSiblings: Boolean = true): MutableList<BaseView> {
    val result = mutableListOf<BaseView>()
    if (root.nodeName == "node") {
        val attr = root.attributes
        if (attr.getNamedItem("class").nodeValue in collectableElements && attr.getNamedItem("resource-id").nodeValue != "") {
            result.add(getViewFromNode(root))
        }
        if (attr.getNamedItem("class").nodeValue in elementsWithChild) {
            val res = mutableSetOf<List<BaseView>>()
            val children = root.childNodes
            for (i in 0 until children.length) {
                if (children.item(i).nodeName == "node") {
                    res.add(findAllViewInDump(children.item(i), false))
                }
            }
            result.add(getViewWithChildrenFromNode(root, res))
        }
        if (root.hasChildNodes() && attr.getNamedItem("class").nodeValue !in elementsWithChild) {
            result.addAll(findAllViewInDump(root.firstChild))
        }
    }
    if (root.nextSibling != null && goToSiblings) {
        result.addAll(findAllViewInDump(root.nextSibling))
    }
    return result
}

fun getViewWithChildrenFromNode(node: Node, childViews: Set<List<BaseView>>): RecyclerView {
    val attr = node.attributes
    val viewType = attr.getNamedItem("class").nodeValue.substringAfterLast(".")
    return RecyclerView(
        attr.getNamedItem("resource-id").nodeValue.substringAfterLast("/"),
        ViewType.valueOf(viewType),
        attr.getNamedItem("package").nodeValue,
        childViews,
    )
}

fun getViewFromNode(node: Node): View {
    val attr = node.attributes
    val viewType = attr.getNamedItem("class").nodeValue.substringAfterLast(".")
    return View(
        attr.getNamedItem("resource-id").nodeValue.substringAfterLast("/"),
        ViewType.valueOf(viewType),
        attr.getNamedItem("package").nodeValue,
    )
}

fun String.findPackage(): String {
    return split("/").toMutableList()
        .dropWhile { it != "com" }.dropLast(1).joinToString(separator = ".")
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
