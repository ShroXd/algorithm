package search

class BinarySearchTree<K : Comparable<K>, V> : SymbolTable<K, V> {
    data class Node<Key, Value>(val key: Key, var value: Value) {
        var left: Node<Key, Value>? = null
        var right: Node<Key, Value>? = null
    }

    private var root: Node<K, V>? = null
    var size: Int = 0
        private set

    override fun put(key: K, value: V) {
        root = put(root, key, value)
    }

    private fun put(node: Node<K, V>?, key: K, value: V): Node<K, V> {
        if (node == null) {
            size++
            return Node(key, value)
        }

        val gap: Int = key.compareTo(node.key)

        if (gap > 0) {
            // right sub tree
            node.right = put(node.right, key, value)
        } else if (gap < 0) {
            // left sub tree
            node.left = put(node.left, key, value)
        } else {
            node.value = value
        }

        // This node is the prev node.left or prev node.right
        return node
    }

    override fun get(key: K): V? {
        return get(root, key)
    }

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

    override fun contains(key: K): Boolean {
        return contains(root, key)
    }

    private fun contains(node: Node<K, V>?, key: K): Boolean {
        if (node == null) return false

        val gap: Int = key.compareTo(node.key)

        return if (gap > 0) {
            contains(node.right, key)
        } else if (gap < 0) {
            contains(node.left, key)
        } else {
            true
        }
    }

    override fun delete(key: K) {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean = size == 0

    fun minKey(): K? {
        return minKey(root)
    }

    private fun minKey(node: Node<K, V>?): K? {
        if (node == null) return null

        return if (node.left != null) {
            minKey(node.left)
        } else {
            node.key
        }
    }

    fun maxKey(): K? {
        return maxKey(root)
    }

    private fun maxKey(node: Node<K, V>?): K? {
        if (node == null) return null

        return if (node.right != null) {
            maxKey(node.right)
        } else {
            node.key
        }
    }
}
