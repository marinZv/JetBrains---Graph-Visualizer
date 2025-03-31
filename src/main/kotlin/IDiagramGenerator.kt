/**
 * Interface defining the generation of a diagram based on graph data.
 */
interface IDiagramGenerator {
    /**
     * Generates a diagram based on the provided graph data.
     *
     * @param graphData A string representing the input graph data in a defined format.
     * @param disabledVertices A set of vertices that should be disabled or ignored when generating the diagram.
     * @return A string representing the generated diagram in a specific output format.
     */
    suspend fun generateDiagram(graphData: String, disabledVertices: Set<String>): String
}
