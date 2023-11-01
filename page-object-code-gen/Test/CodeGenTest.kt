import java.io.File
import java.lang.Runtime
import java.nio.file.Files

class CodeGenTest {

    @ParameterizedTest(name = "Check code gen. Input: {0}. Output: {1}")
    @CsvSource({
        "source1.xml, path1, TestClass1",
        "source2.xml, path2, TestClass2"
    })
    fun 'Check code gen' (String inputPath, String outputDirectory, String className) {
        Runtime.getRuntime().exec("java -jar codeGen.jar ${inputPath} ${className} ${outputDirectory}")
        Assertions.assertEquals(Files.readString("Result.kt"), Files.readString("${outputDirectory}"))
    }
}
