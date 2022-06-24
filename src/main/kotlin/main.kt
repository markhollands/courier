fun main(args: Array<String>) {

    val calculator = OrderHandler()
    val order = calculator.generateOrder(args.toList())

    order?.let { _order ->
        val outputs = calculator.processOrder(_order)

        // write the outputs to the command line
    }




}