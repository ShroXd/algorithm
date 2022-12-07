package graph

class AdjMatrixGraph(override val vertices: Int): Graph {
    override var edges: Int = 0
    override fun addVertex() {
        TODO("Not yet implemented")
    }

    // 2D list
    private val adjacencyMatrix: List<MutableList<Boolean>> = List(vertices) { MutableList(vertices) { false } }
    
    private fun validateVertex(vararg vertices: Int) {
        vertices.forEach {
            if (it < 0 || it > this.vertices) {
                throw IllegalArgumentException("vertex $it is not between 0 and ${it - 1}")
            }
        }
    }

    override fun addEdge(a: Int, b: Int) {
        validateVertex(a, b)
        if (!adjacencyMatrix[a][b]) {
            edges += 1
        }

        adjacencyMatrix[a][b] = true
        adjacencyMatrix[b][a] = true
    }

    override fun removeEdge(a: Int, b: Int) {
        validateVertex(a, b)
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