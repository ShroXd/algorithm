package sort

interface MaxPriorityQueue<Key : Comparable<Key>> {
    val size: Int
    val array: Array<Key>

    fun insert(v: Key)
    fun max(): Key
    fun delMax(): Key
    fun isEmpty(): Boolean
    fun size(): Int
}
