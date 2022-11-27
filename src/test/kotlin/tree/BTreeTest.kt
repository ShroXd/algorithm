package tree

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class BTreeTest {

    @Test
    fun testBTree() {
        val bTree = BTree<Int>(2)
        val randomTestNumbers = IntArray(100) { Random().nextInt() }.asList()

//        randomTestNumbers.forEach {
//            bTree.insert(it)
//        }
//
//        randomTestNumbers.forEach {
//            assertEquals(false, bTree.search(it) == null)
//        }

        bTree.insert(2041479508)
        bTree.insert(-719616384)
        bTree.insert(-388887231)
        bTree.insert(-1384822873)
        bTree.insert(-1384822873)
        bTree.insert(1597592069)
        bTree.insert(684357915)
        bTree.insert(1865559723)
        bTree.insert(1093853064)
        bTree.insert(-592455470)
        bTree.insert(374406946)
        bTree.insert(316156639)
        bTree.insert(-1304898134)
        bTree.insert(-867590560)
        bTree.insert(-367502180)
    }
}
