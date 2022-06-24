import datamodel.Order
import datamodel.PackageQuote
import org.junit.Assert.assertEquals
import org.junit.Test

class TestOrderCalculations {


    @Test
    fun testOrderCalculations() {

        val order = Order(100, listOf(
            PackageQuote("PKG1", 5, 5, "OFR001"),
            PackageQuote("PKG2", 15, 5, "OFR002"),
            PackageQuote("PKG3", 10, 100, "OFR003"),
            ))

        val costs = OrderCalculator.processOrder(order)

        assertEquals("Incorrect number of costs", 3, costs.size)
        assertEquals("Incorrect cost value", "PKG1 0 175", costs[0].toString())
        assertEquals("Incorrect cost value", "PKG2 0 275", costs[0].toString())
        assertEquals("Incorrect cost value", "PKG 35 665", costs[0].toString())
    }
}