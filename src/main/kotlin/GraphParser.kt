class GraphParser {

    fun parseGraphInput(input: String): Set<String> {

        return input
            .lines()
            .filter { it.contains("->") }
            .flatMap { it.split("->").map { vertex -> vertex.trim() } }
            .toSet()
    }

}