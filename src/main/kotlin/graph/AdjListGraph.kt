package graph

class AdjListGraph(override var vertices: Int = 0): Graph {
    override var edges: Int = 0
    // Adjacency list
    val adjacencyList: MutableList<MutableList<Int>> = MutableList(vertices) { mutableListOf() }

    private fun validateVertex(vararg vertices: Int) {
        vertices.forEach {
            if (it < 0 || it >= this.vertices) {
                throw IllegalArgumentException("vertex $it is not between 0 and ${it - 1}")
            }
        }
    }

    override fun addVertex() {
        vertices += 1
        adjacencyList.add(mutableListOf())
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
