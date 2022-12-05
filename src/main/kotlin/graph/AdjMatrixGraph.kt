package graph

class AdjMatrixGraph(private val vertices: Int) {
    var edges: Int = 0
        private set

    // 2D list
    private val adjacencyMatrix: List<MutableList<Boolean>> = List(vertices) { MutableList(vertices) { false } }

    fun addEdge(a: Int, b: Int) {
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

    fun printGraph() {
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