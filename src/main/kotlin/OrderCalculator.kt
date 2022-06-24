import datamodel.DiscountCode
import datamodel.PackageCost
import datamodel.PackageQuote
import datamodel.discounts.Offer001

class OrderCalculator {

    val discountCodes: List<DiscountCode> = listOf(Offer001())

    fun processOrder(packageQuote: PackageQuote): PackageCost {

        discountCodes.firstOrNull { code -> code.}

    }
}