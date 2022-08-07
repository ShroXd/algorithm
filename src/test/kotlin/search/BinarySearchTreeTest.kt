package search

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BinarySearchTreeTest {

    @Test
    fun testInsertAndSearch() {
        val bst = BinarySearchTree<String, String>()

        bst.put("First key", "First value")
        bst.put("Second key", "Second value")

        assertEquals("First value", bst.get("First key"))
        assertEquals("Second value", bst.get("Second key"))
    }
}