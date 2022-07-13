package sort

// TODO: make it generic
interface MaxPriorityQueue {
    fun insert(v: Int)
    fun max(): Int
    fun delMax(): Int
    fun isEmpty(): Boolean
}

class MaxPriorityQueueArray() : MaxPriorityQueue {
    var size: Int = 10
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
        TODO("Not yet implemented")
    }

    override fun max(): Int {
        TODO("Not yet implemented")
    }

    override fun delMax(): Int {
        TODO("Not yet implemented")
    }

}
