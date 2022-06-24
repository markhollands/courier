package datamodel

interface DiscountCode {

    val name: String
    val maxDistance: Int?
    val minDistance: Int?
    val maxWeight: Int?
    val minWeight: Int?
    val discountPercentage: Float

    fun testApplicability(packageQuote: PackageQuote): Boolean {

        // TODO convert these into range checks
        maxDistance?.let { _maxDistance ->
            if (packageQuote.distance > _maxDistance) {
                 return false
            }
        }

        minDistance?.let { _minDistance ->
            if (packageQuote.distance < _minDistance) {
                return false
            }
        }

        maxWeight?.let { _maxWeight ->
            if (packageQuote.weight > _maxWeight) {
                return false
            }
        }

        minWeight?.let { _minWeight ->
            if (packageQuote.weight < _minWeight) {
                return false
            }
        }
        return true
    }

    fun calculateDiscount(packageQuote: PackageQuote): Int
}