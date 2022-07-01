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
}
