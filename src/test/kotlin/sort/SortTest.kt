package sort

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals


internal class SortTest {
    private var n = 3

    @Test
    fun testSelectionSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { selection(arr) }

        assertContentEquals(res, arr)
    }

    @Test
    fun testInsertionSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { insertion(arr) }

        assertContentEquals(res, arr)
    }

    @Test
    fun testShellSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { shell(arr) }

        assertContentEquals(res, arr)
    }

    @Test
    fun testMerge() {
        val arr1: Array<Int> = arrayOf(4, 5, 6, 7, 1, 2, 3)
        val res1: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7)

        val arr2: Array<Int> = arrayOf(4, 5, 6, 1, 2, 3)
        val res2: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6)

        sort.run { merge(arr1, 0, (arr1.size - 1) / 2, arr1.size - 1) }
        sort.run { merge(arr2, 0, (arr2.size - 1) / 2, arr2.size - 1) }

        assertContentEquals(res1, arr1)
        assertContentEquals(res2, arr2)
    }

    @Test
    fun testTopDownMergeSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { topDownMergeSort(arr) }

        assertContentEquals(res, arr)
    }

    @Test
    fun testTopDownMergeSortImprove1() {
        val arr1: Array<Int> = generateRandomIntArray(n)
        val res1: Array<Int> = generateSortedArray(arr1)

        val arr2: Array<Int> = arrayOf(4, 5, 6, 1, 2, 3)
        val res2: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6)

        sort.run { topDownMergeSortImprove1(arr1) }
        sort.run { topDownMergeSortImprove1(arr2) }

        assertContentEquals(res1, arr1)
        assertContentEquals(res2, arr2)
    }

    @Test
    fun testBottomUpMergeSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { bottomUpMergeSort(arr) }

        assertContentEquals(res, arr)
    }

    @Test
    fun testQuickSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { quickSort(arr, 0, arr.size - 1) }

        assertContentEquals(res, arr)
    }

    @Test
    fun testMaxPriorityQueueUnorderedArray() {
        // Initialize the maxPQ with capacity
        var maxPQ = MaxPriorityQueueUnorderedArray(3)

        assertEquals(true, maxPQ.isEmpty())

        maxPQ.insert(1)
        maxPQ.insert(2)
        maxPQ.insert(3)
        maxPQ.insert(10)

        assertEquals(10, maxPQ.max())
        assertEquals(10, maxPQ.delMax())
        assertEquals(3, maxPQ.delMax())
        assertEquals(2, maxPQ.delMax())
        assertEquals(1, maxPQ.delMax())

        assertEquals(true, maxPQ.isEmpty())

        // Initialize the maxPQ with exist array
        maxPQ = MaxPriorityQueueUnorderedArray(arrayOf(1, 2, 3))

        assertEquals(false, maxPQ.isEmpty())

        maxPQ.insert(10)

        assertEquals(10, maxPQ.max())
        assertEquals(10, maxPQ.delMax())
        assertEquals(3, maxPQ.delMax())
        assertEquals(2, maxPQ.delMax())
        assertEquals(1, maxPQ.delMax())

        assertEquals(true, maxPQ.isEmpty())
    }

    @Test
    fun testMaxPriorityQueueOrderedArray() {
        // Initialize the maxPQ with capacity
        val maxPQ = MaxPriorityQueueOrderedArray(10)

        assertEquals(true, maxPQ.isEmpty())

        maxPQ.insert(1)
        maxPQ.insert(2)
        maxPQ.insert(3)
        maxPQ.insert(10)
        maxPQ.insert(5)
        maxPQ.insert(4)
        maxPQ.insert(7)

        assertEquals(10, maxPQ.max())
        assertEquals(10, maxPQ.delMax())
        assertEquals(3, maxPQ.delMax())
        assertEquals(2, maxPQ.delMax())
        assertEquals(1, maxPQ.delMax())

        assertEquals(true, maxPQ.isEmpty())
    }
}
