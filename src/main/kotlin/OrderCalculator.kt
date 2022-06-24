import datamodel.DiscountCode
import datamodel.Order
import datamodel.PackageCost
import datamodel.PackageQuote
import datamodel.discounts.Offer001

object OrderCalculator {

    private val discountCodes: List<DiscountCode> = listOf(Offer001())

    fun processOrder(order: Order): List<PackageCost> {

        return order.packages.map { packageQuote ->
            val code = discountCodes.firstOrNull { code -> code.name == packageQuote.discountCode }
            val validCode = if (code?.testApplicability(packageQuote) == true) code else null
            calculatePackageCost(order, packageQuote, validCode)
        }
    }

    private fun calculatePackageCost(order: Order, packageQuote: PackageQuote, discountCode: DiscountCode?): PackageCost {
        // Base Delivery Cost + (Package Total Weight * 10) +
        //(Distance to Destination * 5)

        return PackageCost(packageQuote.packageId, 0, 0)
    }
}