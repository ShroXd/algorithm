package graph

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AdjacencyListUndirectedGraphTest {

    @Test
    fun `test add vertex`() {
        val g = AdjListUndirectedGraph()

        g.addVertex()
        g.addVertex()
        g.addVertex()
        g.addVertex()

        assertTrue { g.vertices == 4 }
    }

    @Test
    fun `test add edge`() {
        val g = AdjListUndirectedGraph()

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
        val g = AdjListUndirectedGraph()

        g.addVertex()
        g.addVertex()
        g.addVertex()
        g.addVertex()

        g.addEdge(0, 2)
        g.addEdge(1, 3)

        g.removeEdge(0, 2)

        assertFalse { g.adjacencyList[0].contains(2) }
    }
}

class AdjacencyMatrixUndirectedGraphTest {

    @Test
    fun `test add vertex`() {
        val g = AdjMatrixUndirectedGraph()

        g.addVertex()
        g.addVertex()
        g.addVertex()
        g.addVertex()

        assertTrue { g.vertices == 4 }
    }

    @Test
    fun `test add edge`() {
        val g = AdjMatrixUndirectedGraph()

        g.addVertex()
        g.addVertex()
        g.addVertex()
        g.addVertex()

        g.addEdge(0, 2)
        g.addEdge(1, 3)

        assertTrue { g.edges == 2 }
        assertTrue { g.adjacencyMatrix[0][2] }
        assertTrue { g.adjacencyMatrix[1][3] }
        assertFalse { g.adjacencyMatrix[1][2] }
    }

    @Test
    fun `test remove edge`() {
        val g = AdjMatrixUndirectedGraph()

        g.addVertex()
        g.addVertex()
        g.addVertex()
        g.addVertex()

        g.addEdge(0, 2)
        g.addEdge(1, 3)

        g.removeEdge(0, 2)

        assertFalse { g.adjacencyMatrix[0][2] }
    }
}

class DFSTest {

    @Test
    fun `search on single-node graph should succeed`() {
        val g = AdjListUndirectedGraph(1)

        assertTrue { depthFirstSearch(g, 0) == 1 }
    }

    @Test
    fun `search on multi-node acyclic graph should succeed`() {
        val g = AdjListUndirectedGraph(5)

        listOf(1, 2, 3, 4).forEach { g.addEdge(0, it) }

        assertTrue { depthFirstSearch(g, 0) == 5 }
        listOf(1, 2, 3, 4).forEach {
            assertTrue { depthFirstSearch(g, it) == 5 }
        }
    }

    @Test
    fun `search on multi-node cyclic graph should succeed`() {
        val g = AdjListUndirectedGraph(5)
        val vertices = listOf(1, 2, 3, 4)

        vertices.forEach { g.addEdge(0, it) }
        g.addEdge(1, 2)

        assertTrue { depthFirstSearch(g, 0) == 5 }
        vertices.forEach {
            assertTrue { depthFirstSearch(g, it) == 5 }
        }
    }
}

class BFSTest {

    @Test
    fun `search on single-node graph should succeed`() {
        val g = AdjListUndirectedGraph(1)

        assertTrue { breadthFirstSearch(g, 0) == 1 }
    }

    @Test
    fun `search on multi-node acyclic graph should succeed`() {
        val g = AdjListUndirectedGraph(5)
        val vertices = listOf(1, 2, 3, 4)

        vertices.forEach { g.addEdge(0, it) }

        assertTrue { breadthFirstSearch(g, 0) == 5 }
        vertices.forEach {
            assertTrue { breadthFirstSearch(g, it) == 5 }
        }
    }

    @Test
    fun `search on multi-node cyclic graph should succeed`() {
        val g = AdjListUndirectedGraph(5)
        val vertices = listOf(1, 2, 3, 4)

        vertices.forEach { g.addEdge(0, it) }
        g.addEdge(1, 2)

        assertTrue { breadthFirstSearch(g, 0) == 5 }
        vertices.forEach {
            assertTrue { breadthFirstSearch(g, it) == 5 }
        }
    }
}

class DFPSTest() {

    @Test
    fun `search on single-node graph should succeed`() {
        val g = AdjListUndirectedGraph(1)

        assertTrue { depthFirstPathSearch(g, 0, 0) }
    }

