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
    private fun size(node: Node<K, V>?) = node?.size ?: 0

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

    private fun rotateLeft(node: Node<K, V>): Node<K, V> {
        val temp: Node<K, V> = node.right!!

        node.right = temp.left
        temp.left = node

        temp.color = node.color
        node.color = Color.RED

        return temp
    }

    private fun flipColors(node: Node<K, V>) {
        node.color = Color.RED
        node.left?.color = Color.BLACK
        node.right?.color = Color.BLACK
    }

    private fun rotateRight(node: Node<K, V>): Node<K, V> {
        val temp: Node<K, V> = node.left!!

        node.left = temp.right
        temp.right = node

        temp.color = node.color
        node.color = Color.RED

        return temp
    }

    fun put(key: K, value: V) {
        root = put(root, key, value)
        root!!.color = Color.BLACK
    }

    private fun put(node: Node<K, V>?, key: K, value: V): Node<K, V> {
        if (node == null) return Node(key, value, 1, Color.RED)

        val gap: Int = key.compareTo(node.key)

        if (gap < 0) {
            node.left = put(node.left, key, value)
        } else if (gap > 0) {
            node.right = put(node.right, key, value)
        } else {
            node.value = value
        }

        var temp: Node<K, V> = node

        if (!isRed(node.left) && isRed(node.right)) temp = rotateLeft(temp)
        if (isRed(node.left) && isRed(node.left?.left)) temp = rotateRight(temp)
        if (isRed(node.left) && isRed(node.right)) flipColors(temp)

        temp.size = size(node.left) + size(node.right) + 1

        return temp
    }
}