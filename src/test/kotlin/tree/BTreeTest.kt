package tree

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class BTreeTest {

    @Test
    fun testBTreeInitInsertionSearching() {
        val bTree = BTree<Int>(100)

        // 1 million
        // We need to handle duplicate element during inserting & searching
        val randomTestNumbers = IntArray(1000000) { Random().nextInt() }.asList()
        for (element in randomTestNumbers) {
            bTree.insert(element)
        }

        val result = randomTestNumbers.map { bTree.search(it) != null }.reduce { acc, n -> acc && n }
        assertEquals(true, result)
    }

    @Test
    fun testBTreeDeletion() {
        val bTree = BTree<Int>(100)

        // Clean duplicate elements to prevent hash collisions
        val randomTestNumbers = IntArray(1000000) { Random().nextInt() }.asList().toSet()

        for (element in randomTestNumbers) {
            bTree.insert(element)
        }

        val randomToBeRemoved = randomTestNumbers.shuffled().slice(0..(randomTestNumbers.size * 0.5).toInt())
        for (element in randomToBeRemoved) {
            bTree.remove(element)
        }

        val result2 = randomToBeRemoved.map { bTree.search(it) == null }.reduce { acc, n -> acc && n }
        assertEquals(true, result2)
    }
}
