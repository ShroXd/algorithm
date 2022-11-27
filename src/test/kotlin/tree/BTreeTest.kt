package tree

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class BTreeTest {

    @Test
    fun testBTree() {
        val bTree = BTree<Int>(2)

        val manualTestNumbers = listOf(8, 9, 10, 11, 12, 7, 13, 20, 1, 33, 44)
        for (element in manualTestNumbers) {
            bTree.insert(element)
        }

        val result = manualTestNumbers.map { bTree.search(it) != null }.reduce { acc, n -> acc && n}
//
//        assertEquals(false, result)

//
//        val randomTestNumbers = IntArray(100) { Random().nextInt() }.asList()
//        randomTestNumbers.forEach {
//            bTree.insert(it)
//        }
//        randomTestNumbers.forEach {
//            assertEquals(false, bTree.search(it) == null)
//        }
    }
}