    @Test
    fun `search on multi-node graph with no path should fail`() {
        val g = AdjListUndirectedGraph(5)

        g.addEdge(0, 3)
        g.addEdge(3, 4)

        assertFalse { depthFirstPathSearch(g, 1, 4) }
    }

    @Test
    fun `search on multi-node graph with multiple path should succeed`() {
        val g = AdjListUndirectedGraph(5)

        // First path: 0 -> 1 -> 3 -> 4
        g.addEdge(0, 1)
        g.addEdge(1, 3)
        g.addEdge(3, 4)

        // Second path: 0 -> 2 -> 4
        g.addEdge(0, 2)
        g.addEdge(2, 4)

        assertTrue { depthFirstPathSearch(g, 0 , 4) }
    }
}

class BFPSTest() {

    @Test
    fun `search on single-node graph should succeed`() {
        val g = AdjListUndirectedGraph(1)

        assertTrue { breadthFirstPathSearch(g, 0, 0) }
    }

    @Test
    fun `search on multi-node graph with no path should fail`() {
        val g = AdjListUndirectedGraph(5)

        g.addEdge(0, 3)
        g.addEdge(3, 4)

        assertFalse { breadthFirstPathSearch(g, 1, 4) }
    }

    @Test
    fun `search on multi-node graph with multiple path should succeed`() {
        val g = AdjListUndirectedGraph(5)

        // First path: 0 -> 1 -> 3 -> 4
        g.addEdge(0, 1)
        g.addEdge(1, 3)
        g.addEdge(3, 4)

        // Second path: 0 -> 2 -> 4
        g.addEdge(0, 2)
        g.addEdge(2, 4)

        assertTrue { breadthFirstPathSearch(g, 0 , 4) }
    }
}

class VertexTest {

    @Test
    fun `test the addNeighbor method`() {
        val vertex = Vertex("Test Vertex")
        val n1 = Vertex("Neighbor 1")
        val n2 = Vertex("Neighbor 2")

        vertex.addNeighbor(n1, 10)
        vertex.addNeighbor(n2, 20)

        assertTrue { vertex.getNeighbor().size == 2 }
        assertTrue { vertex.getNeighbor()[n1] == 10 }
        assertTrue { vertex.getNeighbor()[n2] == 20 }
    }

    @Test
    fun `test the removeNeighbor method`() {
        val vertex = Vertex("Test Vertex")
        val n1 = Vertex("Neighbor 1")
        val n2 = Vertex("Neighbor 2")

        vertex.addNeighbor(n1, 10)
        vertex.addNeighbor(n2, 20)

        vertex.removeNeighbor(n2)

        assertTrue { vertex.getNeighbor().size == 1 }
        assertTrue { vertex.getNeighbor()[n1] == 10 }
        assertTrue { vertex.getNeighbor()[n2] == null }
    }
}

class AdjacencyListDirectedGraphTest {

    @Test
    fun `test add vertex`() {
        val vertex = Vertex("Test Vertex")
        val graph = AdjListDirectedGraph(mutableListOf(vertex))

        graph.addVertex(Vertex("New Vertex"))
        assertTrue { graph.vertices.size == 2 }
    }

    @Test
    fun `test add edge`() {
        val vertex = Vertex("Test Vertex")
        val graph = AdjListDirectedGraph(mutableListOf(vertex))

        val newVertex = Vertex("New Vertex")
        graph.addVertex(newVertex)

        graph.addEdge(vertex, newVertex, 30)

        assertTrue { vertex.getNeighbor().size == 1 }
        assertTrue { vertex.getNeighbor()[newVertex] == 30 }
    }

    @Test
    fun `test remove edge`() {
        val vertex = Vertex("Test Vertex")
        val graph = AdjListDirectedGraph(mutableListOf(vertex))

        val newVertex = Vertex("New Vertex")
        graph.addVertex(newVertex)
        graph.addEdge(vertex, newVertex, 30)

        graph.removeEdge(vertex, newVertex)

        assertTrue { vertex.getNeighbor().isEmpty() }
        assertTrue { vertex.getNeighbor()[newVertex] == null }
    }
}
