interface IDiagramGenerator {
    suspend fun generateDiagram(graphData: String, disabledVertices: Set<String>): String
}
