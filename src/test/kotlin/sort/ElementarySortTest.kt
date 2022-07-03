package sort

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals


internal class ElementarySortTest {
    private var n = 1000

    @Test
    fun testSelectionSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { selection(arr) }

        assertContentEquals(arr, res)
    }

    @Test
    fun testInsertionSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { insertion(arr) }

        assertContentEquals(arr, res)
    }

    @Test
    fun testShellSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { shell(arr) }

        assertContentEquals(arr, res)
    }

    @Test
    fun testMerge() {
        val arr1: Array<Int> = arrayOf(4, 5, 6, 7, 1, 2, 3)
        val res1: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7)

        val arr2: Array<Int> = arrayOf(4, 5, 6, 1, 2, 3)
        val res2: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6)

        sort.run { merge(arr1, 0, (arr1.size - 1) / 2, arr1.size - 1) }
        sort.run { merge(arr2, 0, (arr2.size - 1) / 2, arr2.size - 1) }

        assertContentEquals(arr1, res1)
        assertContentEquals(arr2, res2)
    }

    @Test
    fun testMergeSort() {
        val arr: Array<Int> = generateRandomIntArray(n)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { mergeSort(arr) }

        assertContentEquals(arr, res)
    }

    @Test
    fun testMergeSort1() {
        val arr1: Array<Int> = generateRandomIntArray(n)
        val res1: Array<Int> = generateSortedArray(arr1)

        val arr2: Array<Int> = arrayOf(4, 5, 6, 1, 2, 3)
        val res2: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6)

        sort.run { mergeSort1(arr1) }
        sort.run { mergeSort1(arr2) }

        assertContentEquals(arr1, res1)
        assertContentEquals(arr2, res2)
    }
}
