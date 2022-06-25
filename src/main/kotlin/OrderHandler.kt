import datamodel.*
import util.permutations
import util.roundDown

object OrderHandler {

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

    private fun generatePotentialDeliveryCombinations(deliveries: List<PackageQuote>, maxCarriableWeight: Int): List<List<PackageQuote>> {
        val permutations = deliveries.permutations()
        val weightLimited = applyWeightLimitation(permutations, maxCarriableWeight)
        val sortedBySize = weightLimited.sortedByDescending { it.size }
        val mostOnly = sortedBySize.filter { it.size == sortedBySize.first().size }
        return mostOnly.sortedByDescending { it.sumOf { item -> item.weight }}
    }

    private fun applyWeightLimitation(permutations: List<List<PackageQuote>>, maxCarriableWeight: Int): MutableList<List<PackageQuote>> {
        val weightLimited = mutableListOf<List<PackageQuote>>()

        permutations.forEach { permutation ->
            val mutablePermutation = permutation.toMutableList()
            while (mutablePermutation.sumOf { it.weight } > maxCarriableWeight) {
                mutablePermutation.removeLast()
            }
            weightLimited.add(mutablePermutation)
        }
        return weightLimited
    }

    data class Vehicle(var timeElapsed: Double = 0.0)

    private fun populateDeliveryEstimates(deliveryParams: DeliveryParams, deliveries: List<Delivery>) {

        val unprocessedDeliveries = mutableListOf<Delivery>()
        unprocessedDeliveries.addAll(deliveries)

        val vehicles = createVehicles(deliveryParams.numberOfVehicles)

        while (unprocessedDeliveries.isNotEmpty()) {

            val vehicle = vehicles.sortedBy { it.timeElapsed }.first()
            val combinations = generatePotentialDeliveryCombinations(unprocessedDeliveries.map { it.packageQuote }, deliveryParams.maxCarriableWeight)
            val targetCombination = combinations.first()
            val longestTrip = targetCombination.sortedByDescending { it.distance }

            targetCombination.forEach { quote ->
                val delivery = deliveries.firstOrNull { quote.packageId == it.packageQuote.packageId }
                delivery?.let { _delivery ->
                    _delivery.packageQuote.deliveryEstimate = vehicle.timeElapsed + calculateDeliveryTime(_delivery.packageQuote.distance, deliveryParams.maxSpeed)
                    unprocessedDeliveries.remove(delivery)
                }
            }

            val deliveryDuration = longestTrip.first { it.deliveryEstimate != null }.deliveryEstimate!! * 2
            vehicle.timeElapsed = (vehicle.timeElapsed + deliveryDuration).roundDown()
        }
    }

    private fun createVehicles(numberOfVehicles: Int): List<Vehicle> {
        val vehicles = mutableListOf<Vehicle>()
        for (i in 0 until numberOfVehicles) {
            vehicles.add(Vehicle())
        }
        return vehicles
    }

    private fun calculateDeliveryTime(distance: Int, maxSpeed: Int) =
        (distance.toDouble() / maxSpeed.toDouble()).roundDown()

}