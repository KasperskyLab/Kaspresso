import org.w3c.dom.Document
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import java.lang.Runtime

/**
 * inputs:
 * 1. Path to xml file with UI Dump
 * 2. Name of generated class
 * 3. Path for generated file
 * output:
 * Kotlin file with screen code in the same derictory as jar execute
 */
fun main(vararg args: String) {

    lateinit var inputFilePath: String
    lateinit var className: String
    lateinit var outputFilePath: String

    try{
        inputFilePath = args[0]
    } catch (e: Exception){
        throw Exception("No file path")
    }

    if (!File(inputFilePath).isFile) {
        throw Exception("File is not exist or derictory")
    }

    try{
        className = args[1]
    } catch (e: Exception){
        println("You put empty class name, we change it to \"TestClass\"")
        className = "TestClass"
    }

    if(!className.contains(Regex("^[A-Z]\\S{0,}$"))){
        println("You put incorrect class name, we change it to \"TestClass\"")
        className = "TestClass"
    }

    try{
        outputFilePath = args[2]+"/${className}.kt"
    } catch (e: Exception){
        println("Output file will be locate in directory where you ran this script with name ${className}.kt")
        outputFilePath = "${className}.kt"
    }

    val filePackage = outputFilePath.findPackage()

    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
    val documentBuilder = documentBuilderFactory.newDocumentBuilder()
    val doc = documentBuilder.parse(inputFilePath)

    val screenElements: List<View> = findAllViewInDump(doc)

    val kscreencode = CreateKScreenObject.generateFile(screenElements, className, filePackage)
    CreateKScreenObject.writeToFile(outputFilePath, kscreencode)
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

fun String.findPackage(): String {
    var tmp = split("/").toMutableList()
    tmp.removeLast()
    var afterCom = false
    var res = ""
    for(i in tmp){
        if(afterCom){
            res+=".${i}"
        }
        if(i=="com"){
            res+="com"
            afterCom=true
        }
    }
    return res
}
