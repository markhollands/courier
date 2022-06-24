import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class TestDeliveryCreation {

    @Test
    fun testOrderCreation() {

        val inputs = "100 3 PKG1 5 5 OFR001 PKG2 15 5 OFR002 PKG3 10 100 OFR003 2 70 200"
        val order = OrderHandler().generateOrderWithDeliveryParams(inputs.split(" "))
        assertNotNull("Order is null", order)
    }

    @Test
    fun testOrderCreationMissingArgument() {

        val inputs = "100 3 PKG1 5 5 OFR001 PKG2 15 5 OFR002 PKG3 10 100 OFR003 2 70"
        val order = OrderHandler().generateOrder(inputs.split(" "))
        assertNull("Order is not null", order)
    }

    @Test
    fun testOrderCreationInvalidArgument() {

        val inputs = "100 3 PKG1 5 5 OFR001 PKG2 15 5.5 OFR002 PKG3 10 100 2 70 200"
        val order = OrderHandler().generateOrder(inputs.split(" "))
        assertNull("Order is not null", order)
    }

    @Test
    fun testOrderCreationNoArgument() {

        val inputs = ""
        val order = OrderHandler().generateOrder(inputs.split(" "))
        assertNull("Order is not null", order)
    }
}