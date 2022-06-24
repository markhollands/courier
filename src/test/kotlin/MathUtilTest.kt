import junit.framework.Assert.assertEquals
import org.junit.Test
import util.round
import util.roundDown

class MathUtilTest {

    @Test
    fun testRoundingDown() {
        val value = 3.456
        val rounded = value.roundDown()
        assertEquals("Incorrect rounding", 3.45, rounded)
    }

    @Test
    fun testRound() {
        val value = 1.929
        val rounded = value.round(2)
        assertEquals("Inocrrect rounding", 1.93, rounded)
    }

}