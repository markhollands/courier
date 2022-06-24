package datamodel

data class PackageQuote(val packageId: String, val weight: Int, val distance: Int, var discountCodeText: String) {
    val discount: DiscountCode?

    init {
        val code = DiscountCodeManager.getDiscountCode(discountCodeText)
        discount = if (code?.testApplicability(this) == true) code else null
    }
}

data class PackageCost(val packageId: String, val discount: Int, val totalCost: Int) {
    override fun toString(): String {
        return "$packageId $discount $totalCost"
    }
}