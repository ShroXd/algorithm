package graph

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
