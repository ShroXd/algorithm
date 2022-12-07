package graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AdjacencyListGraphTest {

    @Test
    fun `test add vertex`() {
        val g = AdjListGraph()

        g.addVertex()
        g.addVertex()
        g.addVertex()
        g.addVertex()

        assertTrue { g.vertices == 4 }
    }

    @Test
    fun `test add edge`() {
        val g = AdjListGraph()

        g.addVertex()
        g.addVertex()
        g.addVertex()
        g.addVertex()

        g.addEdge(0, 2)
        g.addEdge(1, 3)

        assertTrue { g.edges == 2 }
        assertTrue { g.adjacencyList[0].contains(2) }
        assertTrue { g.adjacencyList[1].contains(3) }
        assertFalse { g.adjacencyList[2].contains(3) }
    }

    @Test
    fun `test remove edge`() {
        val g = AdjListGraph()

        g.addVertex()
        g.addVertex()
        g.addVertex()
        g.addVertex()

        g.addEdge(0, 2)
        g.addEdge(1, 3)

        g.removeEdge(0, 2)

        assertFalse { g.adjacencyList[0].contains(2)}
    }
}

class GraphTest {
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
