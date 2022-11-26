package tree

class BTree<K : Comparable<K>>(private val t: Int) {
    data class Node<K>(var isLeaf: Boolean) {
        var keys: MutableList<K> = mutableListOf()
        var children: MutableList<Node<K>> = mutableListOf()

        var numOfKeys: Int = keys.size
    }

    private val capacityOfKeys: Int = 2 * t - 1
    private var root: Node<K>? = null

    private fun search(key: K, node: Node<K>?): Node<K>? {
        if (node == null) {
            return null
        }

        var idx = 0
        for (i in 0 until node.numOfKeys) {
            // When the for loop break, the current key is larger / equal to key
            idx = i
            if (node.keys[i] >= key) {
                break
            }
        }

        // If it's equal, the target is found
        return if (node.keys[idx] == key) {
            node
        } else if (node.keys[idx] < key) {
            search(key, node.children[idx + 1])
        } else {
            // The current key is larger than target, so the target maybe in the left child node
            // The keys in left child node are both smaller than current key
            search(key, node.children[idx])
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
        childNode.keys = childNode.keys.slice(t until 2 * t).toMutableList()

        newNode.numOfKeys = newNode.keys.size
        childNode.numOfKeys = childNode.keys.size

        node.children.add(idx, newNode)

        if (childNode.children.size != 0) {
            newNode.isLeaf = false

            newNode.children = childNode.children.slice(0 until t).toMutableList()
            childNode.children = childNode.children.slice(t until 2 * t).toMutableList()
        }
    }

    // Insert the key into a non-full node
    private fun insertNonFull(key: K, node: Node<K>) {
        if (node.isLeaf) {
            var idx = 0
            for (i in 0 until node.numOfKeys) {
                idx = i
                if (node.keys[i] >= key) {
                    break
                }
            }

            if (idx == node.numOfKeys - 1) {
                idx += 1
            }

            node.keys.add(idx, key)
            node.numOfKeys += 1
        } else {
            var idx = 0
            for (i in 0 until node.numOfKeys) {
                if (node.keys[i] >= key) {
                    idx = i
                    break
                }
            }

            val targetChild = node.children[idx]
            // If the child node is full
            if (targetChild.numOfKeys == capacityOfKeys) {
                splitChild(idx, node)
            }
            if (node.keys[idx] > key) {
                idx -= 1
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

            if (currentRoot.numOfKeys == capacityOfKeys) {
                val newNode = Node<K>(false)
                newNode.children.add(currentRoot)
                currentRoot.keys.add(key)
                currentRoot.numOfKeys += 1

                splitChild(0, newNode)
                root = newNode
            } else {
                insertNonFull(key, currentRoot)
            }
        }
    }
}
