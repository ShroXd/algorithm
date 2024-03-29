package graph

import java.util.PriorityQueue

class Edge<T>(start: T, end: T, val weight: Int) {
    val connect: Pair<T, T> = start to end

    val start get() = connect.first
    val end get() = connect.second
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
                    it.end == v
                })
            }
        } else {
            adj[v]?.forEach { key ->
                adj[key.end]?.remove(adj[key.end]?.find {
                    it.end == v
                })
            }
        }
        adj.remove(v)
    }

    fun removeEdge(a: T, b: T) {
        val v1 = adj[a] ?: throw Error("Graph doesn't have $a")
        val v2 = adj[b] ?: throw Error("Graph doesn't have $b")

        v1.remove(v1.find { it.end == b })
        if (!isDirected) {
            v2.remove(v2.find { it.end == a })
        }
    }

    fun hasVertex(v: T): Boolean = adj.containsKey(v)

    fun hasEdge(a: T, b: T): Boolean {
        return if (isDirected) {
            adj[a]?.find { it.end == b } != null
        } else {
            adj[a]?.find { it.end == b } != null && adj[b]?.find { it.end == a } != null
        }
    }

    fun numOfVertex(): Int = adj.size

    fun vertices(): MutableList<T> = adj.keys.toMutableList()
    fun edges(v: T): MutableList<Edge<T>>? = adj[v]
    fun adjList(): MutableMap<T, MutableList<Edge<T>>> = adj
}
