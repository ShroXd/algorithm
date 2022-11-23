package tree

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BTreeTest {

    @Test
    fun testSelectionSort() {
        val bTree = BTree<Int>(2)

        bTree.insert(1)
        bTree.insert(2)
        bTree.insert(3)

        assertEquals(bTree.search(1) == null, false)
    }
}
