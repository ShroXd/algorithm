package graph

interface Graph {
    val vertices: Int

    fun addEdge(a: Int, b: Int)
    fun printGraph()
}
