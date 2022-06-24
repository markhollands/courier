package datamodel

import util.round

data class Delivery(val packageQuote: PackageQuote, val packageCost: PackageCost) {
    override fun toString(): String {
        return "${packageCost.packageId} ${packageCost.discount} ${packageCost.totalCost} ${packageQuote.deliveryEstimate?.round(2)}"
    }
}