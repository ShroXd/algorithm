package graph

class Vertex<T>(val data: T) {
    private val neighbors = mutableMapOf<Vertex<T>, Int>()

    fun addNeighbor(vertex: Vertex<T>, weight: Int) {
        neighbors[vertex] = weight
    }

    fun removeNeighbor(vertex: Vertex<T>) {
        neighbors.remove(vertex)
    }

    fun getNeighbor(): Map<Vertex<T>, Int> {
        return neighbors
    }
}
