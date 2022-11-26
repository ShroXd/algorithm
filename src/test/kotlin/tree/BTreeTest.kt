package tree

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BTreeTest {

    @Test
    fun testSelectionSort() {
        val bTree = BTree<Int>(2)

        bTree.insert(8)
        bTree.insert(9)
        bTree.insert(10)
        bTree.insert(11)

        assertEquals(bTree.search(8) == null, false)
        assertEquals(bTree.search(9) == null, false)
        assertEquals(bTree.search(10) == null, false)
        assertEquals(bTree.search(11) == null, false)
    }
}
