import org.w3c.dom.Document
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

/**
 * inputs:
 * 1. Path to xml file with UI Dump
 * output:
 * Kotlin file with screen code in the same derictory as jar execute
 */
fun main(vararg args: String) {
    val filePath = args.first()
    if (!File(filePath).isFile) {
        throw Exception("File is not exist or derictory")
    }


    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
    val documentBuilder = documentBuilderFactory.newDocumentBuilder()
    val doc = documentBuilder.parse(filePath)

    val screenElements: List<View> = findAllViewInDump(doc)

    val kscreencode = CreateKScreenObject.generateFile(screenElements)
    CreateKScreenObject.writeToFile("result.kt", kscreencode)
}


fun findAllViewInDump(document: Document): List<View> {
    val collectableElements = listOf("android.widget.Button", "android.widget.TextView", "android.widget.ImageView")
    val screenElements: MutableList<View> = mutableListOf<View>()
    val bookNodeList = document.getElementsByTagName("node")

    for (i in 0..bookNodeList.length - 1) {
        val bookNodeAttr = bookNodeList.item(i).attributes
        if (bookNodeAttr.getNamedItem("class").nodeValue in collectableElements) {
            val elem: View = View(
                bookNodeAttr.getNamedItem("resource-id").nodeValue.substringAfterLast("/"),
                bookNodeAttr.getNamedItem("class").nodeValue.substringAfterLast("."),
                bookNodeAttr.getNamedItem("package").nodeValue,
            )
            screenElements.add(elem)
        }
    }

    return screenElements
}

fun parseElementName(resourceId: String): String {
    return resourceId.split("_").joinToString(separator = "") { it -> it.replaceFirstChar { it.uppercase() } }.replaceFirstChar { it.lowercase() }
}
