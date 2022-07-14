package sort

// TODO: make it generic
interface MaxPriorityQueue {
    fun isEmpty(): Boolean
    fun insert(v: Int)
    fun max(): Int
    fun delMax(): Int
}

class MaxPriorityQueueUnorderedArray() : MaxPriorityQueue {
    var capacity: Int = 10
        private set
    var size: Int = 0
        private set

    private var queue: Array<Int> = Array(this.size) { 0 }

    constructor(max: Int) : this() {
        this.size = max
        this.queue = Array(this.size) { 0 }
    }

    constructor(queue: Array<Int>) : this() {
        this.size = queue.size
        this.queue = queue
    }

    override fun isEmpty(): Boolean = this.size == 0

    override fun insert(v: Int) {
        if (this.size / this.capacity > 1.5) {
            val temp = Array(this.size * 2) { 0 }

            for ((el, idx) in this.queue.withIndex()) {
                temp[idx] = el
            }

            this.queue = temp.clone()
        }

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
        var currentMaxIdx = this.queue.size - 1

        for (idx in this.queue.size - 2 downTo 0)
            if (this.queue[idx] > this.queue[currentMaxIdx])
                currentMaxIdx = idx

        swap(this.queue, currentMaxIdx, this.queue.size - 1)

        val max = this.queue.last()
        this.queue[this.size--] = 0

        return max
    }
}
