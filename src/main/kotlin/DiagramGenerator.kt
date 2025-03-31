import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DiagramGenerator : IDiagramGenerator {

    override suspend fun generateDiagram(graphData: String, disabledVertices: Set<String>): String {

        if (graphData.isBlank()) {
            return ""
        }

        val filteredData = graphData
            .lines()
            .filter { line ->
                val parts = line.split("->").map { it.trim() }
                parts.size == 2 && parts.none { it in disabledVertices }
            }
            .joinToString("\n")

        if (filteredData.isBlank()) return ""

        val umlContent = """
    @startuml
    $filteredData
    @enduml
    """.trimIndent()

        val tempFile = withContext(Dispatchers.IO) {
            File.createTempFile("graph", ".puml")
        }
        tempFile.writeText(umlContent)

        val outputImage = withContext(Dispatchers.IO) {
            File.createTempFile("graph", ".png")
        }

        try {
            val reader = SourceStringReader(umlContent)
            val fileFormatOption = FileFormatOption(FileFormat.PNG)

            withContext(Dispatchers.IO) {
                FileOutputStream(outputImage).use { outputStream ->
                    reader.outputImage(outputStream, fileFormatOption)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }

        return outputImage.absolutePath
    }

}