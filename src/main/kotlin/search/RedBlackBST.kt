package search

enum class Color {
    RED, BLACK
}

class RedBlackBST<K : Comparable<K>, V> {
    data class Node<K : Comparable<K>, V>(val key: K, var value: V, var size: Int = 0, var color: Color = Color.BLACK) {
        var left: Node<K, V>? = null
        var right: Node<K, V>? = null
    }

    private var root: Node<K, V>? = null

    fun size(): Int = root?.size ?: 0
    fun size(node: Node<K, V>?): Int = node?.size ?: 0

    private fun isRed(node: Node<K, V>?): Boolean = node?.color == Color.RED

    private fun rotateLeft(node: Node<K, V>): Node<K, V> {
        // We will check it before invoke this method
        val temp = node.right!!

        node.right = temp.left
        temp.left = node

        temp.color = node.color
        node.color = Color.RED

        temp.size = node.size
        node.size = 1 + size(node.left) + size(node.right)

        return temp
    }

    private fun rotateRight(node: Node<K, V>): Node<K, V> {
        // We will check it before invoke this method
        val temp = node.left!!

        node.left = temp.right
        temp.right = node

        temp.color = node.color
        node.color = Color.RED

        temp.size = node.size
        node.size = 1 + size(node.left) + size(node.right)

        return temp
    }

    private fun flipColors(node: Node<K, V>) {
        node.color = Color.RED
        node.left?.color = Color.BLACK
        node.right?.color = Color.BLACK
    }

    fun put(key: K, value: V) {
        root = put(root, key, value)
        root!!.color = Color.BLACK
    }

    private fun put(node: Node<K, V>?, key: K, value: V): Node<K, V> {
        if (node == null) return Node(key, value, 1, Color.RED)

        var temp: Node<K, V> = node
        val gap: Int = key.compareTo(node.key)

        // Add the new value
        if (gap < 0) {
            node.left = put(node.left, key, value)
        } else if (gap > 0) {
            node.right = put(node.right, key, value)
        } else {
            node.value = value
        }

        // Fix the red-black tree
        if (isRed(node.right) && isRed(node.left)) temp = rotateLeft(temp)
        if (isRed(node.left) && isRed(node.left?.left)) temp = rotateRight(temp)
        if (isRed(node.left) && isRed(node.right)) flipColors(temp)

        temp.size = size(temp.left) + size(temp.right) + 1

        return temp
    }
}