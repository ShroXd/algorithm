package tree

class BTree<K : Comparable<K>>(private val t: Int) {
    data class Node<K>(var isLeaf: Boolean) {
        var keys: MutableList<K> = mutableListOf()
        var children: MutableList<Node<K>> = mutableListOf()
    }

    private var root: Node<K>? = null

    private fun search(key: K, node: Node<K>?): Node<K>? {
        if (node == null) {
            return null
        } else {
            var idx = 0
            // When the while loop end, the index will be at right of the last element which is smaller than the key
            // 1 5 10 14 20 key=18 22
            while (idx < node.keys.size && key > node.keys[idx]) {
                idx += 1
            }

            // Check if the key is the largest key in the array
            // If so, the index will be equals to node.keys.size
            return if (idx == node.keys.size) {
                search(key, node.children[idx - 1])
            } else if (key == node.keys[idx]) {
                // Check if we find the key
                node
            } else if (node.isLeaf) {
                null
            } else {
                // Jump into the children
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

        // We need to push the middle key into the parent node
        // Divide all child nodes equally
        newChild.keys = currentChild.keys.slice(0 until t).toMutableList()
        if (currentChild.isLeaf) {
            newChild.children = currentChild.children.slice(0 until t).toMutableList()
        }

        // The structure of the b tree is
        // node
        // currentChild / newChild
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

        return remove(key, root!!)
    }

    private fun remove(key: K, node: Node<K>) {
        val idx = findKey(key, node)

        if (idx < node.keys.size && node.keys[idx] == key) {
            if (node.isLeaf) {
                node.keys = removeFromLeaf(idx, node)
            } else {
                // TODO: remove from non leaf
            }
        } else {
            if (node.isLeaf) {
                throw Error("The key $key does not exist in the tree\n")
            }

            val isLast = idx == node.keys.size

            if (node.children[idx].keys.size < t) {
                // Fill the child node
            }

            if (isLast && idx > node.keys.size) {
                remove(key, node.children[idx - 1])
            } else {
                remove(key, node.children[idx])
            }
        }
    }

    private fun removeFromLeaf(idx: Int, node: Node<K>): MutableList<K> =
        mutableListOf(node.keys).removeAt(idx)

    private fun removeFromNonLeaf(idx: Int, node: Node<K>) {
        val key = node.keys[idx]

        // Left child node has enough keys
        if (node.children[idx].keys.size >= t) {
            val predecessor = getPredecessor(idx, node)
            node.keys[idx] = predecessor

            remove(key, node.children[idx])
        } else if (node.children[idx + 1].keys.size >= t) {
            val successor = getSuccessor(idx, node)
            node.keys[idx] = successor

            remove(key, node.children[idx + 1])
        } else {
            // TODO: merge two child nodes
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
        var curr = node.children[idx]
        while (!curr.isLeaf) {
            curr = curr.children.first()
        }

        return curr.keys.first()
    }

    private fun fill(idx: Int, node: Node<K>) {
        if (idx != 0 && node.children[idx - 1].keys.size >= t) {
            // TODO: borrow from prev child node
        } else if (idx != node.keys.size && node.children[idx + 1].keys.size >= t) {
            // TODO: borrow from next child node
        } else {
            // If the child is the last one
            if (idx == node.keys.size) {
               // TODO: merge it with prev
            } else {
               // TODO: merge it with next
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
    }
}

