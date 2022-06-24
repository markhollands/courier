package datamodel.discounts

import datamodel.DiscountCode
import datamodel.PackageQuote

class Offer002: DiscountCode {

    override val name: String = "OFR002"
    override val maxDistance: Int? = 150
    override val minDistance: Int? = 50
    override val maxWeight: Int? = 250
    override val minWeight: Int? = 100
    override val discountPercentage: Float = 0.07f
}