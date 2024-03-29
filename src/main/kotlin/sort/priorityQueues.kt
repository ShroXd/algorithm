package sort

// TODO: make it generic
interface MaxPriorityQueue {
    fun isEmpty(): Boolean
    fun insert(v: Int)
    fun max(): Int
    fun delMax(): Int
}

class MaxPriorityQueueUnorderedArray() : MaxPriorityQueue {
    private var capacity: Int = 10
    private var size: Int = 0

    private var queue: Array<Int> = Array(this.size) { 0 }

    constructor(capacity: Int) : this() {
        this.capacity = capacity
        this.queue = Array(this.capacity) { 0 }
    }

    constructor(queue: Array<Int>) : this() {
        this.capacity = queue.size * 3
        this.size = queue.size
        this.queue = Array(this.capacity) { 0 }

        for (idx in 0 until this.size)
            this.queue[idx] = queue[idx]
    }

    private fun expandQueue() {
        this.capacity = this.capacity * 2
        val temp = Array(this.capacity) { 0 }

        for (idx in 0 until this.size)
            temp[idx] = this.queue[idx]

        this.queue = temp.clone()
    }

    override fun isEmpty(): Boolean = this.size == 0

    override fun insert(v: Int) {
        if (this.size.toFloat() / this.capacity.toFloat() > 0.5)
            expandQueue()

        this.queue[size++] = v
    }

    override fun max(): Int {
        var currentMax = this.queue[0]

        for (el in this.queue.drop(1))
            if (el > currentMax)
                currentMax = el

        return currentMax
    }

    override fun delMax(): Int {
        var currentMaxIdx = this.size - 1

        // TODO: shrink the array

        for (idx in this.size - 2 downTo 0)
            if (this.queue[idx] > this.queue[currentMaxIdx])
                currentMaxIdx = idx

        swap(this.queue, currentMaxIdx, this.queue.size - 1)

        val max = this.queue.last()
        this.queue[this.size--] = 0

        return max
    }
}

class MaxPriorityQueueOrderedArray() : MaxPriorityQueue {
    private var capacity: Int = 10
    private var size: Int = 0

    private var queue: Array<Int> = Array(this.size) { 0 }

    constructor(capacity: Int) : this() {
        this.capacity = capacity
        this.queue = Array(this.capacity) { 0 }
    }

//    constructor(queue: Array<Int>) : this() {
//        this.capacity = queue.size * 3
//        this.size = queue.size
//        this.queue = Array(this.capacity) { 0 }
//
//        for (idx in 0 until this.size)
//            this.queue[idx] = queue[idx]
//    }

    private fun expandQueue() {
        this.capacity = this.capacity * 2
        val temp = Array(this.capacity) { 0 }

        for (idx in 0 until this.size)
            temp[idx] = this.queue[idx]

        this.queue = temp.clone()
    }

    override fun isEmpty(): Boolean = this.size == 0

    override fun insert(v: Int) {
        if (this.size.toFloat() / this.capacity.toFloat() > 0.5)
            expandQueue()

        if (this.size == 0) {
            this.queue[this.size++] = v
            return
        }

        if (v > this.queue[this.size - 1])
            this.queue[this.size] = v
        else {
            swap(this.queue, this.size - 1, this.size)
            for (idx in this.size - 2 downTo 0)
                if (this.queue[idx] > v)
                    swap(this.queue, idx, idx + 1)
                else {
                    this.queue[idx + 1] = v
                    break
                }
        }

        this.size++
    }

    override fun max(): Int = this.queue[this.size - 1]

    // TODO: To handle reference data, we need to pay attention about the deep clone
    override fun delMax(): Int {
        if (this.size - 1 < 0) throw ArrayIndexOutOfBoundsException("Out of scope")

        // TODO: shrink the array

        val last = this.queue[this.size - 1]

        this.queue[this.size - 1] = 0
        this.size--

        return last
    }
}

class MaxPriorityQueueLinkedList : MaxPriorityQueue {
    private class Node(v: Int) {
        var value: Int = v
        var next: Node? = null
    }

    // Virtual node
    private var head: Node = Node(0)
    private var tail: Node = head
    var size: Int = 0
        private set

    override fun isEmpty(): Boolean = this.size == 0

    override fun insert(v: Int) {
        val temp = Node(v)

        var p = this.head

        while (p.next != null && p.next?.value!! < v) {
            p = p.next!!
        }

        temp.next = p.next
        p.next = temp

        if (temp.next == null)
            this.tail = temp

        this.size++
    }

    override fun max(): Int = this.tail.value

    override fun delMax(): Int {
        val max = this.tail.value

        var p = this.head

        while (p.next != null) {
            if (p.next?.next == null) break
            p = p.next!!
        }

        this.tail = p
        p.next = null

        this.size--

        return max
    }
}

class MaxPriorityQueueBinaryHeap : MaxPriorityQueue {
    private var capacity: Int = 10
    var size: Int = 0
        private set

    private var queue: Array<Int> = Array(this.capacity) { 0 }

    override fun isEmpty(): Boolean = this.size == 0

    override fun insert(v: Int) {
        // TODO: check the capacity

        this.queue[this.size] = v

        // TODO: encapsulate the swim & sink
        var currentIdx = this.size
        var prevIdx = (this.size - 1) / 2

        while (prevIdx != 0) {
            if (this.queue[prevIdx] < v)
                swap(this.queue, prevIdx, currentIdx)
            currentIdx = prevIdx
            prevIdx /= 2
        }

        if (this.queue[prevIdx] < v)
            swap(this.queue, prevIdx, currentIdx)

        this.size++
    }

    override fun max(): Int = this.queue[0]

    override fun delMax(): Int {
        val max = this.queue[0]
        val endIdx = this.size - 1

        swap(this.queue, 0, endIdx)
        this.queue[endIdx] = 0
        this.size--

        var currentIdx = 0
        var nextIdx = 1

        while (nextIdx + 1 < endIdx || nextIdx < endIdx) {
            // No need to check the nextIdx < endIdx
            // Conditions in the while:
            // 1. left true, right must true
            // 2. left false, if the code jump in the body of while, so the nextIdx < endIdx must be true
            if (this.queue[nextIdx] > this.queue[currentIdx]) {
                swap(this.queue, currentIdx, nextIdx)
                currentIdx = nextIdx
            } else if (nextIdx + 1 < endIdx && this.queue[nextIdx + 1] > this.queue[currentIdx]) {
                swap(this.queue, currentIdx, nextIdx + 1)
                currentIdx = nextIdx + 1
            }
            nextIdx = currentIdx * 2
        }

        return max
    }
}
