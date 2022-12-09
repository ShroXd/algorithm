package graph

class AdjListDirectedGraph<T>(val vertices: MutableList<Vertex<T>>) {
    fun addVertex(vertex: Vertex<T>) {
        vertices.add(vertex)
    }

    fun addEdge(v1: Vertex<T>, v2: Vertex<T>, weight: Int) {
        v1.addNeighbor(v2, weight)
        v2.addNeighbor(v1, weight)
    }

    fun removeEdge(v1: Vertex<T>, v2: Vertex<T>) {
        v1.removeNeighbor(v2)
        v2.removeNeighbor(v1)
    }

    fun printGraph() {
        for (v in vertices) {
            // print the data for the vertex
            println("Vertex: ${v.data}")

            // print the neighbors and their corresponding edge weights
            for ((neighbor, weight) in v.getNeighbor()) {
                println("Neighbor: $neighbor, Edge Weight: $weight")
            }
        }
    }
}