package tree

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class BTreeTest {

    @Test
    fun testBTreeInitInsertionSearching() {
        val bTree = BTree<Int>(2)

        val manualTestNumber = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        for (element in manualTestNumber) {
            bTree.insert(element)
        }

        val result = manualTestNumber.map { bTree.search(it) != null }.reduce { acc, n -> acc && n }
        assertEquals(true, result)

//        val randomTestNumbers = IntArray(10000) { Random().nextInt() }.asList()
//        for (element in randomTestNumbers) {
//            bTree.insert(element)
//        }
//
//        val result = randomTestNumbers.map { bTree.search(it) != null }.reduce { acc, n -> acc && n }
//        assertEquals(true, result)
    }

    @Test
    fun testBTreeDeletion() {
        val bTree = BTree<Int>(2)

        val manualTestNumber = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        for (element in manualTestNumber) {
            bTree.insert(element)
        }
        val result = manualTestNumber.map { bTree.search(it) != null }.reduce { acc, n -> acc && n }
        assertEquals(false, result)

        val numberToBeRemoved = listOf(2, 4, 6, 8)
        for (element in numberToBeRemoved) {
            bTree.remove(element)
        }
        val result2 = numberToBeRemoved.map { bTree.search(it) != null }.reduce { acc, n -> acc && n }
        assertEquals(true, result2)
    }
}
