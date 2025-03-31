import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import java.io.File
import javax.imageio.ImageIO

@Composable
@Preview
fun GraphUI(viewModel: GraphViewModel) {
    var graphInput by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(graphInput, viewModel.disabledVertices) {
        viewModel.refreshDiagram()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = graphInput,
            onValueChange = {
                graphInput = it
                viewModel.updateGraphInput(it)
            },
            label = { Text("Enter graph data (e.g., A -> B)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = if (viewModel.vertices.isEmpty()) "Empty vertices list" else "Vertices",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth()
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().height(400.dp)
                ) {
                    items(viewModel.vertices.toList()) { vertex ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = vertex !in viewModel.disabledVertices,
                                onCheckedChange = { isChecked ->
                                    viewModel.toggleVertex(vertex, isChecked)
                                }
                            )
                            Text("Vertex: $vertex")
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else if (viewModel.diagramPath.isNotEmpty() && File(viewModel.diagramPath).exists()) {

                    Image(
                        painter = BitmapPainter(ImageIO.read(File(viewModel.diagramPath)).toComposeImageBitmap()),
                        contentDescription = "Graph Diagram",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    )
                } else {
                    Text("No diagram available")
                }
            }
        }
    }
}