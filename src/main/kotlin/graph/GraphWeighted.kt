package graph

class Edge<T>(start: T, end: T, val weight: Int) {
    val connect: Pair<T, T> = start to end
}

class GraphWeighted<T>(private val isDirected: Boolean) {
    private val adj: MutableMap<T, MutableList<Edge<T>>> = mutableMapOf()

    fun addVertices(vararg vertices: T) {
        vertices.forEach {
            if (!adj.contains(it)) {
                adj[it] = mutableListOf()
            }
        }
    }

    fun addEdge(a: T, b: T, weight: Int) {
        addVertices(a, b)

        adj[a]?.add(Edge(a, b, weight))
        // If Graph is not directed
        if (!isDirected) {
            adj[b]?.add(Edge(b, a, weight))
        }
    }

    fun removeVertex(v: T) {
        if (isDirected) {
            adj.keys.forEach { key ->
                adj[key]?.remove(adj[key]?.find {
                    it.connect.second == v
                })
            }
        } else {
            adj[v]?.forEach { key ->
                adj[key.connect.second]?.remove(adj[key.connect.second]?.find {
                    it.connect.second == v
                })
            }
        }
        adj.remove(v)
    }

    fun removeEdge(a: T, b: T) {
        val v1 = adj[a] ?: throw Error("Graph doesn't have $a")
        val v2 = adj[b] ?: throw Error("Graph doesn't have $b")

        v1.remove(v1.find { it.connect.second == b })
        if (!isDirected) {
            v2.remove(v2.find { it.connect.second == a })
        }
    }

    fun hasVertex(v: T): Boolean = adj.containsKey(v)

    fun hasEdge(a: T, b: T): Boolean {
        return if (isDirected) {
            adj[a]?.find { it.connect.second == b } != null
        } else {
            adj[a]?.find { it.connect.second == b } != null && adj[b]?.find { it.connect.second == a } != null
        }
    }

    fun numOfVertex() = adj.size
}