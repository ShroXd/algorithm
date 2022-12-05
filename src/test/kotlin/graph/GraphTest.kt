package graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GraphTest {

    @Test
    fun `test graph which is represented by adjacency-list`() {
        val g = Graph(6)

        g.addEdge(0, 2)
        g.addEdge(1, 3)

        g.printGraph()

        listOf(0, 2, 1, 3).forEach {
            assertEquals(1, g.degree(it))
        }
    }

    @Test
    fun `test graph which is represented by matrix`() {
        val g = AdjMatrixGraph(6)

        g.addEdge(0, 2)
        g.addEdge(1, 3)
        g.printGraph()

        assertEquals(2, g.edges)

        g.removeEdge(1, 3)
        g.printGraph()

        assertEquals(1, g.edges)
    }
}
