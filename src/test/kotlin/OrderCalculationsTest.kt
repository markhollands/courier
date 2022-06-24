import datamodel.Order
import datamodel.PackageQuote
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class OrderCalculationsTest {

    @Test
    fun testOrderCalculations() {

        val order = Order(100, listOf(
            PackageQuote("PKG1", 5, 5, "OFR001"),
            PackageQuote("PKG2", 15, 5, "OFR002"),
            PackageQuote("PKG3", 10, 100, "OFR003"),
            ))

        val costs = OrderHandler().processOrder(order)

        assertEquals("Incorrect number of costs", 3, costs.size)
        assertEquals("Incorrect cost value", "PKG1 0 175", costs[0].toString())
        assertEquals("Incorrect cost value", "PKG2 0 275", costs[1].toString())
        assertEquals("Incorrect cost value", "PKG3 35 665", costs[2].toString())
    }

    @Test
    fun testPackageCalculationsNotEligible() {

        val order = Order(100, listOf(PackageQuote("PKG1", 5, 5, "OFR001")))
        val item = order.packages.first()
        assertNull("Discount isn't null", item.discount)
        val cost = order.calculatePackageCost(item)
        assertEquals("Incorrect discount", 0, cost.discount)
        assertEquals("Incorrect total cost", 175, cost.totalCost)
    }

    @Test
    fun testPackageCalculationsInvalidCode() {

        val order = Order(100, listOf(PackageQuote("PKG1", 5, 5, "blah")))
        val cost = order.calculatePackageCost(order.packages.first())
        assertEquals("Incorrect discount", 0, cost.discount)
        assertEquals("Incorrect total cost", 175, cost.totalCost)
    }

    @Test
    fun testPackageCalculationsValidCode() {

        val order = Order(100, listOf(PackageQuote("PKG1", 100, 100, "OFR001")))
        val item = order.packages.first()
        val cost = order.calculatePackageCost(item)
        assertEquals("Incorrect discount", 160, cost.discount)
        assertEquals("Incorrect total cost", 1440, cost.totalCost)
    }


    @Test
    fun testPackageCalculationsValidCode3() {

        val order = Order(100, listOf(PackageQuote("PKG3", 10, 100, "OFR003")))
        val item = order.packages.first()
        val cost = order.calculatePackageCost(item)
        assertEquals("Incorrect discount", 35, cost.discount)
        assertEquals("Incorrect total cost", 665, cost.totalCost)
    }

    @Test
    fun testNonsensicalInputs() {
        val order = Order(100, listOf(PackageQuote("PKG3", -10, -5, "OFR003")))
        val item = order.packages.first()
        val cost = order.calculatePackageCost(item)
        assertEquals("Incorrect discount", 0, cost.discount)
        assertEquals("Incorrect total cost", -25, cost.totalCost)
    }
}