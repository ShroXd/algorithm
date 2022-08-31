package search

enum class Color {
    RED, BLACK
}

class RedBlackBST<K : Comparable<K>, V> {
    // TODO: make the key private settable
    data class Node<K:Comparable<K>, V>(var key: K, var value: V, var size: Int, var color: Color) {
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

    private fun rotateRight(node: Node<K, V>): Node<K, V> {
        val temp: Node<K, V> = node.left!!

        node.left = temp.right
        temp.right = node

        temp.color = node.color
        node.color = Color.RED

        return temp
    }

    private fun flipColors(node: Node<K, V>) {
        node.color = Color.RED
        node.left?.color = Color.BLACK
        node.right?.color = Color.BLACK
    }

    private fun moveRedLeft(node: Node<K, V>): Node<K, V> {
        var temp: Node<K, V> = node

        flipColors(temp)
        if (isRed(temp.right?.left)) {
            temp.right = rotateRight(temp.right!!)
            temp = rotateLeft(temp)
            flipColors(temp)
        }

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

    private fun min(node: Node<K, V>): Node<K, V> {
        return if (node.left == null) {
            node
        } else {
            min(node.left!!)
        }
    }

    fun deleteMin() {
        if (!isRed(root?.left) && !isRed(root?.right))  root?.color = Color.RED

        root = deleteMin(root)
        if (size(root) != 0)    root!!.color = Color.BLACK
    }

    private fun deleteMin(node: Node<K, V>?): Node<K, V>? {
        if (node?.left == null) return null

        var temp: Node<K, V> = node

        if (!isRed(node.left) && !isRed(node.right))
            temp = moveRedLeft(temp.left!!)

        temp.left = deleteMin(temp.left)

        return balance(temp)
    }

    private fun balance(node: Node<K, V>): Node<K, V> {
        var temp: Node<K, V> = node

        if (!isRed(node.left) && isRed(node.right)) temp = rotateLeft(temp)
        if (isRed(node.left) && isRed(node.left?.left)) temp = rotateRight(temp)
        if (isRed(node.left) && isRed(node.right)) flipColors(temp)

        temp.size = size(node.left) + size(node.right) + 1

        return temp
    }

    fun delete(key: K) {
        // TODO: Check if tree has the key

        if (!isRed(root?.left) && !isRed(root?.right))  root?.color = Color.RED
        root = delete(root, key)

        if (size(root) != 0)    root!!.color = Color.BLACK
    }

    private fun delete(node: Node<K, V>?, key: K): Node<K, V>? {
        if (node == null) return null

        var temp: Node<K, V> = node
        val gap: Int = key.compareTo(temp.key)

        if (gap < 0) {
            if (!isRed(temp.left) && !isRed(temp.right))
                temp = moveRedLeft(temp)
            temp.left = delete(temp.left, key)
        } else {
            if (isRed(temp.left))
                temp = rotateRight(temp)
            if (key.compareTo(temp.key) == 0 && temp.right == null)
                return null
            if (!isRed(temp.right) && !isRed(temp.right?.left))
                temp = moveRedLeft(temp)
            if (key.compareTo(temp.key) == 0) {
                val x: Node<K, V> = min(temp.right!!)
                temp.key = x.key
                temp.value = x.value
                temp.right = deleteMin(temp.right)
            } else {
                temp.right = delete(temp.right, key)
            }
        }

        return balance(temp)
    }
}
