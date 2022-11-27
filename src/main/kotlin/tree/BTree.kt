package tree

class BTree<K : Comparable<K>>(private val t: Int) {
    data class Node<K>(var isLeaf: Boolean) {
        var keys: MutableList<K> = mutableListOf()
        var children: MutableList<Node<K>> = mutableListOf()

        var numOfKeys: Int = 0
    }

    private val capacityOfKeys: Int = 2 * t - 1
    private var root: Node<K>? = null

    private fun search(key: K, node: Node<K>?): Node<K>? {
        if (node == null) {
            return null
        } else {
            var idx = 0
            while (idx < node.keys.size && key > node.keys[idx]) {
                idx += 1
            }

            return if (idx == node.keys.size) {
                search(key, node.children[idx - 1])
            } else if (key == node.keys[idx]) {
                node
            } else if (node.isLeaf) {
                null
            } else {
                search(key, node.children[idx])
            }
        }
    }

    fun search(key: K): Node<K>? {
        return search(key, root)
    }

    // Node is the parent node
    // Idx is the index of child node which is going to be split
    private fun splitChild(idx: Int, node: Node<K>) {
        val currentChild = node.children[idx]
        val newChild = Node<K>(currentChild.isLeaf)

        newChild.keys = currentChild.keys.slice(0 until t).toMutableList()
        if (currentChild.isLeaf) {
            newChild.children = currentChild.children.slice(0 until t).toMutableList()
        }

        node.children.add(newChild)
        node.keys[t] = currentChild.keys[t]

        currentChild.keys = currentChild.keys.slice(t + 1 until currentChild.keys.size).toMutableList()
        if (currentChild.isLeaf) {
            currentChild.children = currentChild.children.slice(t until currentChild.children.size).toMutableList()
        }
    }

    // Insert the key into a non-full node
    private fun insertNonFull(key: K, node: Node<K>) {
        // Initialize index as index of rightmost element
        var idx = node.keys.size - 1

        if (node.isLeaf) {
            while (idx >= 0 && node.keys[idx] > key) {
                idx -= 1
            }

            // Stop at the left of correct position
            node.keys.add(idx + 1, key)
        } else {
            // When while end, the index will be left of the last larger item
            while (idx >= 0 && node.keys[idx] > key) {
                idx -= 1
            }

            // If the found child is full
            if (node.children[idx + 1].keys.size == 2 * t - 1) {
                splitChild(idx + 1, node)
                if (node.keys[idx + 1] < key) {
                    idx += 1
                }
            }

            insertNonFull(key, node.children[idx + 1])
        }
    }

    fun insert(key: K) {
        if (root == null) {
            val temp = Node<K>(true)
            temp.keys.add(key)
        } else {
            val currentRoot = root!!
            if (currentRoot.keys.size == 2 * t - 1) {
                val newRoot = Node<K>(false)
                newRoot.children.add(currentRoot)
                splitChild(0, newRoot)

                var idx = 0
                if (newRoot.keys[0] < key) {
                    idx += 1
                }
                insertNonFull(key, newRoot.children[idx])

                root = newRoot
            } else {
                insertNonFull(key, currentRoot)
            }
        }
    }
}
