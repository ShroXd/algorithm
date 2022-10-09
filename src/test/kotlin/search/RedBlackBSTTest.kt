package search

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RedBlackBSTTest {

    @Test
    fun testRedBlackBST() {
        val rd = RedBlackBST<String, Int>()

        rd.put("first key", 1)
        rd.put("second key", 2)
        rd.put("third key", 3)

        assertEquals(1, rd.get("first key"))
        assertEquals(2, rd.get("second key"))
        assertEquals(3, rd.get("third key"))

//        rd.delete("first key")
//        rd.delete("third key")
//        assertEquals(null, rd.get("first key"))
//        assertEquals(2, rd.get("second key"))
//        assertEquals(null, rd.get("third key"))
    }
}