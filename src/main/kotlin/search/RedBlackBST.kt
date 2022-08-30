package search

enum class Color {
    RED, BLACK
}

class RedBlackBST<K : Comparable<K>, V> {
    data class Node<K:Comparable<K>, V>(val key: K, var value: V, var size: Int, var color: Color) {
        var left: Node<K, V>? = null
        var right: Node<K, V>? = null
    }

    private var root: Node<K, V>? = null

    private fun isRed(node: Node<K, V>?) = node?.color == Color.RED

    fun get(key: K): V? = get(root, key)
    private fun get(node: Node<K, V>?, key: K): V? {
        if (node == null) return null

        val gap: Int = key.compareTo(node.key)
        return if (gap > 0) {
            get(node.right, key)
        } else if (gap < 0) {
            get(node.left, key)
        } else {
            node.value
        }
    }

    fun put(key: K, value: V) {
        TODO("Implement this function")
    }
}