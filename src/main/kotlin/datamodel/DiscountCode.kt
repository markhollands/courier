package datamodel

interface DiscountCode {

    val name: String
    val maxDistance: Int?
    val minDistance: Int?
    val maxWeight: Int?
    val minWeight: Int?
    val discountPercentage: Float

    fun testApplicability(distance: Int, weight: Int): Boolean {

        if (maxDistance != null && distance > maxDistance!!) {
             return false
        }
        if (minDistance != null && distance < minDistance!!) {
            return false
        }
        if (maxWeight != null && weight > maxWeight!!) {
            return false
        }
        if (minWeight != null && weight < minWeight!!) {
            return false
        }

        return true
    }
}