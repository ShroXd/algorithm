package graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SpanningTreeTest {

    @Test
    fun `test prim minimum spanning tree`() {
        val graph = GraphWeighted<String>(false)

        graph.addVertices(*arrayOf("A", "B", "C", "D", "E", "F", "G"))
        graph.addEdge("A", "D", 3)
        graph.addEdge("A", "C", 3)
        graph.addEdge("A", "B", 2)
        graph.addEdge("B", "C", 4)
        graph.addEdge("B", "E", 3)
        graph.addEdge("C", "F", 6)
        graph.addEdge("C", "E", 1)
        graph.addEdge("D", "F", 7)
        graph.addEdge("E", "F", 8)
        graph.addEdge("F", "G", 9)

        assertEquals(24, primMST(graph).fold(0) { a, b -> a + b.weight })
    }

    @Test
    fun `test kruskal minimum spanning tree`() {
        val graph = GraphWeighted<String>(true)

        graph.addVertices(*arrayOf("A", "B", "C", "D", "E", "F", "G"))
        graph.addEdge("A", "D", 3)
        graph.addEdge("A", "C", 3)
        graph.addEdge("A", "B", 2)
        graph.addEdge("B", "C", 4)
        graph.addEdge("B", "E", 3)
        graph.addEdge("C", "E", 1)
        graph.addEdge("C", "D", 5)
        graph.addEdge("D", "F", 7)
        graph.addEdge("E", "F", 8)
        graph.addEdge("F", "G", 9)

        assertEquals(25, kruskalMST(graph).fold(0) { a, b -> a + b.weight })
    }
}
