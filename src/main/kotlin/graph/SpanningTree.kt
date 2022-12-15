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
