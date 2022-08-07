package search

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BinarySearchTreeTest {

    @Test
    fun testInsertAndSearch() {
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
}