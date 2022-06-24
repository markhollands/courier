package datamodel

data class PackageCost(val packageId: String, val discount: Int, val totalCost: Int) {
    override fun toString(): String {
        return "$packageId $discount $totalCost"
    }
}