package graph

class AdjListGraph(override val vertices: Int): Graph {
    override var edges: Int = 0
    // Adjacency list
    val adjacencyList: List<MutableList<Int>> = List(vertices) { mutableListOf() }

    private fun validateVertex(vararg vertices: Int) {
        vertices.forEach {
            if (it < 0 || it >= this.vertices) {
                throw IllegalArgumentException("vertex $it is not between 0 and ${it - 1}")
            }
        }
    }

    override fun addEdge(a: Int, b: Int) {
        validateVertex(a, b)
        if (!adjacencyList[a].contains(b)) {
            edges += 1
        }

        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    override fun removeEdge(a: Int, b: Int) {
        validateVertex(a, b)
        if (adjacencyList[a].contains(b)) {
            edges -= 1
        }

        adjacencyList[a].remove(b)
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
