package graph

import java.util.LinkedList

fun depthFirstSearch(graph: AdjListGraph, vertex: Int): Int {
    val marked = MutableList(graph.vertices) { false }
    var count = 0

    fun dfs(g: Graph, v: Int): Int {
        count += 1
        marked[v] = true

        for (idx in graph.adjacencyList[v]) {
            if (!marked[idx]) {
                // Do sth for this vertex
                dfs(g, idx)
            }
        }

        return count
    }

    return dfs(graph, vertex)
}

fun breadthFirstSearch(graph: AdjListGraph, vertex: Int): Int {
    val visited = MutableList(graph.vertices) { false }
    var count = 0

    val q = LinkedList<Int>()
    visited[vertex] = true
    q.add(vertex)
    count += 1

    while (!q.isEmpty()) {
        val curr = q.poll()

        for (adj in graph.adjacencyList[curr]) {
            if (!visited[adj]) {
                visited[adj] = true
                count += 1
                q.add(adj)
            }
        }
    }

    return count
}

fun depthFirstPathSearch(graph: AdjListGraph, start: Int, destination: Int): Boolean {
    val visited: MutableList<Boolean> = MutableList(graph.vertices) { false }

    fun dfps(graph: AdjListGraph, current: Int): Boolean {
        // Path found
        if (current == destination) {
            return true
        }

        visited[current] = true

        for (n in graph.adjacencyList[current]) {
            if (!visited[n]) {
                // Recursive search from neighbor
                if (dfps(graph, n)) {
                    return true
                }
            }
        }

        return false
    }

    return dfps(graph, start)
}

fun breadthFirstPathSearch(graph: AdjListGraph, start: Int, destination: Int): Boolean {
    val visited: MutableList<Boolean> = MutableList(graph.vertices) { false }
    val q = LinkedList<Int>()

    q.add(start)
    visited[start] = true

    while (q.isNotEmpty()) {
        val curr = q.remove()
        if (curr == destination) {
            // path found
            return true
        }

        for (n in graph.adjacencyList[curr]) {
            if (!visited[n]) {
                q.add(n)
                visited[n] = true
            }
        }
    }

    // path not found
    return false
}
