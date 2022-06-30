package sort

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals


internal class ElementarySortTest {
    @Test
    fun testSelectionSort() {
        val arr: Array<Int> = generateRandomIntArray(1000)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { selection(arr) }

        assertContentEquals(arr, res)
    }

    @Test
    fun testInsertionSort() {
        val arr: Array<Int> = generateRandomIntArray(1000)
        val res: Array<Int> = generateSortedArray(arr)

        sort.run { insertion(arr) }

        assertContentEquals(arr, res)
    }
}