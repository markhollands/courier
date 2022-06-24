package datamodel.discounts

import datamodel.DiscountCode

class Offer003: DiscountCode {

    override val name: String = "OFR003"
    override val maxDistance: Int? = 250
    override val minDistance: Int? = 50
    override val maxWeight: Int? = 150
    override val minWeight: Int? = 10
    override val discountPercentage: Float = 0.05f
}