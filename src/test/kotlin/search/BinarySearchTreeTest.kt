package search

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BinarySearchTreeTest {

    @Test
    fun testBasicBST() {
        val bst = BinarySearchTree<String, String>()

        assertEquals(true, bst.isEmpty())

        bst.put("First key", "First value")

        assertEquals(true, bst.contains("First key"))
        assertEquals(false, bst.contains("Second key"))

        bst.put("Second key", "Second value")

        assertEquals(2, bst.size)
        assertEquals(false, bst.isEmpty())

        assertEquals("First value", bst.get("First key"))
        assertEquals("Second value", bst.get("Second key"))
    }

    @Test
    fun testCustomBST() {
        val bst = BinarySearchTree<Int, String>()

        assertEquals(null, bst.minKey())
        assertEquals(null, bst.maxKey())

        bst.put(1, "One")
        bst.put(2, "Two")
        bst.put(10, "Ten")
        bst.put(3, "Three")
        bst.put(4, "Four")

        assertEquals(1, bst.minKey())
        assertEquals(10, bst.maxKey())

        assertEquals("Four", bst.floor(9))
    }
}
