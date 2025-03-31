import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphViewModel(
    private val graphParser: GraphParser,
    private val diagramGenerator: IDiagramGenerator
) {
    var graphInput by mutableStateOf("")
    var vertices by mutableStateOf(emptySet<String>())
    var disabledVertices by mutableStateOf(setOf<String>())
    var diagramPath by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun updateGraphInput(input: String) {
        graphInput = input
        vertices = graphParser.parseGraphInput(input)
    }

    fun toggleVertex(vertex: String, isEnabled: Boolean) {
        disabledVertices = if (isEnabled) {
            disabledVertices - vertex
        } else {
            disabledVertices + vertex
        }
    }

    fun refreshDiagram() {
        CoroutineScope(Dispatchers.IO).launch {
            if (graphInput.isEmpty()) {
                diagramPath = ""
                return@launch
            }

            isLoading = true
            diagramPath = diagramGenerator.generateDiagram(graphInput, disabledVertices)
            isLoading = false
        }
    }
}
