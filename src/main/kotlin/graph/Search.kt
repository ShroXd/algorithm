package graph

import java.util.LinkedList

fun depthFirstSearch(graph: AdjListUndirectedGraph, vertex: Int): Int {
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

fun breadthFirstSearch(graph: AdjListUndirectedGraph, vertex: Int): Int {
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

fun depthFirstPathSearch(graph: AdjListUndirectedGraph, start: Int, destination: Int): Boolean {
    val visited: MutableList<Boolean> = MutableList(graph.vertices) { false }

    fun dfps(graph: AdjListUndirectedGraph, current: Int): Boolean {
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

fun breadthFirstPathSearch(graph: AdjListUndirectedGraph, start: Int, destination: Int): Boolean {
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

fun <T> depthFirstSearch(vertex: Vertex<T>, fn: (v: Vertex<T>) -> Unit) {
    val visited: MutableSet<Vertex<T>> = mutableSetOf()

    fun dfs(v: Vertex<T>) {
        if (v in visited) return

        visited.add(v)
        fn(v)

        v.getNeighbor().forEach {
            dfs(it.key)
        }
    }

    return dfs(vertex)
}

fun <T> breadthFirstSearch(vertex: Vertex<T>, fn: (v: Vertex<T>) -> Unit) {
    val visited: MutableSet<Vertex<T>> = mutableSetOf()
    val queue = LinkedList<Vertex<T>>()

    visited.add(vertex)
    queue.add(vertex)

    while (queue.isNotEmpty()) {
        val curr = queue.remove()
        fn(curr)

        for (neighbor in curr.getNeighbor()) {
            if (!visited.contains(neighbor.key)) {
                visited.add(neighbor.key)
                queue.add(neighbor.key)
            }
        }
    }
}
