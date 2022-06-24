import datamodel.*
import util.permutations
import util.roundDown
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException

class OrderHandler {

    fun processOrder(order: Order): List<PackageCost> {
        return order.packages.map { packageQuote ->
            order.calculatePackageCost(packageQuote)
        }
    }

    fun processDeliveries(orderParams: OrderAndDeliveryParams): List<Delivery> {
        val costedOrders = orderParams.order.packages.map { quote ->
            Delivery(quote, orderParams.order.calculatePackageCost(quote))
        }

        populateDeliveryEstimates(orderParams.deliveryParams, costedOrders)
        costedOrders.sortedBy { it.packageQuote.packageId }
        return costedOrders
    }

    private fun generatePotentialDeliveryCombination(deliveries: List<PackageQuote>, maxCarriableWeight: Int): List<List<PackageQuote>> {
        val permutations = deliveries.permutations()
        val weightLimited = mutableListOf<List<PackageQuote>>()

        permutations.forEach { permutation ->
            val mutablePermutation = permutation.toMutableList()
            while (mutablePermutation.sumOf { it.weight } > maxCarriableWeight) {
                mutablePermutation.removeLast()
            }
            weightLimited.add(mutablePermutation)
        }

        val sortedBySize = weightLimited.sortedByDescending { it.size }
        val mostOnly = sortedBySize.filter { it.size == sortedBySize.first().size }
        return mostOnly.sortedByDescending { it.sumOf { it.weight }}
    }

    data class Vehicle(var timeElapsed: Double = 0.0)

    private fun populateDeliveryEstimates(deliveryParams: DeliveryParams, deliveries: List<Delivery>) {

        val unprocessedDeliveries = mutableListOf<Delivery>()
        unprocessedDeliveries.addAll(deliveries)

        val vehicles = mutableListOf<Vehicle>()
        for (i in 0 until deliveryParams.numberOfVehicles) {
            vehicles.add(Vehicle())
        }

        while (unprocessedDeliveries.isNotEmpty()) {

            val vehicle = vehicles.sortedBy { it.timeElapsed }.first()
            val combinations = generatePotentialDeliveryCombination(unprocessedDeliveries.map { it.packageQuote }, deliveryParams.maxCarriableWeight)
            val targetCombination = combinations.first()

            val longestTrip = targetCombination.sortedByDescending { it.distance }

            targetCombination.forEach { quote ->
                val delivery = deliveries.firstOrNull { quote.packageId == it.packageQuote.packageId }
                delivery?.let { _delivery ->
                    _delivery.packageQuote.deliveryEstimate = vehicle.timeElapsed + (_delivery.packageQuote.distance.toDouble() / deliveryParams.maxSpeed.toDouble()).roundDown()
                    unprocessedDeliveries.remove(delivery)
                }
            }

            val vehicleDuration = longestTrip.first { it.deliveryEstimate != null }.deliveryEstimate!! * 2
            vehicle.timeElapsed = (vehicle.timeElapsed + vehicleDuration).roundDown()
        }
    }

    fun generateOrderWithDeliveryParams(inputs: List<String>): OrderAndDeliveryParams? {

        try {
            val orderParams = inputs.subList(0, inputs.size - 3)
            val maxCarriableWeight = inputs.last().toInt()
            val maxSpeed = inputs[inputs.size - 2].toInt()
            val numberOfVehicles = inputs[inputs.size - 3].toInt()
            val order = generateOrder(orderParams)

            order?.let { _order ->
                return OrderAndDeliveryParams(_order, DeliveryParams(numberOfVehicles, maxSpeed, maxCarriableWeight))
            }

        }
        catch (nfe: NumberFormatException) {
            return null
        } catch (ie: IndexOutOfBoundsException) {
            return null
        }
        return null
    }

    fun generateOrder(inputs: List<String>): Order? {
        val minimumRequiredArgs = 6
        val argumentsPerItem = 4
        val orderArguments = 2

        if (inputs.size < minimumRequiredArgs) {
            return null
        }
        else {

            try {
                val baseDeliveryCost = inputs[0].toInt()
                val numberOfPackages = inputs[1].toInt()

                val expectedNumberOfArguments = (numberOfPackages * argumentsPerItem) + orderArguments
                var index = orderArguments

                if (expectedNumberOfArguments != inputs.size) {
                    return null
                }

                val packages = mutableListOf<PackageQuote>()

                while (index < inputs.size) {

                    val packageId = inputs[index]
                    val weight = inputs[index + 1].toInt()
                    val distance = inputs[index + 2].toInt()
                    val code = inputs[index + 3]

                    packages.add(PackageQuote(packageId, weight, distance, code))
                    index += argumentsPerItem
                }

                return Order(baseDeliveryCost, packages)

            } catch (nfe: NumberFormatException) {
                return null
            } catch (ie: IndexOutOfBoundsException) {
                return null
            }
        }
    }
}