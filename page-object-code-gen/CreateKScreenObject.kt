import java.io.File

object CreateKScreenObject{

    fun generateFile(screenElements: List<View>): String {

        val packages = screenElements[0].packages
        val elements = createElements(screenElements)
        val imports = createImports(screenElements)

        val screenClassTemplate =
            """
import $packages.R
import com.screen.common.KScreen
$imports

object screenClass : KScreen<screenClass>() {

    override val layoutId: Int? = TODO("Need To Implement")
    override val viewClass: Class<*>? = TODO("Need To Implement")

    $elements

    override fun BaseTestContext.waitForScreen() {
        TODO("Need To Implement")
    }
}
    """

        return screenClassTemplate
    }

    fun writeToFile(fileName: String, code: String){

        val dstFile = File(fileName)
        dstFile.writeText(code)
    }

    fun createElements(screenElements: List<View>): String {
        var elemetsStringResult: String = ""

        screenElements.forEach { elemetsStringResult += it.toKaspressoExpression() + "\n\t" }

        return elemetsStringResult
    }

    fun createImports(screenElements: List<View>): String {
        val importsList: MutableSet<String> = mutableSetOf<String>()

        for (element in screenElements) {
            var view = element.viewType
            when (view) {
                "ImageView" -> importsList.add("import io.guthub.kakaocup.kakao.image.KImageView\n")
                "Button" -> importsList.add("import io.guthub.kakaocup.kakao.text.KButton\n")
                "TextView" -> importsList.add("import io.guthub.kakaocup.kakao.text.KTextView\n")
                "RecyclerView" -> importsList.add("import io.guthub.kakaocup.kakao.recycler.KRecyclerView\n")
                "RecyclerItem" -> importsList.add("import io.guthub.kakaocup.kakao.recycler.KRecyclerItem\n")
                "EditText" -> importsList.add("import io.guthub.kakaocup.kakao.edit.KEditText\n")
            }
        }

        return importsList.joinToString(separator="")
    }
}
