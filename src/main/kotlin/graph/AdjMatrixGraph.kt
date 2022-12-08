package graph

class AdjMatrixGraph(override var vertices: Int = 0): Graph {
    override var edges: Int = 0
    // 2D list
    val adjacencyMatrix: MutableList<MutableList<Boolean>> = MutableList(vertices) { MutableList(vertices) { false } }
    
    private fun validateVertex(vararg vertices: Int) {
        vertices.forEach {
            if (it < 0 || it > this.vertices) {
                throw IllegalArgumentException("vertex $it is not between 0 and ${it - 1}")
            }
        }
    }

    override fun addVertex() {
        vertices += 1
        adjacencyMatrix.map { it.add(false) }
        adjacencyMatrix.add(MutableList(vertices) { false })
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