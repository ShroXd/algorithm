package sort


fun heapSort(array: Array<Int>) {
    fun maxHeapify(array: Array<Int>, size: Int, index: Int) {
        val leftChild = index * 2 + 1
        val rightChild = index * 2 + 2
        var currentMax = index

        if (leftChild < size && array[leftChild] > array[index]) {
            currentMax = leftChild
        }

        if (rightChild < size && array[rightChild] > array[index]) {
            currentMax = rightChild
        }

        if (currentMax != index) {
            swap(array, currentMax, index)
            maxHeapify(array, size, currentMax)
        }
    }

    fun buildHeap(array: Array<Int>) {
        // array.size / 2 is the parent of the current node
        // the index of array starts from 1
        for (i in array.size / 2 downTo 0)
            maxHeapify(array, array.size, i)
    }

    var size = array.size
    buildHeap(array)

    for (i in size - 1 downTo 0) {
        // 1. generate the largest one by moving it to the end of array
        // 2. decrease the size so array doesn't include the largest one for now
        // 3. re-heapify the array so we can repeat the step 1
        swap(array, i, 0)
        size--
        maxHeapify(array, size, 0)
    }
}