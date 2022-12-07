package graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GraphTest {

    @Test
    fun `test graph which is represented by adjacency-list`() {
        val g = AdjListGraph(6)

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

    @Test
    fun `test depth first search on adjacency list graph`() {
        val g = AdjListGraph(10)

        g.addEdge(0, 3)
        g.addEdge(0, 7)
        g.addEdge(2, 6)

        assertEquals(3, depthFirstSearch(g, 0))
        assertEquals(1, depthFirstSearch(g, 1))
        assertEquals(2, depthFirstSearch(g, 2))
    }

    @Test
    fun `test breadth first search on adjacency list graph`() {
        val g = AdjListGraph(5)


        g.addEdge(0, 0)
        g.addEdge(0, 1)
        g.addEdge(0, 2)
        g.addEdge(0, 3)
        g.addEdge(0, 4)

        assertEquals(5, breadthFirstSearch(g, 0))
    }
}
