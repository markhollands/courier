package datamodel

data class Order(val baseDeliveryCost: Int, val packages: List<PackageQuote>) {

    fun calculatePackageCost(packageQuote: PackageQuote): PackageCost {

        val deliveryCost = calculateTotalPrice(packageQuote)
        val discount: Int = if (packageQuote.discount != null) (deliveryCost * packageQuote.discount.discountPercentage).toInt() else 0
        return PackageCost(packageQuote.packageId, discount, deliveryCost - discount)
    }

    private val weightMultiplier = 10
    private fun calculateTotalPrice(packageQuote: PackageQuote): Int = baseDeliveryCost + (packageQuote.weight * weightMultiplier) + (packageQuote.distance * 5)
}