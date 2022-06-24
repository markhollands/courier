package datamodel

data class PackageQuote(val packageId: String, val weight: Int, val distance: Int, val discountCode: String)

data class PackageCost(val packageId: String, val discount: Int, val totalCost: Int)