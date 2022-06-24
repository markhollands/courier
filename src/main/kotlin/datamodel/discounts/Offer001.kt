package datamodel.discounts

import datamodel.DiscountCode
import datamodel.PackageQuote

class Offer001: DiscountCode {

    override val name: String = "OFR001"
    override val maxDistance: Int? = 200
    override val minDistance: Int? = null
    override val maxWeight: Int? = 200
    override val minWeight: Int? = 70
    override val discountPercentage: Float = 0.1f

    override fun testApplicability(packageQuote: PackageQuote): Boolean {

    }

    override fun calculateDiscount(packageQuote: PackageQuote): Int {
        TODO("Not yet implemented")
    }

}