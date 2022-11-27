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
            return search(key, root)
        } else {
            var idx = 0
            while (idx < node.numOfKeys && node.keys[idx] < key) {
                idx += 1
            }

            if (idx < node.numOfKeys) {
                if (node.keys[idx] == key) {
                    return node
                } else {
                    return search(key, node.children[idx])
                }
            } else if (node.isLeaf) {
                return null
            } else {
                return search(key, node.children[idx])
            }
        }
    }

    fun search(key: K): Node<K>? {
        return search(key, root)
    }

    // Node is the parent node
    // Idx is the index of child node which is going to be split
    private fun splitChild(idx: Int, node: Node<K>) {
        val childNode = node.children[idx]
        // ? How to decide the leaf
        val newNode = Node<K>(true)

        // Idx is the child node's index
        // Each key in the child node is smaller than the key in the parent node
        // Thus we can put the middle key in the parent node with idx position
        node.keys.add(idx, childNode.keys[t - 1])
        node.numOfKeys += 1

        // left child node, right child node
        // [t, t), [t, 2t)
        newNode.keys = childNode.keys.slice(0 until t - 1).toMutableList()
        childNode.keys = childNode.keys.slice(t until 2 * t - 1).toMutableList()

        newNode.numOfKeys = newNode.keys.size
        childNode.numOfKeys = childNode.keys.size

        node.children.add(idx, newNode)

        if (childNode.children.size != 0) {
            newNode.isLeaf = false

            newNode.children = childNode.children.slice(0 until t - 1).toMutableList()
            childNode.children = childNode.children.slice(t until 2 * t).toMutableList()
        }
    }

    // Insert the key into a non-full node
    private fun insertNonFull(key: K, node: Node<K>) {
        var idx = node.numOfKeys - 1

        if (node.isLeaf) {
            while (idx >= 0 && node.keys[idx] > key) {
                idx -= 1
            }

            node.keys.add(idx + 1, key)
            node.numOfKeys += 1
        } else {
            while (idx >= 0 && node.keys[idx] > key) {
                idx -= 1
            }

            idx += 1
            val targetChild = node.children[idx]
            // If the child node is full
            if (targetChild.numOfKeys == capacityOfKeys) {
                splitChild(idx, node)
                if (node.keys[idx] < key) {
                    idx += 1
                }
            }

            insertNonFull(key, node.children[idx])
        }
    }

    fun insert(key: K) {
        if (root == null) {
            val newNode = Node<K>(true)
            newNode.keys.add(key)
            newNode.numOfKeys += 1

            root = newNode
        } else {
            val currentRoot: Node<K> = root!!

            // If root is full, then tree grows in height
            if (currentRoot.numOfKeys == capacityOfKeys) {
                val newRoot = Node<K>(false)
                newRoot.children.add(currentRoot)

                splitChild(0, newRoot)

                // Now root node has two child nodes
                var idx = 0
                if (newRoot.keys[idx] < key) {
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
