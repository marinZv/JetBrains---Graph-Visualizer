import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    val viewModel = GraphViewModel(GraphParser(), DiagramGenerator())

    Window(onCloseRequest = ::exitApplication, title = "Graph Visualizer") {
        GraphUI(viewModel)
    }
}
