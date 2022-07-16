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

        val last = this.queue[this.size - 1]

        this.queue[this.size - 1] = 0
        this.size--

        return last
    }
}
