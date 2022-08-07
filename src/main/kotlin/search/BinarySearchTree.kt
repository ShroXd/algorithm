package search

class BinarySearchTree<K : Comparable<K>, V> : SymbolTable<K, V> {
    data class Node<Key, Value>(val key: Key, var value: Value) {
        var left: Node<Key, Value>? = null;
        var right: Node<Key, Value>? = null;
    }

    private var root: Node<K, V>? = null;
    var size: Int = 0
        private set

    override fun put(key: K, value: V) {
        root = put(root, key, value)
    }

    private fun put(node: Node<K, V>?, key: K, value: V): Node<K, V> {
        if (node == null) return Node(key, value)

        val gap: Int = key.compareTo(node.key)

        if (gap < 0) {
            node.left = put(node.left, key, value)
        } else if (gap > 0) {
            node.right = put(node.right, key, value)
        } else {
            node.value = value
        }

        // This node is the prev node.left or prev node.right
        return node;
    }

    override fun get(key: K): V? {
        return get(root, key)
    }

    private fun get(node: Node<K, V>?, key: K): V? {
        if (node == null) return null

        val gap: Int = key.compareTo(node.key)

        return if (gap < 0) {
            get(node.left, key)
        } else if (gap > 0) {
            get(node.right, key)
        } else {
            node.value
        }
    }

    override fun containes(key: K): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(key: K) {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }
}