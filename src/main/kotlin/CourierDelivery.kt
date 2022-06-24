import util.otherwise

fun main(args: Array<String>) {

    val calculator = OrderHandler()
    val orderAndDeliveryParams = calculator.generateOrderWithDeliveryParams(args.toList())

    orderAndDeliveryParams?.let { orderParams ->
        val outputs = calculator.processDeliveries(orderParams)
        outputs.forEach { output -> println(output) }
    }.otherwise {
        println("To run the courier delivery application :")
        println("<appname> base_delivery_cost no_of_packages pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1")
        println("eg. <appname> 100 3 PKG1 5 5 OFR001 PKG2 15 5 OFR002 PKG3 10 100 OFR003")
    }
}