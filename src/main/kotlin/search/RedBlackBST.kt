package search

import java.security.Key


class RedBlackBST<K : Comparable<K>, V> {
    enum class Color {
        RED, BLACK
    }

    class Node<K, V>(val key: K, var value: V, var color: Color = Color.BLACK, var size: Int = 0) {
        var left: Node<K, V>? = null
        var right: Node<K, V>? = null
    }

    private var root: Node<K, V>? = null

    private fun isRed(n: Node<K, V>?): Boolean =
        if (n == null) false else n.color == Color.RED

    private fun size(n: Node<K, V>?): Int =
        n?.size ?: 0

    fun isEmpty(): Boolean =
        root == null

    // Search
    private fun get(key: K, node: Node<K, V>?): Node<K, V>? {
        if (node == null) {
            return null
        }

        val gap: Int = key.compareTo(node.key)
        if (gap > 0) {
            get(key, node.right)
        } else if (gap < 0) {
            get(key, node.left)
        } else {
            return node
        }

        return null
    }

    fun get(key: K): Node<K, V>? {
        return get(key, root)
    }
    fun contains(key: K): Boolean {
        return get(key) != null
    }

    private fun rotateLeft(node: Node<K, V>): Node<K, V>? {
        val temp = node.right

        node.right = temp?.left
        temp?.left = node

        temp?.color = node.color
        node.color = Color.RED

        temp?.size = node.size
        node.size = size(node.left) + size(node.right) + 1

        return temp
    }

    private fun rotateRight(node: Node<K, V>): Node<K, V>? {
        val temp = node.left

        node.left = temp?.right
        temp?.right = node

        temp?.color = node.color
        node.color = Color.RED

        temp?.size = node.size
        node.size = size(node.left) + size(node.right) + 1

        return temp
    }

    private fun flipColor(node: Node<K, V>): Node<K, V> {
        node.color = Color.RED
        node.left?.color = Color.BLACK
        node.right?.color = Color.BLACK

        return node
    }

    // Insertion
    private fun put(key: K, value: V, node: Node<K, V>?): Node<K, V>? {
        if (node == null) {
            return Node(key, value, Color.RED, 1)
        }

        var currentNode = node

        val gap: Int = key.compareTo(currentNode.key)
        if (gap > 0) {
            currentNode.right = put(key, value, currentNode.right)
        } else if (gap < 0) {
            currentNode.left = put(key, value, currentNode.left)
        } else {
            currentNode.value = value
        }

        if (isRed(currentNode.right) && !isRed(currentNode.left)) {
            currentNode = rotateLeft(currentNode)
        }
        if (isRed(currentNode?.left) && isRed(currentNode?.left?.left)) {
            currentNode = currentNode?.let { rotateRight(it) }
        }
        if (isRed(currentNode?.left) && isRed(currentNode?.right)) {
            currentNode = currentNode?.let { flipColor(it) }
        }

        currentNode?.size = size(currentNode?.left) + size(currentNode?.right) + 1

        return currentNode
    }
}
