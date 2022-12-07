package graph

class AdjMatrixGraph(vertices: Int): Graph {
    override val vertices: Int
    var edges: Int = 0
        private set

    // 2D list
    private val adjacencyMatrix: List<MutableList<Boolean>> = List(vertices) { MutableList(vertices) { false } }

    init {
        this.vertices = vertices
    }

    fun vertices(): Int {
        return this.vertices
    }

    override fun addEdge(a: Int, b: Int) {
        if (!adjacencyMatrix[a][b]) {
            edges += 1
        }

        adjacencyMatrix[a][b] = true
        adjacencyMatrix[b][a] = true
    }

    fun removeEdge(a: Int, b: Int) {
        if (adjacencyMatrix[a][b]) {
            edges -= 1
        }

        adjacencyMatrix[a][b] = false
        adjacencyMatrix[b][a] = false
    }

    override fun printGraph() {
        adjacencyMatrix.forEachIndexed { idx, it ->
            print("$idx : ")
            for (i in 0 until it.size) {
                print("${it[i]} ")
            }
            print("\n")
            println()
        }
    }
}