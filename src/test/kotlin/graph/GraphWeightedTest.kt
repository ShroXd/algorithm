package graph

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GraphWeightedTest {

    @Test
    fun `test init a graph`() {
        val directedGraph = GraphWeighted<Int>(true)
        val undirectedGraph = GraphWeighted<Int>(false)

        assertTrue { directedGraph.numOfVertex() == 0 }
        assertTrue { undirectedGraph.numOfVertex() == 0 }

        val verticesForDirectedGraph = arrayOf(1, 3, 5, 7, 9)
        directedGraph.addVertices(*verticesForDirectedGraph)

        val verticesForUndirectedGraph = arrayOf(2, 4, 6, 10)
        undirectedGraph.addVertices(*verticesForUndirectedGraph)

        assertTrue { directedGraph.numOfVertex() == verticesForDirectedGraph.size }
        assertTrue { undirectedGraph.numOfVertex() == verticesForUndirectedGraph.size }
        verticesForDirectedGraph.forEach { assertTrue { directedGraph.hasVertex(it) } }
        verticesForUndirectedGraph.forEach { assertTrue { undirectedGraph.hasVertex(it) } }
    }

    @Test
    fun `add edges`() {
        val directedGraph = GraphWeighted<Int>(true)
        val undirectedGraph = GraphWeighted<Int>(false)

        val verticesForDirectedGraph = arrayOf(1, 3, 5, 7, 9)
        directedGraph.addVertices(*verticesForDirectedGraph)
        directedGraph.addEdge(1, 3, 10)
        directedGraph.addEdge(3, 7, 20)

        assertTrue { directedGraph.hasEdge(1, 3) }
        assertTrue { directedGraph.hasEdge(3, 7) }
        assertFalse { directedGraph.hasEdge(1, 5) }

        val verticesForUndirectedGraph = arrayOf(2, 4, 6, 10)
        undirectedGraph.addVertices(*verticesForUndirectedGraph)
        undirectedGraph.addEdge(2, 4, 10)
        undirectedGraph.addEdge(2, 10, 10)
        undirectedGraph.addEdge(4, 6, 20)

        assertTrue { undirectedGraph.hasEdge(2, 4) }
        assertTrue { undirectedGraph.hasEdge(2, 10) }
        assertTrue { undirectedGraph.hasEdge(4, 6) }
        assertFalse { undirectedGraph.hasEdge(2, 6) }
    }

    @Test
    fun `remove edges`() {
        val directedGraph = GraphWeighted<Int>(true)

        val verticesForDirectedGraph = arrayOf(1, 3, 5, 7, 9)
        directedGraph.addVertices(*verticesForDirectedGraph)
        directedGraph.addEdge(1, 3, 10)
        directedGraph.addEdge(3, 7, 20)

        directedGraph.removeEdge(3, 7)
        assertFalse { directedGraph.hasEdge(3, 7) }

        val undirectedGraph = GraphWeighted<Int>(false)

        val verticesForUndirectedGraph = arrayOf(2, 4, 6, 10)
        undirectedGraph.addVertices(*verticesForUndirectedGraph)
        undirectedGraph.addEdge(2, 4, 10)
        undirectedGraph.addEdge(2, 10, 10)
        undirectedGraph.addEdge(4, 6, 20)

        undirectedGraph.removeEdge(4, 6)
        assertFalse { directedGraph.hasEdge(4, 6) }
    }

    @Test
    fun `remove vertices`() {
        val directedGraph = GraphWeighted<Int>(true)

        val verticesForDirectedGraph = arrayOf(1, 3, 5, 7, 9)
        directedGraph.addVertices(*verticesForDirectedGraph)
        directedGraph.addEdge(1, 3, 10)
        directedGraph.addEdge(3, 7, 20)

        directedGraph.removeVertex(3)
        assertFalse { directedGraph.hasVertex(3) }
        assertFalse { directedGraph.hasEdge(1, 3) }
        assertFalse { directedGraph.hasEdge(3, 7) }

        val undirectedGraph = GraphWeighted<Int>(false)

        val verticesForUndirectedGraph = arrayOf(2, 4, 6, 10)
        undirectedGraph.addVertices(*verticesForUndirectedGraph)
        undirectedGraph.addEdge(2, 4, 10)
        undirectedGraph.addEdge(2, 10, 10)
        undirectedGraph.addEdge(4, 6, 20)

        undirectedGraph.removeVertex(2)
        assertFalse { undirectedGraph.hasVertex(2) }
        assertFalse { undirectedGraph.hasEdge(2, 4) }
        assertFalse { undirectedGraph.hasEdge(10 ,2) }
        assertTrue { undirectedGraph.hasEdge(4, 6) }
    }
}
