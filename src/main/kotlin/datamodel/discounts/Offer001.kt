package datamodel.discounts

import datamodel.DiscountCode

class Offer001: DiscountCode {

    override val name: String = "OFR001"
    override val maxDistance: Int? = 200
    override val minDistance: Int? = null
    override val maxWeight: Int? = 200
    override val minWeight: Int? = 70
    override val discountPercentage: Float = 0.1f
}