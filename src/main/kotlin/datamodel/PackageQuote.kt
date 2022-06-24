package datamodel

import util.round

data class PackageQuote(val packageId: String, val weight: Int, val distance: Int, var discountCodeText: String, var deliveryEstimate: Double? = null) {
    val discount: DiscountCode?

    init {
        val code = DiscountCodeManager.getDiscountCode(discountCodeText)
        val applicable = code?.testApplicability(distance, weight)
        discount = if (applicable == true) code else null
    }
}

data class PackageCost(val packageId: String, val discount: Int, val totalCost: Int) {
    override fun toString(): String {
        return "$packageId $discount $totalCost"
    }
}

data class Delivery(val packageQuote: PackageQuote, val packageCost: PackageCost) {
    override fun toString(): String {
        return "${packageCost.packageId} ${packageCost.discount} ${packageCost.totalCost} ${packageQuote.deliveryEstimate?.round(2)}"
    }
}