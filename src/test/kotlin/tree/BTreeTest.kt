package tree

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class BTreeTest {

    @Test
    fun testBTree() {
        val bTree = BTree<Int>(2)

        val randomTestNumbers = IntArray(10000) { Random().nextInt() }.asList()
        for (element in randomTestNumbers) {
            bTree.insert(element)
        }

        val result = randomTestNumbers.map { bTree.search(it) != null }.reduce { acc, n -> acc && n}
        assertEquals(false, result)
    }
}
