import util.otherwise

fun main(args: Array<String>) {

    val calculator = OrderHandler()
    val order = calculator.generateOrder(args.toList())

    order?.let { _order ->
        val outputs = calculator.processOrder(_order)
        outputs.forEach { output -> println(output) }
    }.otherwise {
        println("To run the courier pricing application :")
        println("courier base_delivery_cost no_of_packages pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1")
        println("eg. courier 100 3 PKG1 5 5 OFR001 PKG2 15 5 OFR002 PKG3 10 100 OFR003")
    }
}