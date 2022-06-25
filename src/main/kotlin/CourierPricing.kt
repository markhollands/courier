import util.otherwise

fun main(args: Array<String>) {

    val order = CommandLineHandler.generateOrder(args.toList())

    order?.let { _order ->
        val outputs = OrderHandler.processOrder(_order)
        outputs.forEach { output -> println(output) }
    }.otherwise {
        println("To run the courier pricing application :")
        println("CourierPricingKt base_delivery_cost no_of_packages pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1")
        println("eg. CourierPricingKt 100 3 PKG1 5 5 OFR001 PKG2 15 5 OFR002 PKG3 10 100 OFR003")
    }
}