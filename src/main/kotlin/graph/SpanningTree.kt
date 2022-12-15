package graph

import java.util.*

fun <T> primMST(graph: GraphWeighted<T>): MutableList<Edge<T>> {
    val mst = mutableListOf<Edge<T>>()
    // Record the visited vertices to prevent infinity cycle
    val visited = mutableSetOf<T>()
    // Create a priority queue of edges to order them by weight
    val queue = PriorityQueue<Edge<T>> { e1, e2 -> e1.weight - e2.weight }

    val firstVertex = graph.vertices()[0]
    visited.add(firstVertex)
    graph.edges(firstVertex)?.let { queue.addAll(it) }

    while (queue.isNotEmpty()) {
        val edge = queue.poll()

        // If two vertices have already been visited, skip it and move on to the next edge
        if (visited.contains(edge.start) && visited.contains(edge.end)) continue

        mst.add(edge)

        // Add non-visited vertices
        if (!visited.contains(edge.start)) {
            visited.add(edge.start)
            graph.edges(edge.start)?.let { queue.addAll(it) }
        }
        if (!visited.contains(edge.end)) {
            visited.add(edge.end)
            graph.edges(edge.end)?.let { queue.addAll(it) }
        }
    }

    return mst
}

fun <T> kruskalMST(graph: GraphWeighted<T>): MutableList<Edge<T>> {
    val mst = mutableListOf<Edge<T>>()
    val edges = graph.adjList().flatMap { it.value }.sortedBy { it.weight }
    val ds = DisjointSet<T>()

    for (edge in edges) {
        if (!ds.isSameSet(edge.start, edge.end)) {
            mst.add(edge)
            ds.union(edge.start, edge.end)
        }
    }

    return mst
}

class DisjointSet<T> {
    private val parent = mutableMapOf<T, T>()

    private fun find(element: T): T {
        var root = element
        while (parent.contains(root) && root != parent[root]) {
            root = parent[root]!!
        }

        return root
    }

    fun union(e1: T, e2: T) {
        val r1 = find(e1)
        val r2 = find(e2)

        if (r1 != r2) {
            parent[r1] = r2
        }
    }

    fun isSameSet(e1: T, e2: T): Boolean = find(e1) == find(e2)
}




















