package graph

class Graph(private var vertices: Int) {
    private var edges: Int = 0
    // Adjacency list
    var adjacencyList: MutableList<MutableList<Int>> = mutableListOf()

    init {
        // Initializes the adjacency matrix for empty graph
        for (v in 0 until vertices) {
            adjacencyList.add(mutableListOf())
        }
    }

    private fun validateVertex(vararg vertices: Int) {
        vertices.forEach {
            if (it < 0 || it >= this.vertices) {
                throw java.lang.IllegalArgumentException("vertex $it is not between 0 and ${it - 1}")
            }
        }
    }

    fun addEdge(a: Int, b: Int) {
        validateVertex(a, b)

        edges += 1
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun degree(vertex: Int): Int {
        validateVertex(vertex)
        return adjacencyList[vertex].size
    }

    fun printGraph() {
        adjacencyList.forEachIndexed { idx, it ->
            println("\nAdjacency list of vertex $idx")
            print("head")
            it.forEach { vertex ->
                print(" -> $vertex")
            }
            println()
        }
    }
}
