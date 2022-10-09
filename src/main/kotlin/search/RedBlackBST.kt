package search


class RedBlackBST<K : Comparable<K>, V> {
    enum class Color {
        RED, BLACK
    }

    class Node<K, V>(var key: K, var value: V, var color: Color = Color.BLACK, var size: Int = 0) {
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

    private fun rotateLeft(node: Node<K, V>): Node<K, V> {
        if (node.right == null) {
            return node
        }
        val temp = node.right!!

        node.right = temp.left
        temp.left = node

        temp.color = node.color
        node.color = Color.RED

        temp.size = node.size
        node.size = size(node.left) + size(node.right) + 1

        return temp
    }

    private fun rotateRight(node: Node<K, V>): Node<K, V> {
        if (node.left == null) {
            return node
        }
        val temp = node.left!!

        node.left = temp.right
        temp.right = node

        temp.color = node.color
        node.color = Color.RED

        temp.size = node.size
        node.size = size(node.left) + size(node.right) + 1

        return temp
    }

    private fun flipColor(node: Node<K, V>): Node<K, V> {
        node.color = Color.RED
        node.left?.color = Color.BLACK
        node.right?.color = Color.BLACK

        return node
    }

    private fun moveRedLeft(node: Node<K, V>): Node<K, V> {
        var cNode = node

        if (isRed(cNode.right?.left)) {
            cNode.right = cNode.right?.let { rotateRight(it) }
            cNode = rotateLeft(cNode)
            flipColor(cNode)
        }

        return cNode
    }

    private fun moveRedRight(node: Node<K, V>): Node<K, V> {
        var cNode = node

        flipColor(cNode)
        if (isRed(cNode.left?.left)) {
            cNode = rotateRight(cNode)
            flipColor(cNode)
        }

        return cNode
    }

    private fun balance(node: Node<K, V>): Node<K, V> {
        var cNode = node

        if (isRed(cNode.right) && !isRed(cNode.left)) {
            cNode = rotateLeft(cNode)
        }
        if (isRed(cNode.left) && isRed(cNode.left?.left)) {
            cNode = rotateRight(cNode)
        }
        if (isRed(cNode.left) && isRed(cNode.right)) {
            cNode = flipColor(cNode)
        }

        cNode.size = size(cNode.left) + size(cNode.right) + 1

        return cNode
    }

    private fun min(node: Node<K, V>): Node<K, V> =
        if (node.left == null) {
            node
        } else {
            min(node.left!!)
        }

    private fun deleteMin(node: Node<K, V>): Node<K, V>? {
        var cNode = node

        if (cNode.left == null) {
            return null
        }

        if (!isRed(cNode.left) && !isRed(cNode.left?.left)) {
            cNode = moveRedLeft(cNode)
        }

        cNode.left = deleteMin(cNode.left!!)

        return balance(cNode)
    }

    // Search
    private fun get(key: K, node: Node<K, V>?): V? {
        var cNode = node

        while (cNode != null) {
            if (key < cNode.key) {
                cNode = cNode.left
            } else if (key > cNode.key) {
                cNode = cNode.right
            } else {
                return cNode.value
            }
        }

        return null
    }

    fun get(key: K): V? {
        return get(key, root)
    }
    fun contains(key: K): Boolean {
        return get(key) != null
    }

    // Insertion
    private fun put(key: K, value: V, node: Node<K, V>?): Node<K, V>? {
        if (node == null) {
            return Node(key, value, Color.RED, 1)
        }

        val gap: Int = key.compareTo(node.key)
        if (gap > 0) {
            node.right = put(key, value, node.right)
        } else if (gap < 0) {
            node.left = put(key, value, node.left)
        } else {
            node.value = value
        }

        return balance(node)
    }
    fun put(key: K, value: V) {
        root = put(key, value, root)
        root?.color = Color.BLACK
    }

    // Deletion
    private fun delete(key: K, node: Node<K, V>): Node<K, V>? {
        var cNode = node

        if (key < node.key) {
            if (!isRed(cNode.left) && !isRed(cNode.left?.left)) {
                cNode = moveRedLeft(cNode)
            }
            cNode.left = delete(key, cNode.left!!)
        } else {
            if (isRed(cNode.left)) {
                cNode = rotateRight(cNode)
            }
            // bottom
            if (key == cNode.key && cNode.right == null) {
                return null
            }
            if (!isRed(cNode.right) && !isRed(cNode.right?.left)) {
                cNode = moveRedRight(cNode)
            }

            // middle
            if (key == node.key) {
                val minNode = min(cNode.right!!)
                cNode.key = minNode.key
                cNode.value = minNode.value
                cNode.right = deleteMin(cNode.right!!)
            } else {
                cNode.right = delete(key, cNode.right!!)
            }
        }

        return balance(cNode)
    }
    fun delete(key: K) {
        if (!contains(key)) {
            return
        }

        if (!isRed(root?.left) && !isRed(root?.right)) {
            root?.color = Color.RED
        }

        // TODO: fix this
        root = delete(key, root!!)

        if (!isEmpty()) {
            root?.color = Color.BLACK
        }
    }
}
