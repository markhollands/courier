package datamodel

data class PackageQuote(val packageId: String, val weight: Int, val distance: Int, var discountCodeText: String, var deliveryEstimate: Double? = null) {
    val discount: DiscountCode?

    init {
        val code = DiscountCodeManager.getDiscountCode(discountCodeText)
        val applicable = code?.testApplicability(distance, weight)
        discount = if (applicable == true) code else null
    }
}

