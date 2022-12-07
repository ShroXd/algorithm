package graph

class AdjListGraph(vertices: Int): Graph {
    override val vertices: Int
    var edges: Int = 0
        private set
    // Adjacency list
    var adjacencyList: MutableList<MutableList<Int>> = mutableListOf()

    init {
        this.vertices = vertices
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

    override fun addEdge(a: Int, b: Int) {
        validateVertex(a, b)

        edges += 1
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun degree(vertex: Int): Int {
        validateVertex(vertex)
        return adjacencyList[vertex].size
    }

    override fun printGraph() {
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
