import util.otherwise

fun main(args: Array<String>) {

    val orderAndDeliveryParams = CommandLineHandler.generateOrderWithDeliveryParams(args.toList())

    orderAndDeliveryParams?.let { orderParams ->
        val outputs = OrderHandler.processDeliveries(orderParams)
        outputs.forEach { output -> println(output) }
    }.otherwise {
        println("To run the courier delivery application :")
        println("CourierDeliveryKt base_delivery_cost no_of_packages pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1 ... no_of_vehicles max_speed max_carriable_weight")
        println("eg. CourierDeliveryKt 100 3 PKG1 5 5 OFR001 PKG2 15 5 OFR002 PKG3 10 100 OFR003 2 70 200")
    }
}