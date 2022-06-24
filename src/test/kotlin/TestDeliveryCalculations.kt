import datamodel.DeliveryParams
import datamodel.Order
import datamodel.OrderAndDeliveryParams
import datamodel.PackageQuote
import org.junit.Assert
import org.junit.Test

class TestDeliveryCalculations {

    @Test
    fun testDeliveryCalculations() {

        val order = Order(100, listOf(
            PackageQuote("PKG1", 50, 30, "OFR001"),
            PackageQuote("PKG2", 75, 125, "OFR008"),
            PackageQuote("PKG3", 175, 100, "OFR003"),
            PackageQuote("PKG4", 110, 60, "OFR002"),
            PackageQuote("PKG5", 155, 95, "NA"),
        ))

        val orderParams = OrderAndDeliveryParams(order, DeliveryParams(2, 70, 200))

        val deliveries = OrderHandler().processDeliveries(orderParams)

        Assert.assertEquals("Incorrect number of costs", 5, deliveries.size)
        Assert.assertEquals("Incorrect cost value", "PKG1 0 750 3.98", deliveries[0].toString())
        Assert.assertEquals("Incorrect cost value", "PKG2 0 1475 1.78", deliveries[1].toString())
        Assert.assertEquals("Incorrect cost value", "PKG3 0 2350 1.42", deliveries[2].toString())
        Assert.assertEquals("Incorrect cost value", "PKG4 105 1395 0.85", deliveries[3].toString())
        Assert.assertEquals("Incorrect cost value", "PKG5 0 2125 4.19", deliveries[4].toString())
    }

}

