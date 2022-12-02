package tree

class BTree<K : Comparable<K>>(private val t: Int) {
    data class Node<K>(var isLeaf: Boolean) {
        var keys: MutableList<K> = mutableListOf()
        var children: MutableList<Node<K>> = mutableListOf()
    }

    private var root: Node<K>? = null

    private fun search(key: K, node: Node<K>): Node<K>? {
        var idx = node.keys.size - 1

        while (idx >= 0 && node.keys[idx] > key) {
            idx -= 1
        }

        if (node.keys[if (idx == -1) 0 else idx] == key) {
            return node
        }

        if (node.isLeaf) {
            return null
        }

        return search(key, node.children[idx + 1])
    }

    fun search(key: K): Node<K>? {
        if (root == null) {
            throw Error("B Tree is empty")
        }
        return search(key, root!!)
    }

    // Node is the parent node
    // Idx is the index of child node which is going to be split
    private fun splitChild(idx: Int, node: Node<K>) {
        val currentChild = node.children[idx]
        val newChild = Node<K>(currentChild.isLeaf)

        // We need to push the middle key into the parent node
        // Divide all child nodes equally
        newChild.keys = currentChild.keys.slice(t until currentChild.keys.size).toMutableList()
        if (!currentChild.isLeaf) {
            newChild.children = currentChild.children.slice(t until currentChild.children.size).toMutableList()
        }

        // The structure of the b tree is
        // node
        // currentChild / newChild
        node.keys.add(idx, currentChild.keys[t - 1])
        node.children.add(idx + 1, newChild)

        currentChild.keys = currentChild.keys.slice(0 until t - 1).toMutableList()
        if (!currentChild.isLeaf) {
            currentChild.children = currentChild.children.slice(0 until t).toMutableList()
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
            // Which means, keys in the child node to be split is larger than node[idx]
            while (idx >= 0 && node.keys[idx] > key) {
                idx -= 1
            }

            // If the found child is full
            if (node.children[idx + 1].keys.size == 2 * t - 1) {
                splitChild(idx + 1, node)
                // After splitting, the middle key of child node will be put at the position with t index
                // node.keys[t] = currentChild.keys[t]
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

            root = temp
        } else {
            val currentRoot = root!!
            // If the root node is full
            if (currentRoot.keys.size == 2 * t - 1) {
                val newRoot = Node<K>(false)
                newRoot.children.add(currentRoot)
                splitChild(0, newRoot)

                var idx = 0
                // Determine if we need to put the key at the left of the current key or right
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

    private fun findKey(key: K, node: Node<K>): Int {
        var idx = 0
        while (idx < node.keys.size && key > node.keys[idx]) {
            idx += 1
        }

        return idx
    }

    fun remove(key: K) {
        if (root == null) {
            throw Error("Nothing in the b tree")
        }

        remove(key, root!!)

        if (root!!.keys.size == 0) {
            root = if (root!!.isLeaf) {
                null
            } else {
                root!!.children[0]
            }
        }
    }

    private fun remove(key: K, node: Node<K>) {
        var idx = 0
        while (idx < node.keys.size && key > node.keys[idx]) {
            idx += 1
        }

        if (idx < node.keys.size && node.keys[idx] == key) {
            if (node.isLeaf) {
                removeFromLeaf(idx, node)
            } else {
                removeFromNonLeaf(idx, node)
            }
        } else {
            if (node.isLeaf) {
                throw Error("The key $key does not exist in the tree\n")
            }
            val isLast = idx == node.keys.size

            if (node.children[idx].keys.size < t) {
                // Fill the child node
                fill(idx, node)
            }

            // Last child will merge with prev one, so we need to move the idx one step forward
            if (isLast && idx > node.keys.size) {
                remove(key, node.children[idx - 1])
            } else {
                remove(key, node.children[idx])
            }
        }
    }

    private fun removeFromLeaf(idx: Int, node: Node<K>) =
        node.keys.removeAt(idx)

    private fun removeFromNonLeaf(idx: Int, node: Node<K>) {
        val key = node.keys[idx]

        // Left child node has enough keys
        if (node.children[idx].keys.size >= t) {
            val predecessor = getPredecessor(idx, node)
            node.keys[idx] = predecessor

            remove(predecessor, node.children[idx])
        } else if (node.children[idx + 1].keys.size >= t) {
            val successor = getSuccessor(idx, node)
            node.keys[idx] = successor

            remove(successor, node.children[idx + 1])
        } else {
            merge(idx, node)
            remove(key, node.children[idx])
        }
    }

    private fun getPredecessor(idx: Int, node: Node<K>): K {
        var curr = node.children[idx]
        while (!curr.isLeaf) {
            // The last child node
            curr = curr.children.last()
        }

        return curr.keys.last()
    }

    private fun getSuccessor(idx: Int, node: Node<K>): K {
        var curr = node.children[idx + 1]
        while (!curr.isLeaf) {
            curr = curr.children.first()
        }

        return curr.keys.first()
    }

    private fun fill(idx: Int, node: Node<K>) {
        if (idx != 0 && node.children[idx - 1].keys.size >= t) {
            borrowFromPrev(idx, node)
        } else if (idx != node.keys.size && node.children[idx + 1].keys.size >= t) {
            borrowFromNext(idx, node)
        } else {
            // If the child is the last one
            if (idx == node.keys.size) {
                merge(idx - 1, node)
            } else {
                merge(idx, node)
            }
        }
    }

    private fun borrowFromPrev(idx: Int, node: Node<K>) {
        val child = node.children[idx]
        val sibling = node.children[idx - 1]

        child.keys.add(0, node.keys[idx - 1])
        if (!child.isLeaf) {
            child.children.add(0, sibling.children.last())
            sibling.children.removeLast()
        }

        node.keys[idx - 1] = sibling.keys.last()
        sibling.keys.removeLast()
    }

    private fun borrowFromNext(idx: Int, node: Node<K>) {
        val child = node.children[idx]
        val sibling = node.children[idx + 1]

        // Child is the left subtree of current node, therefore each key in this node is smaller than that in parent node
        child.keys.add(node.keys[idx])
        if (!child.isLeaf) {
            child.children.add(sibling.children.first())
            sibling.children.removeFirst()
        }

        node.keys[idx] = sibling.keys.first()
        sibling.keys.removeFirst()
    }

    // Merge node.children[idx] with node.children[idx+1]
    private fun merge(idx: Int, node: Node<K>) {
        val child = node.children[idx]
        val sibling = node.children[idx + 1]

        child.keys.add(node.keys[idx])

        child.keys.addAll(sibling.keys)
        child.children.addAll(sibling.children)

        // Clean up the key and the useless sibling node
        node.keys.removeAt(idx)
        node.children.removeAt(idx + 1)
    }
}

