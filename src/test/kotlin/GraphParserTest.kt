import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GraphParserTest {

    private val graphParser = GraphParser()

    @Test
    fun `should parse valid graph input correctly`() {
        val input = """
            A -> B
            B -> C
            C -> D
        """.trimIndent()

        val result = graphParser.parseGraphInput(input)

        val expected = setOf("A", "B", "C", "D")
        assertEquals(expected, result)
    }

    @Test
    fun `should ignore lines without an arrow`() {
        val input = """
            A B C
            D E F
            X Y Z
        """.trimIndent()

        val result = graphParser.parseGraphInput(input)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `should ignore empty lines`() {
        val input = """
            A -> B
            
            C -> D
        """.trimIndent()

        val result = graphParser.parseGraphInput(input)

        val expected = setOf("A", "B", "C", "D")
        assertEquals(expected, result)
    }

    @Test
    fun `should handle duplicate vertices gracefully`() {
        val input = """
            A -> B
            B -> C
            A -> C
            B -> A
        """.trimIndent()

        val result = graphParser.parseGraphInput(input)

        val expected = setOf("A", "B", "C")
        assertEquals(expected, result)
    }

    @Test
    fun `should handle graph with a single vertex`() {
        val input = """
            A -> B
        """.trimIndent()

        val result = graphParser.parseGraphInput(input)

        val expected = setOf("A", "B")
        assertEquals(expected, result)
    }

}
