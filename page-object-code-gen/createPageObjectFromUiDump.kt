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
    val fileResult = File("result.kt")

    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
    val documentBuilder = documentBuilderFactory.newDocumentBuilder()
    val doc = documentBuilder.parse(filePath)

    val screenElements: List<View> = findAllViewInDump(doc)

    fileResult.writeText(createScreenClass(screenElements))
}

data class View(val resourceId: String, val viewType: String, val packages: String) {

    fun toKaspressoExpression(): String {
        return "val ${parseElementName(resourceId, viewType)} = K$viewType{ withId(R.id.$resourceId) }"
    }
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

fun createScreenClass(screenElements: List<View>): String {
    val packages = screenElements[0].packages
    val elements = createElements(screenElements)
    val imports = createImports(screenElements)
    val elementForWaitForScreen = parseElementName(screenElements.first().resourceId, screenElements.first().viewType)

    val screenClassTemplate =
        """
    import $packages.R
    import com.screen.common.KScreen
    $imports

    object screenClass : KScreen<screenClass>() {

        override val layoutId: Int? = TODO("Need To Implemet")
        override val viewClass: Class<*>? = TODO("Need To Implemet")

        $elements

        override fun BaseTestContext.waitForScreen() {
            $elementForWaitForScreen{
                isVisible()
            }
        }
    }
"""

    return screenClassTemplate
}

fun createElements(screenElements: List<View>): String {
    var elemetsStringResult: String = ""

    screenElements.forEach { elemetsStringResult += it.toKaspressoExpression() + "\n\t\t" }

    return elemetsStringResult
}

fun createImports(screenElements: List<View>): String {
    var importsStringResult: String = ""
    val importsList: MutableList<String> = mutableListOf<String>()

    for (element in screenElements) {
        var view = element.viewType
        when (view) {
            "ImageView" -> importsList.add("import io.guthub.kakaocup.kakao.image.KImageView\n\t")
            "Button" -> importsList.add("import io.guthub.kakaocup.kakao.text.KButton\n\t")
            "TextView" -> importsList.add("import io.guthub.kakaocup.kakao.text.KTextView\n\t")
            "RecyclerView" -> importsList.add("import io.guthub.kakaocup.kakao.recycler.KRecyclerView\n\t")
            "RecyclerItem" -> importsList.add("import io.guthub.kakaocup.kakao.recycler.KRecyclerItem\n\t")
            "EditText" -> importsList.add("import io.guthub.kakaocup.kakao.edit.KEditText\n\t")
        }
    }

    val uniqueImportsList: List<String> = unique(importsList).sorted()
    uniqueImportsList.forEach { importsStringResult += it }

    return importsStringResult
}

fun parseElementName(resourceId: String, viewType: String): String {
    var parts = resourceId.split("_").filter { it -> it !in listOf("button", "image", "text") }
    val name = parts.joinToString(separator = "") { it -> it.replaceFirstChar { it.uppercase() } }.replaceFirstChar { it.lowercase() }
    when (viewType) {
        "Button" -> return name + "Btn"
        "ImageView" -> return name + "Img"
        else -> return name
    }
}

fun unique(list: List<String>): MutableList<String> {
    val resultList: MutableList<String> = mutableListOf()
    for (elem in list) {
        if (elem !in resultList) {
            resultList.add(elem)
        }
    }
    return resultList
}
