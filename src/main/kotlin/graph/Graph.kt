package graph

interface Graph {
    val vertices: Int
    var edges: Int

    fun addEdge(a: Int, b: Int)
    fun removeEdge(a: Int, b: Int)
    fun printGraph()
}
