package tree

class BTree<K : Comparable<K>>(val t: Int) {
    data class Node<K>(val isLeaf: Boolean) {
        var capacity: Int = 0
        var keys: MutableList<K> = mutableListOf()
        var children: MutableList<Node<K>> = mutableListOf()
    }

    private var root: Node<K>? = Node<K>(true)

    private fun splitChild(currentNode: Node<K>, idx: Int) {
        val childNode: Node<K> = currentNode.children[idx]
        val newNode: Node<K> = Node<K>(childNode.isLeaf)

        // Put the middle key into the parent keys
        currentNode.keys.add(idx, childNode.keys[t - 1])

        // Idx is the key we kicked up, so the new node will at idx + 1
        // Left of the idx key is the original node
        currentNode.children.add(idx + 1, newNode)

        // Fill the keys for two child nodes around key kicked up to parent
        newNode.keys = childNode.keys.slice(t until (2 * t)).toMutableList()
        childNode.keys = childNode.keys.slice(0 until t - 1).toMutableList()

        if (!childNode.isLeaf) {
            // Fill the children like what we did for keys
            newNode.children = childNode.children.slice(t until (2 * t)).toMutableList()
            childNode.children = childNode.children.slice(0 until t).toMutableList()
        }
    }

    private fun insertNonFull(currentNode: Node<K>, key: K) {
        if (currentNode.isLeaf) {
            // Find the position to make the list stay organized after inserting new key
            var idx = 0
            for (i in 0 until currentNode.keys.size) {
                if (currentNode.keys[i] > key) {
                    idx = i
                    break
                }
            }

            // Insert key, update the capacity
            currentNode.keys.add(idx, key)
            currentNode.capacity += 1
        } else {
            var idx = 0
            for (i in 0 until currentNode.keys.size) {
                if (currentNode.keys[i] > key) {
                    idx = i
                    break
                }
            }

            // Check if the child node is full
            if (currentNode.children[idx - 1].capacity == 2 * t - 1) {
                splitChild(currentNode.children[idx - 1] ,idx - 1)
                if (currentNode.keys[idx - 1] < key) {
                    idx++
                }
            }

            insertNonFull(currentNode.children[idx + 1], key)
        }
    }

    fun insert(key: K) {
        if (root == null) {
            val newNode = Node<K>(true)
            newNode.keys[0] = key
            newNode.capacity = 1

            root = newNode
        } else {
            if (root!!.capacity == 2 * t - 1) {
                val newNode = Node<K>(false)
                newNode.children[0] = root!!

                splitChild(newNode, 0)

                val i = if (newNode.keys[0] < key) 1 else 0
                insertNonFull(newNode.children[i], key)
            } else {
                insertNonFull(root!!, key)
            }
        }
    }

    private fun search(key: K, node: Node<K>): Node<K>? {
        var idx = 0
        for (i in 0 until node.capacity) {
            if (node.keys[i] < key) {
                idx += 1
            } else {
                break
            }
        }

        if (node.keys[idx] == key) {
            return node
        }

        if (node.isLeaf) {
            return null
        }

        return search(key, node.children[idx])
    }
    fun search(key: K): Node<K>? = if (root == null) null else search(key, root!!)
}
